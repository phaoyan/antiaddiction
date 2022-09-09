package pers.juumii.antiaddiction.model.environment.collector;

import org.apache.commons.math3.util.Pair;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerProcess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Service
public class ComputerProcessDataCollector implements ComputerDataCollector{


    private ArrayList<String> getProcessLines(){
        ArrayList<String> res = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("cmd /c tasklist");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (int i = 0; i < 100; i++) {
                br.readLine();
            }
            String line;
            while ((line = br.readLine()) != null)
                res.add(line);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
    private ArrayList<Pair<String, Integer>> getProcessDetailList(){
        ArrayList<Pair<String, Integer>> processDetailList = new ArrayList<>();
        ArrayList<String> processLines = getProcessLines();

        outer:
        for(String e: processLines){
            String[] split = e.split(" ");
            String processName = split[0];

            StringBuilder formatInteger = new StringBuilder();
            for(String sub: split[split.length - 2].split(",")){
                formatInteger.append(sub);
            }
            int memoryUsage = Integer.parseInt(formatInteger.toString().equals("") ? "-1": formatInteger.toString());

            for(Pair<String, Integer> detail: processDetailList){
                if(detail.getFirst().equals(processName))
                    continue outer;
            }
            processDetailList.add(new Pair<>(processName, memoryUsage));
        }

        return processDetailList;
    }
    @Override
    public Environment collect() {
        ComputerEnvironment res = new ComputerEnvironment();
        ArrayList<Pair<String, Integer>> processDetailList = getProcessDetailList();
        for(Pair<String, Integer> e: processDetailList)
            res.mount(new ComputerProcess(e.getFirst(),e.getSecond()));
        return res;
    }

}

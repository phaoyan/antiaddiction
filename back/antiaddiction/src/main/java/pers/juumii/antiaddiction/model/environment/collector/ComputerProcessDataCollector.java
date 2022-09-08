package pers.juumii.antiaddiction.model.environment.collector;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.util.Pair;
import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerProcess;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class ComputerProcessDataCollector implements ComputerDataCollector{

    private static final ComputerProcessDataCollector INSTANCE = new ComputerProcessDataCollector();

    public ArrayList<String> ignoreList, concernList;

    public void initIgnoreList(){ //将初始化的第一次收集的进程数据视为可以忽视的数据
        ArrayList<String> ignoreList = new ArrayList<>();
        for(EnvironmentData data: collect().getDatum())
            ignoreList.add(((ComputerProcess)data).getProcessName());
        setIgnoreList(ignoreList);
    }

    public void setConcernList(ArrayList<String> concernList){
        this.concernList = concernList;
    }

    public void setConcernList(String[] concernList){
        this.concernList = new ArrayList<>();
        this.concernList.addAll(Arrays.asList(concernList));
    }

    public void setIgnoreList(ArrayList<String> ignoreList) {
        this.ignoreList = ignoreList;
        try {
            FileUtils.writeStringToFile(new File(Paths.COMPUTER_PROCESS_IGNORE_LIST_PATH),new Gson().toJson(ignoreList), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToIgnoreList(String processName){
        if(ignoreList == null)
            ignoreList = new ArrayList<>();
        ignoreList.add(processName);
        try {
            FileUtils.writeStringToFile(new File(Paths.COMPUTER_PROCESS_IGNORE_LIST_PATH),new Gson().toJson(ignoreList), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getIgnoreList() {
        return ignoreList;
    }

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
            if(ignoreList != null && ignoreList.contains(processName))
                continue;
            if(concernList !=null && !concernList.contains(processName))
                continue;

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

    public static ComputerProcessDataCollector getInstance(){
        return INSTANCE;
    }
}

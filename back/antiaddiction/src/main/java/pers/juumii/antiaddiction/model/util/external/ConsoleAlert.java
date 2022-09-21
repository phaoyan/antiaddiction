package pers.juumii.antiaddiction.model.util.external;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.SpringConfig;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerProcess;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.WebsiteBrowsingData;
import pers.juumii.antiaddiction.model.util.Paths;
import pers.juumii.antiaddiction.model.util.SpringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsoleAlert extends Alert{

    private List<String> cmdLines;
    public ConsoleAlert(){}

    @PostConstruct
    public void init(){
        cmdLines = new ArrayList<>();
        cmdLines.add("@echo off");
        cmdLines.add("if \"%v%\"==\"Y\" (");
        cmdLines.add(")");
    }
    @Override
    public void addShortcut(List<EnvironmentData> environmentDataList) {
        for(EnvironmentData data: environmentDataList){
            if(data instanceof ComputerProcess)
                addComputerProcessShortcut(((ComputerProcess) data).getProcessName());
            else if(data instanceof WebsiteBrowsingData)
                addWebsiteShortcut(((WebsiteBrowsingData) data).getUrl());
        }
    }

    @Override
    public void setBehaviorName(String behaviorName) {
        this.behaviorName = behaviorName;
        cmdLines.add(1,"set /p v=ready for " + behaviorName + "?(Y/N)");
    }

    private void addWebsiteShortcut(URL url) {
        cmdLines.add(cmdLines.size()-1,"start " + url);
    }
    private void addComputerProcessShortcut(String processName) {
        //todo
    }

    @Override
    public void open() {
        try{
            StringBuilder cmd = new StringBuilder();
            for(String line: cmdLines)
                cmd.append(line).append("\n");
            String path = Paths.getAbsolutePrefix() + Paths.getBatRoot() + "alert.bat";
            File src = new File(path);
            if(!src.exists())
                src.createNewFile();
            FileUtils.writeStringToFile(src, cmd.toString(), StandardCharsets.UTF_8);
            CMD.run(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        new AnnotationConfigApplicationContext(SpringConfig.class);
        ArrayList<EnvironmentData> environmentDataArrayList = new ArrayList<>();
        environmentDataArrayList.add(new WebsiteBrowsingData(new URL("https://cn.bing.com/")));

        List<Alert> alerts = SpringUtils.getSubclasses(Alert.class);
        alerts.get(0).setBehaviorName("teee");
        alerts.get(0).addShortcut(environmentDataArrayList);
        alerts.get(0).open();
    }
}

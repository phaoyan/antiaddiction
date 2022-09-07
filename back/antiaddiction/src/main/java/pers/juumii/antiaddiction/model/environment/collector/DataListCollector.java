package pers.juumii.antiaddiction.model.environment.collector;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.util.Pair;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerProcess;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerScreenData;
import pers.juumii.antiaddiction.model.util.FileRelated;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DataListCollector{

    private static final DataListCollector INSTANCE = new DataListCollector();
    private OverallEnvironmentDataCollector collector;
    private long interval;
    private boolean flag;
    private OverallEnvironment environment = new OverallEnvironment();
    private File src;

    private DataListCollector(){}


    public void setCollector(OverallEnvironmentDataCollector collector) {
        this.collector = collector;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setSrc(File src) {
        this.src = src;
    }

    public void setEnvironment(OverallEnvironment environment) {
        this.environment = environment == null ? this.environment : environment;
    }

    public OverallEnvironmentDataCollector getCollector() {
        return collector;
    }

    public long getInterval() {
        return interval;
    }

    public boolean isFlag() {
        return flag;
    }

    public OverallEnvironment getEnvironment() {
        return environment;
    }

    public File getSrc() {
        return src;
    }

    public void updateDataList(OverallEnvironment environment) {
        ArrayList<Integer> idCodes = new ArrayList<>();
        for(EnvironmentData data: this.environment.getDatum()){
            idCodes.add(data.getIdCode());
        }
        for(EnvironmentData data: environment.getDatum())
            if(!idCodes.contains(data.getIdCode()) && !(data instanceof ComputerScreenData))
                this.environment.getDatum().add(data);
        FileRelated.toJsonFile(src, this.environment);
    }

    public void updateDataList(){
        try {
            ArrayList<String> ignoreList = new Gson().fromJson(FileUtils.readFileToString(new File(Paths.COMPUTER_PROCESS_IGNORE_LIST_PATH), StandardCharsets.UTF_8),ArrayList.class);
            for(String processName: ignoreList)
                environment.getDatum().removeIf(data -> new ComputerProcess(processName).equals(data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataListCollector getInstance(){
        return INSTANCE;
    }
}

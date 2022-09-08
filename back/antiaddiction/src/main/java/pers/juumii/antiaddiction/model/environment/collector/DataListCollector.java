package pers.juumii.antiaddiction.model.environment.collector;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerScreenData;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.WebsiteBrowsingData;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.IOCContainer;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DataListCollector{

    private static final DataListCollector INSTANCE = new DataListCollector();
    private OverallEnvironmentDataCollector collector;
    private boolean flag;
    private OverallEnvironment environment = new OverallEnvironment();
    private File src;

    private DataListCollector(){}


    public void setCollector(OverallEnvironmentDataCollector collector) {
        this.collector = collector;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setSrc(File src) {
        this.src = src;
        if(!src.exists()) {
            try {
                src.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setEnvironment() {
        try {
            OverallEnvironment environment = AdaptedGsonProvider.getGsonWithDeserializeAdapter().fromJson(FileUtils.readFileToString(src, StandardCharsets.UTF_8), OverallEnvironment.class);
            this.environment = environment == null ? this.environment : environment;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public OverallEnvironmentDataCollector getCollector() {
        return collector;
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
        try {
            FileUtils.writeStringToFile(src,AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(this.environment),StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateDataList(){
        try {
            ArrayList<String> ignoreList = new Gson().fromJson(FileUtils.readFileToString(new File(Paths.COMPUTER_PROCESS_IGNORE_LIST_PATH), StandardCharsets.UTF_8),ArrayList.class);
            for(String s: ignoreList){
                environment.getDatum().removeIf(data->{
                    if(data instanceof WebsiteBrowsingData){
                        System.out.println(((WebsiteBrowsingData)data).getUrl().toString() + ":" + data.getIdCode());
                        System.out.println(s + ":" + s.hashCode());
                    }
                    return data.getIdCode() == s.hashCode();});
            }
            FileUtils.writeStringToFile(src,AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(this.environment),StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static DataListCollector getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        IOCContainer.initialize();
        for(EnvironmentData data: DataListCollector.getInstance().getEnvironment().getDatum()){
            System.out.println(data.getClassName());
        }
    }
}

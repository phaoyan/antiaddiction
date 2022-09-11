package pers.juumii.antiaddiction.model.environment.collector;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerScreenData;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class DataListCollector{


    @Autowired
    private OverallEnvironmentDataCollector collector;
    private OverallEnvironment environment;
    @Autowired
    IgnoreList ignoreList;
    @Autowired
    private Paths paths;

    private DataListCollector(){}

    @PostConstruct
    public void init(){
        try {
            File src = new File(paths.getDataListSrc());
            if(!src.exists()) {
                src.createNewFile();
            }
            setEnvironment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setEnvironment() {
        try {
            OverallEnvironment environment = AdaptedGsonProvider.getGsonWithDeserializeAdapter().fromJson(FileUtils.readFileToString(new File(paths.getDataListSrc()), StandardCharsets.UTF_8), OverallEnvironment.class);
            this.environment = environment == null ? this.environment : environment;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public OverallEnvironment getEnvironment() {
        return environment;
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
            FileUtils.writeStringToFile(new File(paths.getDataListSrc()),AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(this.environment),StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void updateDataList(){
        try {
            for(String s: ignoreList.getIgnoreList()){
                environment.getDatum().removeIf(data-> data.getIdCode() == s.hashCode());
            }
            FileUtils.writeStringToFile(new File(paths.getDataListSrc()),AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(this.environment),StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

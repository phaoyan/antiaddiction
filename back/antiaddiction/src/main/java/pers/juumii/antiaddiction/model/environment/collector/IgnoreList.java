package pers.juumii.antiaddiction.model.environment.collector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IgnoreList {

    private List<String> ignoreList;

    @PostConstruct
    public void init(){
        try {
            ignoreList = new ArrayList<>();
            File src = new File(Paths.getIgnoreListSrc());
            JsonArray ignoreItems = AdaptedGsonProvider.getGsonWithDeserializeAdapter().fromJson(FileUtils.readFileToString(src, StandardCharsets.UTF_8), JsonArray.class);
            for(JsonElement item: ignoreItems)
                ignoreList.add(item.getAsString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getIgnoreList() {
        return ignoreList;
    }
    public void addIgnore(String item){
        ignoreList.add(item);
        toFile();
    }

    private void toFile() {
        try {
            FileUtils.writeStringToFile(new File(Paths.getIgnoreListSrc()),AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(ignoreList), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

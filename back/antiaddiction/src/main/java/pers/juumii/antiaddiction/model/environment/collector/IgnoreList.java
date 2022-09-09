package pers.juumii.antiaddiction.model.environment.collector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IgnoreList {

    @Value("${ignoreListSrc}")
    private String src;
    private List<String> ignoreList;

    @PostConstruct
    public void init(){
        try {
            ignoreList = new ArrayList<>();
            File src = new File(this.src);
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
    }
}
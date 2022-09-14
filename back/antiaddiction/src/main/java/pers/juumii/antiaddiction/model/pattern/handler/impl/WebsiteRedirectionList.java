package pers.juumii.antiaddiction.model.pattern.handler.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WebsiteRedirectionList {

    @Autowired
    private Paths paths;

    private List<String> websiteRedirectionList;

    @PostConstruct
    public void init(){
        readFile();
    }

    private void readFile() {
        try{
            websiteRedirectionList = new ArrayList<>();
            File src = new File(paths.getWebsiteRedirectionListSrc());
            if(!src.exists())
                src.createNewFile();
            String jsonString = FileUtils.readFileToString(src, StandardCharsets.UTF_8);
            Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
            JsonArray items = gson.fromJson(jsonString, JsonArray.class);
            if(items != null)
                for(JsonElement e: items)
                    websiteRedirectionList.add(e.getAsString());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void toFile() {
        try {
            FileUtils.writeStringToFile(new File(paths.getWebsiteRedirectionListSrc()), AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(websiteRedirectionList),StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String sourceUrl, String targetUrl){
        if(!websiteRedirectionList.contains(sourceUrl + " " + targetUrl))
            websiteRedirectionList.add(sourceUrl + " " + targetUrl);
        toFile();
    }

    public void remove(String sourceUrl, String targetUrl){
        websiteRedirectionList.remove(sourceUrl + " " + targetUrl);
        toFile();
    }


    public List<String> getWebsiteRedirectionList() {
        return websiteRedirectionList;
    }
}

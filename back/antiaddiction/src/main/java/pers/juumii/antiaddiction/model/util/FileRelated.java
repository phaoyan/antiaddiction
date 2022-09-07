package pers.juumii.antiaddiction.model.util;

import com.google.gson.*;
import org.apache.commons.io.FileUtils;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileRelated {

    public static void main(String[] args) {
        BehaviorHistory behaviorHistory = readJsonFile(new File("D:\\coding\\javaProject\\springProject\\demo\\src\\main\\resources\\static\\json\\history\\2022-08-23.json"), BehaviorHistory.class);
        System.out.println(behaviorHistory.getEndTime());
    }
    public static <T> T readJsonFile(File src, Class<T> cl){
        Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
        try {
            if(!src.exists())
                src.createNewFile();
            return gson.fromJson(FileUtils.readFileToString(src, StandardCharsets.UTF_8), cl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void toJsonFile(File src, Object target){
        Gson gson = AdaptedGsonProvider.getGsonWithSerializeAdapter();
        try {
            if(!src.exists())
                src.createNewFile();
            FileUtils.writeStringToFile(src,gson.toJson(target),StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

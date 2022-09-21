package pers.juumii.antiaddiction.model.util;

import com.google.gson.*;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.pattern.handler.impl.ImplUnit;
import pers.juumii.antiaddiction.model.pattern.handler.impl.AddToWebsiteRedirectionListImpl;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdaptedGsonProvider {

    private static <T> void addInterfaceDeserializer(Class<T> clazz, GsonBuilder builder){
        builder.registerTypeAdapter(clazz, (JsonDeserializer<T>) (json, type, jsonDeserializationContext) -> {
            try {
                String className = json.getAsJsonObject().get("className").getAsString();
                Class<?> cls = Class.forName(className);
                return (T) cls.getConstructor(JsonElement.class).newInstance(json);
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static Gson getGsonWithDeserializeAdapter(){
        GsonBuilder builder = new GsonBuilder();

        //LocalDateTime反序列化适配器
        builder.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
            String datetime = json.getAsJsonPrimitive().getAsString();
            return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        });

        //各个数据接口的反序列化适配器
        addInterfaceDeserializer(EnvironmentData.class, builder);
        addInterfaceDeserializer(BehaviorPattern.class, builder);

        return builder.create();
    }

    public static Gson getGsonWithSerializeAdapter(){
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (source, typeOfSrc, context) -> new JsonPrimitive(source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        return builder.create();
    }
}

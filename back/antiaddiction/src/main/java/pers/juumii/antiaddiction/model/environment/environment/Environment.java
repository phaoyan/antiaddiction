package pers.juumii.antiaddiction.model.environment.environment;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pers.juumii.antiaddiction.exception.NonexistenceException;


import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Environment {

    protected LocalDateTime moment;
    protected ArrayList<EnvironmentData> datum;
    public Environment(){
        this.moment = LocalDateTime.now();
        this.datum = new ArrayList<>();
    }
    public LocalDateTime getMoment(){
        return moment;
    }
    public ArrayList<EnvironmentData> getDatum(){
        return datum;
    }
    public EnvironmentData getDataByIdCode(int idCode) throws NonexistenceException {
        for(EnvironmentData data: datum){
            if(data.getIdCode() == idCode)
                return data;
        }
        throw new NonexistenceException("The data with idCode: " + idCode + "isn't exist.");
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public void setDatum(ArrayList<EnvironmentData> datum) {
        this.datum = datum;
    }

    public void construct(Environment environment){
        if(environment == null)
            return;
        datum.addAll(environment.getDatum());
    }

    public void mount(EnvironmentData data) {
        datum.add(data);
    }

    public ArrayList<EnvironmentData> getDatumFromJson(JsonArray datum){
        ArrayList<EnvironmentData> res = new ArrayList<>();
        for(JsonElement e: datum){
            String typeName = e.getAsJsonObject().get("type").getAsString();
        }

        return res;
    }

}

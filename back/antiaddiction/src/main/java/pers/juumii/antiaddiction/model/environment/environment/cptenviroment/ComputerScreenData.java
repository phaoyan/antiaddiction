package pers.juumii.antiaddiction.model.environment.environment.cptenviroment;

import com.google.gson.JsonElement;

import java.io.File;

public class ComputerScreenData implements ComputerEnvironmentData{

    public final String className = getClass().getName();
    private String imgSrcString;
    public String getImgSrcString() {
        return imgSrcString;
    }

    public void setImgSrcString(String imgSrcString) {
        this.imgSrcString = imgSrcString;
    }

    public ComputerScreenData(String imgSrcString) {
        this.imgSrcString = imgSrcString;
    }

    public ComputerScreenData(JsonElement json){
        this.imgSrcString = json.getAsJsonObject().get("imgSrcString").getAsString();
    }
    @Override
    public int getIdCode() {
        return imgSrcString.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ComputerScreenData && ((ComputerScreenData)obj).getIdCode() == this.getIdCode();
    }

    @Override
    public String getClassName() {
        return className;
    }
}

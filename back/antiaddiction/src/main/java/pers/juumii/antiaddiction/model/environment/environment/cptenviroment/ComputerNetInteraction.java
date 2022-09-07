package pers.juumii.antiaddiction.model.environment.environment.cptenviroment;

import com.google.gson.JsonElement;

public class ComputerNetInteraction implements ComputerEnvironmentData{

    public final String className = getClass().getName();

    public ComputerNetInteraction(JsonElement json){
        //todo
    }
    @Override
    public int getIdCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ComputerNetInteraction && ((ComputerNetInteraction)obj).getIdCode() == this.getIdCode();

    }

    @Override
    public String getClassName() {
        return className;
    }


}

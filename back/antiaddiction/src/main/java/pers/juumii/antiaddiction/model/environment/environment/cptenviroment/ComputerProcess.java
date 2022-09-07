package pers.juumii.antiaddiction.model.environment.environment.cptenviroment;

import com.google.gson.JsonElement;

public class ComputerProcess implements ComputerEnvironmentData{

    public final String className = getClass().getName();
    private String processName;
    private int memoryUsage;

    public ComputerProcess(){}
    public ComputerProcess(String processName){
        this.processName = processName;
        this.memoryUsage = -1;
    }

    public ComputerProcess(String processName, int memoryUsage) {
        this.processName = processName;
        this.memoryUsage = memoryUsage;
    }

    public ComputerProcess(JsonElement json){
        this.processName = json.getAsJsonObject().get("processName").getAsString();
        this.memoryUsage = json.getAsJsonObject().get("memoryUsage").getAsInt();
    }

    public String getProcessName() {
        return processName;
    }


    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(int memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    @Override
    public int getIdCode() {
        return processName.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof ComputerProcess && ((ComputerProcess)obj).getIdCode() == this.getIdCode();
    }

    @Override
    public String getClassName() {
        return className;
    }
}

package pers.juumii.antiaddiction.model.pattern.handler.impl;

import com.google.gson.JsonElement;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerProcess;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.util.CMD;
import pers.juumii.antiaddiction.model.util.SpringUtils;

public class CeaseComputerProcessImpl implements BehaviorHandler {

    public final String className = getClass().getName();
    public final String simplifiedName = "cease computer process";
    private ComputerProcess target = new ComputerProcess("undefined");

    public ComputerProcess getTarget() {
        return target;
    }

    public CeaseComputerProcessImpl() {}

    public CeaseComputerProcessImpl(JsonElement json) {
        init(json);
    }

    public void setTarget(ComputerProcess target) {
        this.target = target;
    }



    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getSimplifiedName() {
        return simplifiedName;
    }

    @Override
    public void init(JsonElement json) {
        target = new ComputerProcess(json.getAsJsonObject().get("target").getAsJsonObject().get("processName").getAsString());
    }

    @Override
    public void handle(Event event) {
        CMD cmd = SpringUtils.getBean(CMD.class);
        cmd.taskKill(target.getProcessName());
    }


}

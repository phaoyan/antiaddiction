package pers.juumii.antiaddiction.model.pattern.handler.impl;

import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerProcess;
import pers.juumii.antiaddiction.model.util.CMD;
import pers.juumii.antiaddiction.model.util.SpringUtils;

public class CeaseComputerProcessImpl implements ImplUnit {

    public final String className = getClass().getName();
    public final String simplifiedName = "cease computer process";
    private ComputerProcess target = new ComputerProcess("undefined");

    public ComputerProcess getTarget() {
        return target;
    }

    public CeaseComputerProcessImpl() {}

    public void setTarget(ComputerProcess target) {
        this.target = target;
    }




    public String getClassName() {
        return className;
    }


    public String getSimplifiedName() {
        return simplifiedName;
    }

    @Override
    public void handle() {
        CMD cmd = SpringUtils.getBean(CMD.class);
        cmd.taskKill(target.getProcessName());
    }


}

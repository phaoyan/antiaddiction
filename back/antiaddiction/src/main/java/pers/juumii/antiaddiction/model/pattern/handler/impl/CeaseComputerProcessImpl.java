package pers.juumii.antiaddiction.model.pattern.handler.impl;

import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerProcess;
import pers.juumii.antiaddiction.model.util.external.CMD;

public class CeaseComputerProcessImpl implements ImplUnit {

    private final ComputerProcess target;
    public CeaseComputerProcessImpl(ComputerProcess target) {
        this.target = target;
    }
    @Override
    public void handle() {
        CMD.taskKill(target.getProcessName());
    }


}

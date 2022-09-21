package pers.juumii.antiaddiction.model.pattern.handler.impl;

import pers.juumii.antiaddiction.model.util.external.CMD;


public class RunComputerProcessImpl extends RunImpl{

    public final String className = getClass().getName();
    public final String simplifiedName = "run computer process";
    private String path;

    public RunComputerProcessImpl(){}


    public String getClassName() {
        return className;
    }


    public String getSimplifiedName() {
        return simplifiedName;
    }

    @Override
    public void handle() {
        CMD.run(path);
    }

}

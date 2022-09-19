package pers.juumii.antiaddiction.model.pattern.handler.impl;

import com.google.gson.JsonElement;
import pers.juumii.antiaddiction.model.util.CMD;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.SpringUtils;


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
        CMD cmd = SpringUtils.getBean(CMD.class);
        cmd.run(path);
    }

}

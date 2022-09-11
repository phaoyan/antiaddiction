package pers.juumii.antiaddiction.model.pattern.handler;

import org.springframework.beans.factory.annotation.Autowired;

public class StartupImpl implements BehaviorHandler{

    public final String className = getClass().getName();
    public final String simplifiedName = "startup";
    private RunImpl run;

    public RunImpl getRun() {
        return run;
    }

    public void setRun(RunImpl run) {
        this.run = run;
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
    public void handle() {

    }


}

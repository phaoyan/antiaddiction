package pers.juumii.antiaddiction.model.environment.collector;


import org.reflections.Reflections;
import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;

public class ComputerEnvironmentDataCollector implements DataCollector{

    private static final ComputerEnvironmentDataCollector INSTANCE = new ComputerEnvironmentDataCollector();

    @Override
    public Environment collect(){ //用于收集computer environment data，任务委派给下层完成
        ComputerEnvironment res = new ComputerEnvironment();

        Reflections reflections = new Reflections(getClass().getPackage().getName());
        Set<Class<? extends  ComputerDataCollector>> classes = reflections.getSubTypesOf(ComputerDataCollector.class);
        for (Class<? extends ComputerDataCollector> cl: classes){
            try {
                res.construct((Environment) cl.getMethod("collect").invoke(cl.getMethod("getInstance").invoke(null)));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        res.getDatum().removeAll(Collections.singleton(null));

        return res;
    }

    public static ComputerEnvironmentDataCollector getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(ComputerDataCollector.class.getPackage().getName());
    }
}

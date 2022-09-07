package pers.juumii.antiaddiction.model.util;


import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerProcess;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectionRelated {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("processName", "msedge.exe");
        params.put("memoryUsage", 10);
        ComputerProcess process = (ComputerProcess)createInstance("demo.model.environment.environment.cptenviroment.ComputerProcess", params);
        System.out.println(process);

    }

    public static Object createInstance(String className, HashMap<String, Object> params) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object res = Class.forName(className).getConstructor().newInstance();
        ArrayList<Method> methods = new ArrayList<>(List.of(res.getClass().getMethods()));
        ArrayList<Method> setters = getSetters(methods);
        for(Method e: setters){
            e.invoke(res, getParam(e, params));
        }

        return res;
    }

    private static ArrayList<Method> getSetters(ArrayList<Method> methods) {
        ArrayList<Method> res = new ArrayList<>();
        for(Method e: methods){
            if(e.getName().startsWith("set"))
                res.add(e);
        }

        return res;
    }

    private static Object getParam(Method method, HashMap<String,Object> params) {
        Class<?> type = method.getParameterTypes()[0];
        for(String name: params.keySet())
            if(type.getName().equals(name))
                return params.get(name);
        return null;
    }

}

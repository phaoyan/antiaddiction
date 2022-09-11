package pers.juumii.antiaddiction.model.util;

import org.apache.commons.math3.util.Pair;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;


public class TimeLine extends Thread{

    private final long unit;//时间单位,unit 毫秒
    private final ArrayList<Pair<LoopOperation, Integer>> operations;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public TimeLine(long unit){
        this.unit = unit;
        this.flag = true;
        this.operations = new ArrayList<>();
    }
    public void addTask(LoopOperation operation, int interval) {
        operations.add(new Pair<>(operation, interval));
    }

    @Override
    public void run() {
        System.out.println(new File("").getAbsolutePath());
        long counter = 0;
        while(flag){

            for(Pair<LoopOperation, Integer> entry: operations){
                if(counter % entry.getValue() == 0)
                    entry.getKey().operation();
            }

            counter ++;
            try {
                Thread.sleep(unit);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

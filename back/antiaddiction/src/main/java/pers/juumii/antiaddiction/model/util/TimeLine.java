package pers.juumii.antiaddiction.model.util;

import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.pattern.handler.event.GlobalBroadcast;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventType;

import java.util.ArrayList;

@Service
public class TimeLine extends Thread{

    private long unit;//时间单位,unit 毫秒
    private ArrayList<Pair<LoopOperation, Integer>> operations;
    @Autowired
    private GlobalBroadcast globalBroadcast;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public void setUnit(long unit) {
        this.unit = unit;
    }
    public void setOperations(ArrayList<Pair<LoopOperation, Integer>> operations) {
        this.operations = operations;
    }

    public TimeLine(){}

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
        globalBroadcast.onAction(new Event(EventType.StartupEvent));
        long counter = 0;
        while(flag){
            globalBroadcast.onAction(new Event(EventType.LoopEvent));
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

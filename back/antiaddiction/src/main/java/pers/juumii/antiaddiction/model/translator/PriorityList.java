package pers.juumii.antiaddiction.model.translator;


import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;

import java.util.ArrayList;

public class PriorityList{

    private static final PriorityList INSTANCE= new PriorityList();
    private ArrayList<MomentaryBehavior> priorityList;
    public ArrayList<MomentaryBehavior> getPriorityList() {
        return priorityList;
    }

    public void setPriorityList(ArrayList<MomentaryBehavior> priorityList) {
        this.priorityList = priorityList;
    }

    public MomentaryBehavior pick(ArrayList<MomentaryBehavior> behaviorList){
        behaviorList.sort((o1, o2) -> o1.equals(o2) ? 0 : (priorityList.subList(priorityList.indexOf(o1),priorityList.size()-1).contains(o2) ? -1 : 1));
        return behaviorList.get(0); //todo 待细节调试
    }

    public static PriorityList getInstance(){
        return INSTANCE;
    }
}

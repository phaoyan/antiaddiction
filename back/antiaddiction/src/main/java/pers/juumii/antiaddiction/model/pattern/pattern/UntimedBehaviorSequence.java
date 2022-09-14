package pers.juumii.antiaddiction.model.pattern.pattern;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;


import java.util.ArrayList;
import java.util.Comparator;

public class UntimedBehaviorSequence implements BehaviorPattern{

    public final String className = getClass().getName();
    public final String patternType = "untimedSequence";

    private ArrayList<TimedBehavior> behaviorList;

    public UntimedBehaviorSequence(){
        behaviorList = new ArrayList<>();
    }
    public UntimedBehaviorSequence(ArrayList<TimedBehavior> behaviors){
        setBehaviorList(behaviors);
    }
    public UntimedBehaviorSequence(JsonElement json){
        Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
        ArrayList<TimedBehavior> deserialized = new ArrayList<>();
        for(JsonElement behavior: json.getAsJsonObject().get("behaviorList").getAsJsonArray()){
            deserialized.add(gson.fromJson(behavior, TimedBehavior.class));
        }
        behaviorList = deserialized;
//        handler = gson.fromJson(json.getAsJsonObject().get("handler"), BehaviorHandler.class);
        for(TimedBehavior behavior: behaviorList){
            behavior.getDetails().clear();
        }
    }

    public void setBehaviorList(ArrayList<TimedBehavior> behaviorList) {
        behaviorList.sort(Comparator.comparing(TimedBehavior::getStartTime)); //todo 待细节调试
        this.behaviorList = behaviorList;
    }
    public ArrayList<TimedBehavior> getBehaviorList() {
        return this.behaviorList;
    }

    @Override
    public String getPatternType() {
        return patternType;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean equivalent(BehaviorPattern other) {
        if(!(other instanceof UntimedBehaviorSequence) || other.getBehaviorList().size() != getBehaviorList().size())
            return false;

        for(int i = 0; i < getBehaviorList().size(); i ++)
            if(!other.getBehaviorList().get(i).getName().equals(this.getBehaviorList().get(i).getName()))
                return false;

        return true;
    }


}

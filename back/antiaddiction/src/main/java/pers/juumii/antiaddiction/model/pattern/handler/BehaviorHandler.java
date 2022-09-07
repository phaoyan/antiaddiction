package pers.juumii.antiaddiction.model.pattern.handler;


public interface BehaviorHandler {

    String getClassName();
    String getSimplifiedName();
    void handle(); //核心功能，处理behavior pattern
}

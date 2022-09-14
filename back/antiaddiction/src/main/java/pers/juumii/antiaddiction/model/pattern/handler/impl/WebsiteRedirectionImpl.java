package pers.juumii.antiaddiction.model.pattern.handler.impl;

import com.google.gson.JsonElement;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventListener;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventType;
import pers.juumii.antiaddiction.model.util.SpringUtils;




public class WebsiteRedirectionImpl implements BehaviorHandler {

    public final String className = getClass().getName();
    public final String simplifiedName = "limit website access";
    private String targetUrl;
    private String sourceUrl;
    private WebsiteRedirectionList websiteRedirectionList;

    public WebsiteRedirectionImpl(){
    }

    public WebsiteRedirectionImpl(JsonElement json){
        init(json);
    }

    public void init(){
        websiteRedirectionList = SpringUtils.getBean(WebsiteRedirectionList.class);
    }

    public void init(JsonElement json){
        targetUrl = json.getAsJsonObject().get("targetUrl").getAsString();
        sourceUrl = json.getAsJsonObject().get("sourceUrl").getAsString();
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
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
    public void handle(Event event) {
        if(websiteRedirectionList == null)
            init();
        if(event.getSource() instanceof EventListener
                && ((EventListener)event.getSource()).getHandler().equals(this)
                && event.getEventType().equals(EventType.CreateEvent))
            websiteRedirectionList.add(sourceUrl, targetUrl);
        if(event.getSource() instanceof EventListener
                && ((EventListener)event.getSource()).getHandler().equals(this)
                && event.getEventType().equals(EventType.DeleteEvent))
            websiteRedirectionList.remove(sourceUrl, targetUrl);

    }
}

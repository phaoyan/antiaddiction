package pers.juumii.antiaddiction.model.pattern.handler.impl;

import com.google.gson.JsonElement;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventListener;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventType;
import pers.juumii.antiaddiction.model.util.SpringUtils;




public class WebsiteRedirectionImpl implements ImplUnit {

    public final String className = getClass().getName();
    public final String simplifiedName = "limit website access";
    private String targetUrl;
    private String sourceUrl;

    public WebsiteRedirectionImpl(){}
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


    public String getClassName() {
        return className;
    }


    public String getSimplifiedName() {
        return simplifiedName;
    }

    @Override
    public void handle() {
        WebsiteRedirectionList websiteRedirectionList = SpringUtils.getBean(WebsiteRedirectionList.class);
//        websiteRedirectionList.add(sourceUrl, targetUrl);
//        websiteRedirectionList.remove(sourceUrl, targetUrl);

    }
}

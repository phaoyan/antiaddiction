package pers.juumii.antiaddiction.model.pattern.handler;

import com.google.gson.JsonElement;

public class WebsiteRedirectionImpl implements BehaviorHandler{

    public final String className = getClass().getName();
    public final String simplifiedName = "limit website access";
    private String targetUrl;
    private String sourceUrl;

    public WebsiteRedirectionImpl(){}

    public WebsiteRedirectionImpl(JsonElement json){
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
    public void handle() {

    }
}

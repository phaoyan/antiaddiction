package pers.juumii.antiaddiction.model.pattern.handler.impl;

import pers.juumii.antiaddiction.model.util.SpringUtils;

public class RemoveFromWebsiteRedirectionListImpl implements ImplUnit{
    private final String targetUrl;
    private final String sourceUrl;

    public RemoveFromWebsiteRedirectionListImpl(String sourceUrl, String targetUrl) {
        this.targetUrl = targetUrl;
        this.sourceUrl = sourceUrl;
    }

    @Override
    public void handle() {
        WebsiteRedirectionList websiteRedirectionList = SpringUtils.getBean(WebsiteRedirectionList.class);
        websiteRedirectionList.remove(sourceUrl, targetUrl);
    }
}

package pers.juumii.antiaddiction.model.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class Paths {

    @Value("${staticPrefix}")
    private String staticPrefix;
    @Value("${behaviorHistoryRoot}")
    private String behaviorHistoryRoot;
    @Value("${dataListSrc}")
    private String dataListSrc;
    @Value("${ignoreListSrc}")
    private String ignoreListSrc;
    @Value("${patternListSrc}")
    private String patternListSrc;
    @Value("${manualSetRuleSrc}")
    private String manualSetRuleSrc;
    @Value("${screenSrc}")
    private String screenSrc;
    @Value("${globalListenerSrc}")
    private String globalListenerSrc;
    @Value("${websiteRedirectionListSrc}")
    private String websiteRedirectionListSrc;
    @Value("${batSrc}")
    private String batSrc;

    public String getStaticPrefix() {
        return staticPrefix;
    }

    public String getBehaviorHistoryRoot() {
        return staticPrefix + behaviorHistoryRoot;
    }

    public String getDataListSrc() {
        return staticPrefix + dataListSrc;
    }

    public String getIgnoreListSrc() {
        return staticPrefix + ignoreListSrc;
    }

    public String getPatternListSrc() {
        return staticPrefix + patternListSrc;
    }

    public String getManualSetRuleSrc() {
        return staticPrefix + manualSetRuleSrc;
    }

    public String getScreenSrc() {
        return staticPrefix + screenSrc;
    }

    public String getGlobalListenerSrc() {
        return staticPrefix + globalListenerSrc;
    }

    public String getWebsiteRedirectionListSrc() {
        return staticPrefix + websiteRedirectionListSrc;
    }

    public String getBatSrc() {
        return staticPrefix + batSrc;
    }
}

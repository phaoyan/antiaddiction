package pers.juumii.antiaddiction.model.util;


public class Paths {


    private static final String absolutePrefix = "D:/coding/projects/applicationProjects/antiaddiction/back/antiaddiction/";

    private static final String staticPrefix = "src/main/resources/static/";

    public static String getAbsolutePrefix() {
        return absolutePrefix;
    }
    public static String getAbsoluteSrc(String src){
        return absolutePrefix + src;
    }
    public static String getStaticPrefix() {
        return staticPrefix;
    }
    public static String getBehaviorHistoryRoot() {
        return staticPrefix + "json/history";
    }

    public static String getDataListSrc() {
        return staticPrefix + "json/dataList.json";
    }

    public static String getIgnoreListSrc() {
        return staticPrefix + "json/ignore.json";
    }
    public static String getPatternListSrc() {
        return staticPrefix + "json/patternList.json";
    }
    public static String getManualSetRuleSrc() {
        return staticPrefix + "json/manualSetRule.json";
    }
    public static String getScreenSrc() {
        return staticPrefix + "img/screen";
    }
    public static String getWebsiteRedirectionListSrc() {
        return staticPrefix + "json/websiteRedirectionList.json";
    }
    public static String getHandlerListSrc(){
        return staticPrefix + "json/handlerList.json";
    }
    public static String getBatRoot(){
        return staticPrefix + "bat/";
    }
    public static String getBatSrc() {
        return staticPrefix + "bat/temp.bat";
    }
    public static String getStartupBatSrc() {
        return staticPrefix + "bat/startup.bat";
    }
    public static String getJsonSchemaPrefix() {
        return staticPrefix + "json/schema/";
    }
    public static String getAutoRunHandlerSchemaSrc(){return getJsonSchemaPrefix() + "autoRunHandler.json";}
    public static String getCeaseComputerProcessHandlerSchemaSrc(){return getJsonSchemaPrefix() + "ceaseComputerProcessHandler.json";}
    public static String getWebsiteRedirectionHandlerSchemaSrc(){return getJsonSchemaPrefix() + "websiteRedirectionHandler.json";}
    public static String getGuidanceHandlerSchemaSrc() {
        return getJsonSchemaPrefix() + "guidanceHandler.json";
    }
}

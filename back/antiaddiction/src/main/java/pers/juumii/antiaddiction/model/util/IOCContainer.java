package pers.juumii.antiaddiction.model.util;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.behavior.collector.BehaviorHistoryCollector;
import pers.juumii.antiaddiction.model.behavior.collector.MomentaryBehaviorCollector;
import pers.juumii.antiaddiction.model.behavior.collector.TimedBehaviorCollector;
import pers.juumii.antiaddiction.model.environment.collector.*;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.collector.BehaviorPatternCollector;
import pers.juumii.antiaddiction.model.pattern.collector.TimedBehaviorSequenceCollector;
import pers.juumii.antiaddiction.model.pattern.collector.UntimedBehaviorSequenceCollector;
import pers.juumii.antiaddiction.model.translator.EnvironmentToBehaviorTranslator;
import pers.juumii.antiaddiction.model.translator.MapperRuleManager;
import pers.juumii.antiaddiction.model.translator.PriorityList;
import pers.juumii.antiaddiction.model.translator.mapperrule.ManualSetRule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

public class IOCContainer {

    public static void initialize(){
        ArrayList<String> ignoreList;
        try {
            ignoreList = new Gson().fromJson(FileUtils.readFileToString(new File(Paths.COMPUTER_PROCESS_IGNORE_LIST_PATH), StandardCharsets.UTF_8), ArrayList.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WebsiteBrowsingDataCollector websiteBrowsingDataCollector = WebsiteBrowsingDataCollector.getInstance();
        ComputerNetInteractionDataCollector computerNetInteractionDataCollector = ComputerNetInteractionDataCollector.getInstance();
        ComputerScreenDataCollector computerScreenDataCollector = ComputerScreenDataCollector.getInstance();
        ComputerProcessDataCollector computerProcessDataCollector = ComputerProcessDataCollector.getInstance();
        computerScreenDataCollector.setScale(0.5);
        computerProcessDataCollector.setIgnoreList(ignoreList);
        ManualSetRule manualSetRule = ManualSetRule.getInstance();
        manualSetRule.setSrc(new File(Paths.MANUAL_SET_RULE_PATH));
        manualSetRule.readFile();

        ComputerEnvironmentDataCollector computerEnvironmentDataCollector = ComputerEnvironmentDataCollector.getInstance();
        computerEnvironmentDataCollector.setNetDataCollector(computerNetInteractionDataCollector);
        computerEnvironmentDataCollector.setProcessDataCollector(computerProcessDataCollector);
        computerEnvironmentDataCollector.setScreenDataCollector(computerScreenDataCollector);
        computerEnvironmentDataCollector.setWebsiteBrowsingDataCollector(websiteBrowsingDataCollector);
        MapperRuleManager mapperRuleManager = MapperRuleManager.getInstance();
        mapperRuleManager.setBehaviorList(manualSetRule.getMapperBehaviorList());
        PriorityList priorityList = PriorityList.getInstance();
        priorityList.setPriorityList(manualSetRule.getMapperBehaviorList());

        OverallEnvironmentDataCollector overallEnvironmentDataCollector = OverallEnvironmentDataCollector.getInstance();
        overallEnvironmentDataCollector.setCeCollector(computerEnvironmentDataCollector);
        EnvironmentToBehaviorTranslator translator = EnvironmentToBehaviorTranslator.getInstance();
        translator.setRule(mapperRuleManager);
        translator.setPriorityList(priorityList);

        MomentaryBehaviorCollector momentaryBehaviorCollector = MomentaryBehaviorCollector.getInstance();
        momentaryBehaviorCollector.setCollector(overallEnvironmentDataCollector);
        momentaryBehaviorCollector.setTranslator(translator);

        BehaviorHistory behaviorHistory = FileRelated.readJsonFile(new File(Paths.HISTORY_PATH + "/" +LocalDate.now() + ".json"), BehaviorHistory.class);
        if(behaviorHistory == null || behaviorHistory.getHistory() == null){
            behaviorHistory = new BehaviorHistory();
            behaviorHistory.setHistory(new ArrayList<>());
        }
        TimedBehaviorCollector timedBehaviorCollector = TimedBehaviorCollector.getInstance();
        timedBehaviorCollector.setCollector(momentaryBehaviorCollector);
        timedBehaviorCollector.setPeriod(120*1000);

        BehaviorHistoryCollector behaviorHistoryCollector = BehaviorHistoryCollector.getInstance();
        behaviorHistoryCollector.setHistory(behaviorHistory);
        behaviorHistoryCollector.setTimedBehaviorCollector(timedBehaviorCollector);

        //data list
        DataListCollector dataListCollector = DataListCollector.getInstance();
        dataListCollector.setFlag(true);
        dataListCollector.setInterval(120*1000);
        dataListCollector.setSrc(new File(Paths.DATA_LIST_PATH));
        dataListCollector.setEnvironment(FileRelated.readJsonFile(dataListCollector.getSrc(), OverallEnvironment.class));
        dataListCollector.setCollector(overallEnvironmentDataCollector);

        //pattern list
        PatternList patternList = PatternList.getInstance();
        patternList.setSrc(new File(Paths.PATTERN_LIST_PATH));
        patternList.readFile();
        UntimedBehaviorSequenceCollector untimedSequenceCollector = UntimedBehaviorSequenceCollector.getInstance();
        TimedBehaviorSequenceCollector timedSequenceCollector = TimedBehaviorSequenceCollector.getInstance();
        untimedSequenceCollector.setPatternList(patternList.getPatterns());
        untimedSequenceCollector.setHistory(behaviorHistory);
        timedSequenceCollector.setPatternList(patternList.getPatterns());
        timedSequenceCollector.setHistory(behaviorHistory);

        BehaviorPatternCollector behaviorPatternCollector = BehaviorPatternCollector.getInstance();
        behaviorPatternCollector.setFlag(true);
        behaviorPatternCollector.setInterval(120*1000);
        behaviorPatternCollector.setHistory(behaviorHistory);
        behaviorPatternCollector.setPatternList(patternList);
        behaviorPatternCollector.setCollection(new ArrayList<>());
        behaviorPatternCollector.setUntimedSequenceCollector(untimedSequenceCollector);
        behaviorPatternCollector.setTimedSequenceCollector(timedSequenceCollector);

    }

}

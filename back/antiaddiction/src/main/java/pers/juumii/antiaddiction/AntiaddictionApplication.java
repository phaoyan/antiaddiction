package pers.juumii.antiaddiction;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import pers.juumii.antiaddiction.model.behavior.collector.BehaviorHistoryCollector;
import pers.juumii.antiaddiction.model.behavior.collector.TimedBehaviorCollector;
import pers.juumii.antiaddiction.model.environment.collector.DataListCollector;
import pers.juumii.antiaddiction.model.environment.collector.OverallEnvironmentDataCollector;
import pers.juumii.antiaddiction.model.pattern.collector.BehaviorPatternCollector;
import pers.juumii.antiaddiction.model.util.SpringUtils;
import pers.juumii.antiaddiction.model.util.TimeLine;

import java.util.ArrayList;

@SpringBootApplication
public class AntiaddictionApplication {


	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AntiaddictionApplication.class);
		builder.headless(false).run(args);

		OverallEnvironmentDataCollector overallEnvironmentDataCollector = SpringUtils.getBean(OverallEnvironmentDataCollector.class);
		TimedBehaviorCollector timedBehaviorCollector = SpringUtils.getBean(TimedBehaviorCollector.class);
		BehaviorHistoryCollector behaviorHistoryCollector = SpringUtils.getBean(BehaviorHistoryCollector.class);
		BehaviorPatternCollector behaviorPatternCollector = SpringUtils.getBean(BehaviorPatternCollector.class);
		DataListCollector dataListCollector = SpringUtils.getBean(DataListCollector.class);
		TimeLine timeLine = SpringUtils.getBean(TimeLine.class);
		timeLine.setUnit(60*1000);
		timeLine.setFlag(true);
		timeLine.setOperations(new ArrayList<>());
		timeLine.addTask(timedBehaviorCollector::collect, 2);
		timeLine.addTask(behaviorHistoryCollector::collect, 2);
		timeLine.addTask(behaviorPatternCollector::collect,2);
		timeLine.addTask(()->dataListCollector.updateDataList(overallEnvironmentDataCollector.collect()),5);
		timeLine.start();

	}

}

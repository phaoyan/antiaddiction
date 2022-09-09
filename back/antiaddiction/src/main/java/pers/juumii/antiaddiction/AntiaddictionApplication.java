package pers.juumii.antiaddiction;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.juumii.antiaddiction.model.behavior.collector.BehaviorHistoryCollector;
import pers.juumii.antiaddiction.model.behavior.collector.TimedBehaviorCollector;
import pers.juumii.antiaddiction.model.environment.collector.DataListCollector;
import pers.juumii.antiaddiction.model.environment.collector.OverallEnvironmentDataCollector;
import pers.juumii.antiaddiction.model.pattern.collector.BehaviorPatternCollector;
import pers.juumii.antiaddiction.model.pattern.handler.OverallHandler;
import pers.juumii.antiaddiction.model.util.TimeLine;

@SpringBootApplication
public class AntiaddictionApplication {


	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		OverallEnvironmentDataCollector overallEnvironmentDataCollector = ctx.getBean(OverallEnvironmentDataCollector.class);
		TimedBehaviorCollector timedBehaviorCollector = ctx.getBean(TimedBehaviorCollector.class);
		BehaviorHistoryCollector behaviorHistoryCollector = ctx.getBean(BehaviorHistoryCollector.class);
		BehaviorPatternCollector behaviorPatternCollector = ctx.getBean(BehaviorPatternCollector.class);
		DataListCollector dataListCollector = ctx.getBean(DataListCollector.class);
		OverallHandler overallHandler = ctx.getBean(OverallHandler.class);
		TimeLine timeLine = new TimeLine(60*1000);
		timeLine.addTask(timedBehaviorCollector::collect, 2);
		timeLine.addTask(behaviorHistoryCollector::collect, 2);
		timeLine.addTask(behaviorPatternCollector::collect,2);
		timeLine.addTask(()->dataListCollector.updateDataList(overallEnvironmentDataCollector.collect()),5);
		timeLine.addTask(overallHandler::handle,2);
		timeLine.start();


		SpringApplicationBuilder builder = new SpringApplicationBuilder(AntiaddictionApplication.class);
		builder.headless(false).run(args);

	}

}

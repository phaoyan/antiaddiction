package pers.juumii.antiaddiction;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import pers.juumii.antiaddiction.model.behavior.collector.BehaviorHistoryCollector;
import pers.juumii.antiaddiction.model.behavior.collector.TimedBehaviorCollector;
import pers.juumii.antiaddiction.model.environment.collector.DataListCollector;
import pers.juumii.antiaddiction.model.environment.collector.OverallEnvironmentDataCollector;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.collector.BehaviorPatternCollector;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;
import pers.juumii.antiaddiction.model.util.IOCContainer;
import pers.juumii.antiaddiction.model.util.TimeLine;

@SpringBootApplication
public class AntiaddictionApplication {


	public static void main(String[] args) {
		IOCContainer.initialize();

		SpringApplicationBuilder builder = new SpringApplicationBuilder(AntiaddictionApplication.class);
		builder.headless(false).run(args);

	}

}

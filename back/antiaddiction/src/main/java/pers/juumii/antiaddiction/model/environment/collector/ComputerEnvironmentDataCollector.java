package pers.juumii.antiaddiction.model.environment.collector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;

import java.util.Collections;
import java.util.List;

@Service
public class ComputerEnvironmentDataCollector implements DataCollector{

    @Autowired
    private List<ComputerDataCollector> collectors;
    @Override
    public Environment collect(){ //用于收集computer environment data，任务委派给下层完成
        ComputerEnvironment res = new ComputerEnvironment();
        for(ComputerDataCollector collector: collectors)
            res.construct(collector.collect());

        res.getDatum().removeAll(Collections.singleton(null));

        return res;
    }


}

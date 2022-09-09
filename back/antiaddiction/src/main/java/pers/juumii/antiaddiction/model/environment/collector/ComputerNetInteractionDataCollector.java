package pers.juumii.antiaddiction.model.environment.collector;


import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;

@Service
public class ComputerNetInteractionDataCollector implements ComputerDataCollector{
    @Override
    public Environment collect() {
        return new ComputerEnvironment();
    }

}

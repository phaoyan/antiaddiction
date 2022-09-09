package pers.juumii.antiaddiction.model.environment.collector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

@Service
public class OverallEnvironmentDataCollector implements DataCollector{
    @Autowired
    private ComputerEnvironmentDataCollector ceCollector;
    @Autowired
    private IgnoreList ignoreList;
    @Override
    public OverallEnvironment collect() {
        OverallEnvironment res = new OverallEnvironment();
        res.construct(ceCollector.collect());

        ignore(res);

        return res;
    }

    private void ignore(OverallEnvironment env) {
        if(ignoreList == null)
            return;
        for(String s: ignoreList.getIgnoreList())
            env.getDatum().removeIf(data->data.getIdCode() == s.hashCode());
    }

}

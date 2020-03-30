package eggplant.backend.service;

import eggplant.backend.model.EggplantConfig;
import eggplant.backend.model.Scenario;
import eggplant.backend.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EggplantConfigServiceImpl implements EggplantConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public void updateConfig(Scenario scenario) {
        EggplantConfig config = getConfig();
        boolean hasChange = false;

        if (!config.getCorrectionActions()
                .contains(scenario.getCorrectionAction())
        ){
            config.getCorrectionActions().add(scenario.getCorrectionAction());
            hasChange = true;
        }

        if (!config.getFailSteps()
                .contains(scenario.getFailStepKeyWord())
        ){
            config.getFailSteps().add(scenario.getFailStepKeyWord());
            hasChange = true;
        }

        if (!config.getTrainingLabels()
                .contains(scenario.getTrainingLabel())
        ){
            config.getTrainingLabels().add(scenario.getTrainingLabel());
            hasChange = true;
        }

        if (hasChange) {
            configRepository.save(config);
        }
    }

    @Override
    public EggplantConfig getConfig() {
        return configRepository.findAll().get(0);
    }
}

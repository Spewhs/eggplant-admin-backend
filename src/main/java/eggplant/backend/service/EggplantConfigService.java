package eggplant.backend.service;

import eggplant.backend.model.EggplantConfig;
import eggplant.backend.model.Scenario;

public interface EggplantConfigService {

    void updateConfig(Scenario scenario);

    EggplantConfig getConfig();
}

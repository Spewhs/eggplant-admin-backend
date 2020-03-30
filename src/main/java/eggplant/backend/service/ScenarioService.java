package eggplant.backend.service;

import eggplant.backend.dto.scenario.CreateScenarioRequest;
import eggplant.backend.dto.scenario.UpdateScenarioParams;
import eggplant.backend.model.DatasetStat;
import eggplant.backend.model.Scenario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface ScenarioService {

    Page<Scenario> getScenarioPage(Integer page, Integer pageSize);

    Scenario getScenarioById(String id);

    Slice<Scenario> getDataset(Integer page, Integer pageSize);

    Scenario createScenario(CreateScenarioRequest params);

    Scenario updateScenario(UpdateScenarioParams params);

    Scenario changeScenarioStatus(String id);

    DatasetStat getDatasetStats();
}

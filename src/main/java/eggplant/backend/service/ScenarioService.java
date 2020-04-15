package eggplant.backend.service;

import eggplant.backend.dto.scenario.CreateScenarioParams;
import eggplant.backend.dto.scenario.CreateScenarioRequest;
import eggplant.backend.dto.scenario.UpdateScenarioParams;
import eggplant.backend.model.DatasetStat;
import eggplant.backend.model.Scenario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface ScenarioService {

    Page<Scenario> getScenarioPage(Integer page, Integer pageSize);

    Scenario getScenarioById(String id);

    Scenario getScenarioByZucchiniId(String id);

    Slice<Scenario> getDataset(Integer page, Integer pageSize);

    Scenario createScenario(CreateScenarioParams params);

    Scenario updateScenarioWithId(UpdateScenarioParams params);

    Scenario updateScenarioWithZucchiniId(UpdateScenarioParams params);

    Scenario changeScenarioStatus(String id);

    DatasetStat getDatasetStats();
}

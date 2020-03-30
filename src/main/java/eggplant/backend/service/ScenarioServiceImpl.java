package eggplant.backend.service;

import eggplant.backend.dto.scenario.CreateScenarioRequest;
import eggplant.backend.dto.scenario.UpdateScenarioParams;
import eggplant.backend.exception.NoDocument;
import eggplant.backend.model.DatasetStat;
import eggplant.backend.model.Scenario;
import eggplant.backend.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
public class ScenarioServiceImpl implements ScenarioService {

    @Autowired
    private ScenarioRepository scenarioRepository;

    @Override
    public Page<Scenario> getScenarioPage(Integer page, Integer pageSize) {
        return scenarioRepository.findAll(
                PageRequest.of(page, page + pageSize)
        );
    }

    @Override
    public Scenario getScenarioById(String id) {
        return scenarioRepository.findByZucchiniId(id);
    }

    @Override
    public Slice<Scenario> getDataset(Integer page, Integer pageSize) {
        return scenarioRepository.findByUsedInDatasetIsTrue(
                PageRequest.of(page, page + pageSize)
        );
    }

    @Override
    public Scenario createScenario(CreateScenarioRequest params) {
        ZonedDateTime createdAt = ZonedDateTime.now(ZoneOffset.UTC);
        Scenario scenario = new Scenario(
                params.getZucchiniId(),
                params.getTrace(),
                params.getTrainingLabel(),
                params.getCorrectionAction(),
                params.getScenarioKey(),
                params.getFailStepKeyWord(),
                createdAt,
                true
        );
        scenarioRepository.insert(scenario);
        return scenario;
    }

    @Override
    public Scenario updateScenario(UpdateScenarioParams params) {
        Scenario scenarioToUpdate = scenarioRepository.findById(params.getId())
                .orElseThrow(NoDocument::new);

        params.getCorrectionAction()
                .ifPresent(scenarioToUpdate::setCorrectionAction);

        params.getTrainingLabel()
                .ifPresent(scenarioToUpdate::setTrainingLabel);

        params.getUsedInDataset()
                .ifPresent(scenarioToUpdate::setUsedInDataset);

        scenarioRepository.save(scenarioToUpdate);
        return scenarioToUpdate;
    }

    @Override
    public Scenario changeScenarioStatus(String id) {
        Scenario scenario = scenarioRepository.findById(id)
                .orElseThrow(NoDocument::new);

        scenario.setUsedInDataset(!scenario.isUsedInDataset());
        scenarioRepository.save(scenario);
        return scenario;
    }

    @Override
    public DatasetStat getDatasetStats() {
        //TODO implement
        return null;
    }
}

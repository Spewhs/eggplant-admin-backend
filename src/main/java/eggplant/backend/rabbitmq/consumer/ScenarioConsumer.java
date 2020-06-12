package eggplant.backend.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eggplant.backend.dto.scenario.CreateScenarioParams;
import eggplant.backend.dto.scenario.UpdateScenarioParams;
import eggplant.backend.dto.stats.EntryInDatasetStats;
import eggplant.backend.dto.zucchini.ZucchiniScenario;
import eggplant.backend.exception.NoDocument;
import eggplant.backend.model.Scenario;
import eggplant.backend.rabbitmq.producer.EggplantPredictionProducer;
import eggplant.backend.service.DatasetStatsService;
import eggplant.backend.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ScenarioConsumer {

    @Autowired
    private ScenarioService scenarioService;

    @Autowired
    private EggplantPredictionProducer eggplantPredictionProducer;

    @Autowired
    private DatasetStatsService datasetStatsService;

    public void receiveMessage(byte[] message) {
        ZucchiniScenario scenario;
        try {
            scenario = new ObjectMapper().readValue(new String(message), ZucchiniScenario.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        if (scenario.getTrainingLabel() == null || scenario.getTrainingLabel().equals("")) {
            makeAPrediction(scenario);
        } else {
            addOrUpdateToDataset(scenario);
        }
    }

    private void makeAPrediction(ZucchiniScenario scenarioJson) {
        Scenario scenario = createNewScenario(scenarioJson);
        try {
            eggplantPredictionProducer.submitPrediction(scenario);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void addOrUpdateToDataset(ZucchiniScenario entry) {
        try {
            Scenario scenario = scenarioService.getScenarioByZucchiniId(entry.getId());
            updateExistingScenario(entry);
        } catch (NoDocument e) {
            createNewScenario(entry);
        }
    }

    private void updateExistingScenario(ZucchiniScenario entry) {
        boolean usedInDataset = entry.getTrainingLabel() != null;
        UpdateScenarioParams updateScenario = new UpdateScenarioParams(
                entry.getId(),
                Optional.of(entry.getTrainingLabel()),
                Optional.of(entry.getCorrectionAction()),
                Optional.of(usedInDataset)
        );
        scenarioService.updateScenarioWithZucchiniId(updateScenario);
    }

    private Scenario createNewScenario(ZucchiniScenario entry) {
        CreateScenarioParams newScenario = new CreateScenarioParams(
                entry.getId(),
                entry.getTestRunId(),
                entry.getTrace(),
                Optional.ofNullable(entry.getTrainingLabel()),
                Optional.ofNullable(entry.getCorrectionAction()),
                entry.getScenarioKey(),
                entry.getFailStepKeyWord()
        );

        /*
        if (newScenario.getCorrectionAction().isPresent() && newScenario.getTrainingLabel().isPresent()) {
            datasetStatsService.addNewEntry(new EntryInDatasetStats(
                    newScenario.getTrainingLabel().get(),
                    newScenario.getCorrectionAction().get(),
                    newScenario.getFailStepKeyWord()
            ));
        }
        */

        return scenarioService.createScenario(newScenario);
    }
}
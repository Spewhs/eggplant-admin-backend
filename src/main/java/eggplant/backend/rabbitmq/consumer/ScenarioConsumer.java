package eggplant.backend.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eggplant.backend.dto.scenario.CreateScenarioParams;
import eggplant.backend.dto.scenario.UpdateScenarioParams;
import eggplant.backend.dto.zucchini.ZucchiniScenario;
import eggplant.backend.model.Scenario;
import eggplant.backend.rabbitmq.producer.EggplantPredictionProducer;
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

    public void receiveMessage(byte[] message) {
        ZucchiniScenario scenario;
        try {
            scenario = new ObjectMapper().readValue(new String(message), ZucchiniScenario.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("New incoming message");
        if (scenario.getTrainingLabel() == null || scenario.getTrainingLabel().equals("")) {
            makeAPrediction(scenario);
        } else {
            addOrUpdateToDataset(scenario);
        }
    }

    private void makeAPrediction(ZucchiniScenario scenarioJson) {
        System.out.println("Making prediction for new object");
        Scenario scenario = createNewScenario(scenarioJson);
        try {
            eggplantPredictionProducer.submitPrediction(scenario);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void addOrUpdateToDataset(ZucchiniScenario entry) {
        Scenario scenario = scenarioService.getScenarioByZucchiniId(entry.getId());
        if (scenario == null) {
            createNewScenario(entry);
        } else {
            updateExistingScenario(entry);
        }
    }

    private void updateExistingScenario(ZucchiniScenario entry) {
        System.out.println("Updating existing scenario");
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
        System.out.println("Adding new entry to dataset");
        CreateScenarioParams newScenario = new CreateScenarioParams(
                entry.getId(),
                entry.getTrace(),
                Optional.ofNullable(entry.getTrainingLabel()),
                Optional.ofNullable(entry.getCorrectionAction()),
                entry.getScenarioKey(),
                entry.getFailStepKeyWord()
        );
        return scenarioService.createScenario(newScenario);
    }
}
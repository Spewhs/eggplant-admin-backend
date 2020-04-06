package eggplant.backend.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eggplant.backend.dto.scenario.CreateScenarioRequest;
import eggplant.backend.dto.scenario.UpdateScenarioParams;
import eggplant.backend.model.Scenario;
import eggplant.backend.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;


@Component
public class ScenarioConsumer {

    @Autowired
    private ScenarioService scenarioService;

    public void receiveMessage(String message) {
        HashMap<String, Object> scenario;
        try {
            scenario = new ObjectMapper().readValue(message, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("New incoming message");
        if (scenario.containsKey("trainingLabel") && !scenario.get("trainingLabel").equals("")) {
            addOrUpdateToDataset(scenario);
        } else {
            makeAPrediction(scenario);
        }
        System.out.println();
    }

    private void makeAPrediction(HashMap<String, Object> scenario) {
        System.out.println("Making prediction for new object");
    }

    private void addOrUpdateToDataset(HashMap<String, Object> entry) {
        if (entry.containsKey("id")) {
            Scenario scenario = scenarioService.getScenarioById(entry.get("id") + "");
            if (scenario != null) {
                updateExistingScenario(entry);
                return;
            }
        }
        createNewScenario(entry);
    }

    private void updateExistingScenario(HashMap<String, Object> entry) {
        System.out.println("Updating existing scenario");
        UpdateScenarioParams updateScenario = new UpdateScenarioParams(
                entry.get("id").toString(),
                Optional.of(entry.get("trainingLabel").toString()),
                Optional.of(entry.get("correctionAction").toString()),
                Optional.of(Boolean.valueOf(entry.get("usedInDataset").toString()))
        );
        scenarioService.updateScenario(updateScenario);
    }

    private void createNewScenario(HashMap<String, Object> entry) {
        System.out.println("Adding new entry to dataset");
        CreateScenarioRequest newScenario = new CreateScenarioRequest(
                entry.get("zucchiniId").toString(),
                entry.get("trace").toString(),
                entry.get("trainingLabel").toString(),
                entry.get("correctionAction").toString(),
                entry.get("scenarioKey").toString(),
                entry.get("failStepKeyWord").toString()
        );
        scenarioService.createScenario(newScenario);
    }
}

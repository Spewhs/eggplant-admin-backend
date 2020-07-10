package eggplant.backend.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eggplant.backend.dto.classifier.CreateClassifierParams;
import eggplant.backend.dto.classifier.CreateRabbitMQClassifierParams;
import eggplant.backend.service.ClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrainingConsumer {

    @Autowired
    private ClassifierService classifierService;

    public void receiveMessage(byte[] message) {
        CreateRabbitMQClassifierParams createScenarioParams;

        try {
            createScenarioParams = new ObjectMapper().readValue(new String(message), CreateRabbitMQClassifierParams.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        classifierService.createNewClassifier(
                new CreateClassifierParams(
                        Optional.of(createScenarioParams.getVersion()),
                        Optional.of(createScenarioParams.getTrainingAccuracy()),
                        Optional.of(createScenarioParams.getInterestingWords())
                )
        );
    }
}

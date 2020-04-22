package eggplant.backend.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eggplant.backend.configuration.rabbitMq.TrainingQueueConfiguration;
import eggplant.backend.configuration.rabbitMq.SubmitPredictionQueueConfiguration;
import eggplant.backend.dto.prediction.SubmitPrediction;
import eggplant.backend.dto.rabbitmq.EggplantAdministration;
import eggplant.backend.model.Classifier;
import eggplant.backend.model.Scenario;
import eggplant.backend.service.ClassifierService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EggplantPredictionProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ClassifierService classifierService;

    public void submitPrediction(Scenario scenario) throws JsonProcessingException {

        Classifier activeClassifier = classifierService.getActiveClassifier();

        submitPrediction(scenario, activeClassifier.getId());
    }

    public void submitPrediction(Scenario scenario, String classifierId) throws JsonProcessingException {

        SubmitPrediction submitPrediction = new SubmitPrediction(
                scenario.getId(),
                classifierId,
                scenario.getTrace(),
                scenario.getFailStepKeyWord(),
                scenario.getZucchiniId()
        );

        rabbitTemplate.convertAndSend(
                SubmitPredictionQueueConfiguration.EGGPLANT_SUBMIT_PREDICTION_EXCHANGE,
                SubmitPredictionQueueConfiguration.PREDICTION_ROUTING_KEY,
                new ObjectMapper().writeValueAsString(submitPrediction)
        );

    }
}

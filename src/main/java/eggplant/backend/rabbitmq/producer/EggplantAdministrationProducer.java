package eggplant.backend.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eggplant.backend.configuration.rabbitMq.TrainingQueueConfiguration;
import eggplant.backend.configuration.rabbitMq.SubmitPredictionQueueConfiguration;
import eggplant.backend.dto.rabbitmq.EggplantAdministration;
import eggplant.backend.model.Scenario;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EggplantAdministrationProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    private final String PREDICTION = "PREDICTION";
    private final String TRAINING = "TRAINING";

    public void submitPrediction(Scenario scenario) throws JsonProcessingException {
        sendMessage(PREDICTION, SubmitPredictionQueueConfiguration.PREDICTION_ROUTING_KEY, scenario);
    }

    public void submitNewTraining() throws JsonProcessingException {
        sendMessage(TRAINING, TrainingQueueConfiguration.TRAIN_ROUTING_KEY, new Object());
    }

    private void sendMessage(String actionType, String routingkey, Object payload) throws JsonProcessingException {
        EggplantAdministration administrationMessage = new EggplantAdministration(actionType, payload);
        String message = new ObjectMapper().writeValueAsString(administrationMessage);
        rabbitTemplate.convertAndSend(
                SubmitPredictionQueueConfiguration.EGGPLANT_SUBMIT_PREDICTION_EXCHANGE,
                routingkey,
                message
        );
    }
}

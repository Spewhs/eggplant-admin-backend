package eggplant.backend.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eggplant.backend.configuration.RabbitMqConfiguration;
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
    private final String CHANGE_ACTIVE_CLASSIFIER = "CHANGE_ACTIVE_CLASSIFIER";

    public void submitPrediction(Scenario scenario) throws JsonProcessingException {
        sendMessage(PREDICTION, scenario);
    }

    public void processNewTraining() throws JsonProcessingException {
        sendMessage(TRAINING, new Object());
    }

    public void changeActiveClassifier(/* ADD Object */) throws JsonProcessingException {
        sendMessage(CHANGE_ACTIVE_CLASSIFIER, /* ADD Object */new Object());
    }

    private void sendMessage(String actionType, Object payload) throws JsonProcessingException {
        EggplantAdministration administrationMessage = new EggplantAdministration(actionType, payload);
        String message = new ObjectMapper().writeValueAsString(administrationMessage);
        rabbitTemplate.convertAndSend(
                RabbitMqConfiguration.EGGPLANT_ADMINISTRATION_EXCHANGE,
                "",
                message
        );
    }
}

package eggplant.backend.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eggplant.backend.configuration.RabbitMqConfiguration;
import eggplant.backend.model.Scenario;
import eggplant.backend.service.ScenarioService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScenarioProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ScenarioService scenarioService;

    private void sendNewScenario() {
        System.out.println("Send new message");
        System.out.println();
        Scenario scenario = scenarioService.getScenarioPage(0, 1).toList().get(0);
        String message = null;
        try {
            message = new ObjectMapper().writeValueAsString(scenario);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("error send");
            return;
        }
        rabbitTemplate.convertAndSend(
                RabbitMqConfiguration.ZUCCHINI_INCOMING_EXCHANGE,
                "scenario",
                message
        );
    }
}

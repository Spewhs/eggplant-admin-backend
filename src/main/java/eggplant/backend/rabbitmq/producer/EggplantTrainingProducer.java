package eggplant.backend.rabbitmq.producer;

import eggplant.backend.configuration.rabbitMq.TrainingQueueConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EggplantTrainingProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void submitNewTraining() {
        rabbitTemplate.convertAndSend(
                TrainingQueueConfiguration.EGGPLANT_SUBMIT_TRAINING_EXCHANGE,
                TrainingQueueConfiguration.TRAIN_ROUTING_KEY,
                new Object()
        );
    }
}

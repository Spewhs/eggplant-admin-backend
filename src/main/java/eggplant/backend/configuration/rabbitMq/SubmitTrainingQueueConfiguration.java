package eggplant.backend.configuration.rabbitMq;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SubmitTrainingQueueConfiguration {

    public static final String EGGPLANT_SUBMIT_TRAINING_QUEUE = "eggplant-submit-training";

    public static final String EGGPLANT_SUBMIT_TRAINING_EXCHANGE = "eggplant-submit-training";

    public static final String TRAIN_ROUTING_KEY = "train";

    @Bean
    public Queue submitTrainingQueue() {
        return new Queue(EGGPLANT_SUBMIT_TRAINING_QUEUE, true);
    }

    @Bean
    public TopicExchange submitTrainingExchange() {
        return new TopicExchange(EGGPLANT_SUBMIT_TRAINING_EXCHANGE);
    }

    @Bean
    public Binding submitTrainingBinding(Queue submitTrainingQueue, TopicExchange submitTrainingExchange) {
        return BindingBuilder.bind(submitTrainingQueue).to(submitTrainingExchange).with(TRAIN_ROUTING_KEY);
    }
}

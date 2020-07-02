package eggplant.backend.configuration.rabbitMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TrainingQueueConfiguration {

    public static final String EGGPLANT_TRAINING_QUEUE = "eggplant-training";

    public static final String EGGPLANT_TRAINING_EXCHANGE = "eggplant-training";

    public static final String TRAIN_ROUTING_KEY = "train";

    @Bean
    public Queue trainingQueue() {
        return new Queue(EGGPLANT_TRAINING_QUEUE, true);
    }

    @Bean
    public TopicExchange trainingExchange() {
        return new TopicExchange(EGGPLANT_TRAINING_EXCHANGE);
    }

    @Bean
    public Binding trainingBinding(Queue trainingQueue, TopicExchange trainingExchange) {
        return BindingBuilder.bind(trainingQueue).to(trainingExchange).with(TRAIN_ROUTING_KEY);
    }
}

package eggplant.backend.configuration.rabbitMq;

import eggplant.backend.rabbitmq.consumer.PredictionConsumer;
import eggplant.backend.rabbitmq.consumer.ScenarioConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TrainingQueueConfiguration {

    public static final String EGGPLANT_SUBMIT_TRAINING_QUEUE = "eggplant-submit-training";

    public static final String EGGPLANT_SUBMIT_TRAINING_EXCHANGE = "eggplant-submit-training";

    public static final String TRAIN_ROUTING_KEY = "train";

    @Bean
    public Queue trainingQueue() {
        return new Queue(EGGPLANT_SUBMIT_TRAINING_QUEUE, true);
    }

    @Bean
    public TopicExchange trainingExchange() {
        return new TopicExchange(EGGPLANT_SUBMIT_TRAINING_EXCHANGE);
    }

    @Bean
    public Binding trainingBinding(Queue trainingQueue, TopicExchange trainingExchange) {
        return BindingBuilder.bind(trainingQueue).to(trainingExchange).with(TRAIN_ROUTING_KEY);
    }
}

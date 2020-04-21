package eggplant.backend.configuration.rabbitMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SubmitPredictionQueueConfiguration {

    public static final String EGGPLANT_SUBMIT_PREDICTION_QUEUE = "eggplant-submit-prediction";

    public static final String EGGPLANT_SUBMIT_PREDICTION_EXCHANGE = "eggplant-submit-prediction";

    public static final String PREDICTION_ROUTING_KEY = "prediction";

    // Prediction queue creation
    @Bean
    public Queue submitPredictionQueue() {
        return new Queue(EGGPLANT_SUBMIT_PREDICTION_QUEUE);
    }

    @Bean
    public TopicExchange submitPredictionExchange() {
        return new TopicExchange(EGGPLANT_SUBMIT_PREDICTION_EXCHANGE);
    }

    @Bean
    public Binding submitPredictionBinding(Queue submitPredictionQueue, TopicExchange submitPredictionExchange) {
        return BindingBuilder.bind(submitPredictionQueue).to(submitPredictionExchange).with(PREDICTION_ROUTING_KEY);
    }
}

package eggplant.backend.configuration.rabbitMq;

import eggplant.backend.rabbitmq.consumer.PredictionConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PredictionQueueConfiguration {

    public static final String PREDICTION_QUEUE = "eggplant-prediction";

    public static final String PREDICTION_EXCHANGE = "eggplant-prediction";

    @Bean
    public Queue predictionQueue() {
        return new Queue(PREDICTION_QUEUE, true);
    }

    @Bean
    public FanoutExchange predictionExchange() {
        return new FanoutExchange(PREDICTION_EXCHANGE);
    }

    @Bean
    public Binding predictionBinding(Queue predictionQueue, FanoutExchange predictionExchange) {
        return BindingBuilder.bind(predictionQueue).to(predictionExchange);
    }

    @Bean
    SimpleMessageListenerContainer incomingPredictionContainer(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter incomingPredictionListener
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(PREDICTION_QUEUE);
        container.setMessageListener(incomingPredictionListener);
        return container;
    }

    @Bean
    MessageListenerAdapter incomingPredictionListener(PredictionConsumer predictionConsumer) {
        return new MessageListenerAdapter(predictionConsumer, "receiveMessage");
    }
}

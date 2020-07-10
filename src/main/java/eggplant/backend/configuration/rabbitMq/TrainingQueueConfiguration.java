package eggplant.backend.configuration.rabbitMq;

import eggplant.backend.rabbitmq.consumer.TrainingConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
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

    @Bean
    SimpleMessageListenerContainer incomingTrainedClassifier(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter incomingClassifierListener
    ){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(EGGPLANT_TRAINING_QUEUE);
        container.setMessageListener(incomingClassifierListener);
        return container;
    }

    @Bean
    MessageListenerAdapter incomingClassifierListener(TrainingConsumer trainingConsumer) {
        return new MessageListenerAdapter(trainingConsumer, "receiveMessage");
    }
}

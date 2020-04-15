package eggplant.backend.configuration;

import eggplant.backend.rabbitmq.consumer.ScenarioConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    // Todo Follow example: https://www.rabbitmq.com/tutorials/tutorial-three-spring-amqp.html
    // 1 Queue pour scénario entrant de zucchini
    // 1 Queue pour envoyer des exécution à Eggplant
    // 2 Queue reliées à un exchange en fanout pour la prédition vers Eggplant admin et Zucchini

    public static final String ZUCCHINI_INCOMING_QUEUE = "scenario";
    public static final String ZUCCHINI_INCOMING_EXCHANGE = "scenario";
    public static final String EGGPLANT_ADMINISTRATION_QUEUE = "eggplant-administration";
    public static final String EGGPLANT_ADMINISTRATION_EXCHANGE = "eggplant-administration";
    public static final String PREDICTION_QUEUE = "prediction";
    public static final String PREDICTION_EXCHANGE = "prediction";

    // Zucchini queue creation
    @Bean
    public Queue scenarioQueue() {
        return new Queue(ZUCCHINI_INCOMING_QUEUE, true);
    }

    @Bean
    public TopicExchange scenarioExchange() {
        return new TopicExchange(ZUCCHINI_INCOMING_EXCHANGE);
    }

    @Bean
    public Binding scenarioBinding(Queue scenarioQueue, TopicExchange scenarioExchange) {
        return BindingBuilder.bind(scenarioQueue).to(scenarioExchange).with("scenario");
    }

    // Administration queue creation
    @Bean
    public Queue administrationQueue() {
        return new Queue(EGGPLANT_ADMINISTRATION_QUEUE);
    }

    @Bean
    public TopicExchange administrationExchange() {
        return new TopicExchange(EGGPLANT_ADMINISTRATION_EXCHANGE);
    }

    @Bean
    public Binding administrationBinding(Queue administrationQueue, TopicExchange administrationExchange) {
        return BindingBuilder.bind(administrationQueue).to(administrationExchange).with("action");
    }

    // Prediction queue creation
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
    SimpleMessageListenerContainer container(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(
                ZUCCHINI_INCOMING_QUEUE,
                EGGPLANT_ADMINISTRATION_QUEUE,
                PREDICTION_QUEUE
        );
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(ScenarioConsumer scenarioConsumer) {
        return new MessageListenerAdapter(scenarioConsumer, "receiveMessage");
    }
}

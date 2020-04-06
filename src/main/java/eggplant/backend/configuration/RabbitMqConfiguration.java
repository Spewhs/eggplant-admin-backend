package eggplant.backend.configuration;

import eggplant.backend.rabbitmq.consumer.ScenarioConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class RabbitMqConfiguration {

    public static final String QUEUE_SCENARIO_NAME = "scenario";
    public static final String EXCHANGE_NAME = "eggplant";

    @Bean
    public Queue scenarioQueue() {
        return new Queue(QUEUE_SCENARIO_NAME, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue scenarioQueue, TopicExchange exchange) {
        return BindingBuilder.bind(scenarioQueue).to(exchange).with("scenario");
    }

    @Bean
    SimpleMessageListenerContainer container(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_SCENARIO_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(ScenarioConsumer scenarioConsumer) {
        return new MessageListenerAdapter(scenarioConsumer, "receiveMessage");
    }
}

package eggplant.backend.configuration.rabbitMq;

import eggplant.backend.rabbitmq.consumer.ScenarioConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ZucchiniQueueConfiguration {

    public static final String ZUCCHINI_INCOMING_QUEUE = "eggplant-zucchini-incoming-scenario";

    public static final String ZUCCHINI_INCOMING_EXCHANGE = "eggplant-zucchini-incoming-scenario";

    public static final String NEW_SCENARIO_ROUTING_KEY = "new-scenario";

    @Bean
    public Queue scenarioQueue() {
        return new Queue(ZUCCHINI_INCOMING_QUEUE, true);
    }

    @Bean
    public TopicExchange scenarioExchange() {
        return new TopicExchange(ZUCCHINI_INCOMING_EXCHANGE);
    }

    @Bean
    public Binding scenarioBinding(
            Queue scenarioQueue,
            TopicExchange scenarioExchange
    ) {
        return BindingBuilder.bind(scenarioQueue).to(scenarioExchange).with(NEW_SCENARIO_ROUTING_KEY);
    }

    @Bean
    SimpleMessageListenerContainer incomingZucchiniMessagesContainer(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter incomingZucchiniMessagesListener
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(
                ZUCCHINI_INCOMING_QUEUE
        );
        container.setMessageListener(incomingZucchiniMessagesListener);
        return container;
    }

    @Bean
    MessageListenerAdapter incomingZucchiniMessagesListener(ScenarioConsumer scenarioConsumer) {
        return new MessageListenerAdapter(scenarioConsumer, "receiveMessage");
    }
}

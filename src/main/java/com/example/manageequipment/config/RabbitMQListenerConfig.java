package com.example.manageequipment.config;

import com.example.manageequipment.consumer.PushNotificationConsume;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQListenerConfig {


    private PushNotificationConsume pushNotificationConsume;

    public RabbitMQListenerConfig(PushNotificationConsume pushNotificationConsume) {
        this.pushNotificationConsume = pushNotificationConsume;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(RabbitConfig.getConnection());
        container.setQueueNames("push_notification_queue"); // Queue(s) to listen to
        container.setMessageListener(pushNotificationConsume);

        container.stop();

        return container;
    }
}

package com.example.manageequipment.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbit.queue.save_request}")
    public String save_request_queue;

    @Value("${rabbit.exchange.manage_equipment}")
    public String save_request_exchange;

    @Value("${rabbit.routing.save_request}")
    public String save_request_routingKey;

    @Value("${rabbit.queue.push_notification}")
    public String push_notification_queue;

    @Value("${rabbit.routing.push_notification}")
    public String push_notification_routingKey;

    @Bean
    public Queue save_request_queue() {
        return new Queue(save_request_queue);
    }

    @Bean
    public Queue push_notification_queue() {
        return new Queue(push_notification_queue);
    }

    @Bean
    public TopicExchange save_request_exchange() {
        return new TopicExchange(save_request_exchange);
    }

    @Bean
    public Binding binding_save_request() {
        return BindingBuilder.bind(save_request_queue())
                .to(save_request_exchange())
                .with(save_request_routingKey);
    }

    @Bean
    public Binding binding_push_notification() {
        return BindingBuilder.bind(push_notification_queue())
                .to(save_request_exchange())
                .with(push_notification_routingKey);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate getTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }


//    @Bean
//    public CachingConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost("armadillo-01.rmq.cloudamqp.com");
//        connectionFactory.setPort(5672);
//        connectionFactory.setUsername("ynpktdgu:ynpktdgu");
//        connectionFactory.setPassword("3sU73Ks3p2X7Yld2gqyP5GblOTpCDWR1");
//        connectionFactory.setVirtualHost("ynpktdgu");
//        return connectionFactory;
//
//    }
}

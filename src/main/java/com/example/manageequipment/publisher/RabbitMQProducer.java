package com.example.manageequipment.publisher;

import com.example.manageequipment.config.RabbitMQConfig;
import com.example.manageequipment.dto.NotificationDto;
import com.example.manageequipment.dto.RequestDto;
import com.example.manageequipment.model.Role;
import com.example.manageequipment.model.User;
import com.example.manageequipment.repository.RoleCustomRepo;
import com.example.manageequipment.repository.UserRepository;
import com.example.manageequipment.service.FCMService;
import com.example.manageequipment.service.NotificationService;
import com.example.manageequipment.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RabbitMQProducer {
    @Value("${rabbit.exchange.manage_equipment}")
    private String exchange;

    @Value("${rabbit.routing.push_notification}")
    private String push_notification_routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    @Autowired
    private RoleCustomRepo roleCustomRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private FCMService fcmService;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(RequestDto message) {
//        requestService.createRequestEquipment(message);
//
//        rabbitTemplate.convertAndSend(exchange, rabbitMQConfig.push_notification_routingKey, message);


        CachingConnectionFactory connectionFactory=new CachingConnectionFactory ("armadillo.rmq.cloudamqp.com");
        connectionFactory.setUsername("ynpktdgu");
        connectionFactory.setPassword("3sU73Ks3p2X7Yld2gqyP5GblOTpCDWR1");
        connectionFactory.setVirtualHost("ynpktdgu");
        //Set up the listener
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(connectionFactory);
        //Send a message

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());

        template.convertAndSend("equipment_exchange", "push_notification_key", message);
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        container.stop();
    }
}

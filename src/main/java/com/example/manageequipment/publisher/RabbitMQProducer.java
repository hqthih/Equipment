package com.example.manageequipment.publisher;

import com.example.manageequipment.config.RabbitConfig;
import com.example.manageequipment.dto.RequestDto;
import com.example.manageequipment.dto.TypeNotification;
import com.example.manageequipment.service.RequestService;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;

@Service
public class RabbitMQProducer {
    
    @Autowired
    private RequestService requestService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createRequestAndSendNotification(RequestDto message) {
        System.out.println("connection from RabbitMQProducer");
        requestService.createRequestEquipment(message);

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        TypeNotification typeNotification = new TypeNotification().builder().type("REQUEST").content(message).build();

        rabbitTemplate.convertAndSend("equipment_exchange", "push_notification_key", typeNotification);
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void confirmRequestAndSendNotification(Long requestId) {
        requestService.confirmRequestEquipment(requestId);

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        TypeNotification typeNotification = new TypeNotification().builder().type("CONFIRM").content(requestId).build();

        rabbitTemplate.convertAndSend("equipment_exchange", "push_notification_key", typeNotification);
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void rejectRequestAndSendNotification(Long requestId) {
        requestService.rejectRequestEquipment(requestId);

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        TypeNotification typeNotification = new TypeNotification().builder().type("REJECT").content(requestId).build();

        rabbitTemplate.convertAndSend("equipment_exchange", "push_notification_key", typeNotification);
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

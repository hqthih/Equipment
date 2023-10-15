package com.example.manageequipment.publisher;

import com.example.manageequipment.config.RabbitConfig;
import com.example.manageequipment.dto.RequestDto;
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

    public void sendMessage(RequestDto message) {
        System.out.println("connection from RabbitMQProducer");
        requestService.createRequestEquipment(message);

        ConnectionFactory connectionFactory = RabbitConfig.getConnection();
        //Set up the listener
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(connectionFactory);
        //Send a message

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());

        template.convertAndSend("equipment_exchange", "push_notification_key", message);
//        try{
//            Thread.sleep(1000);
//        } catch(InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }

        template.stop();
        container.stop();
    }
}

//package com.example.manageequipment.publisher;
//
//import com.example.manageequipment.dto.RequestDto;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RabbitMQProducer {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void sendMessage(RequestDto message) {
////        requestService.createRequestEquipment(message);
////
////        rabbitTemplate.convertAndSend(exchange, rabbitMQConfig.push_notification_routingKey, message);
//
//
//        CachingConnectionFactory connectionFactory=new CachingConnectionFactory ("armadillo.rmq.cloudamqp.com");
//        connectionFactory.setUsername("ynpktdgu");
//        connectionFactory.setPassword("3sU73Ks3p2X7Yld2gqyP5GblOTpCDWR1");
//        connectionFactory.setVirtualHost("ynpktdgu");
//        //Set up the listener
//        SimpleMessageListenerContainer container =
//                new SimpleMessageListenerContainer(connectionFactory);
//        //Send a message
//
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//
//        template.convertAndSend("equipment_exchange", "push_notification_key", message);
//        try{
//            Thread.sleep(1000);
//        } catch(InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        container.stop();
//    }
//}

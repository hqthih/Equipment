package com.example.manageequipment.config;


import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

//    @Bean
//    public void messageListenerContainer() {
//        CachingConnectionFactory connectionFactory=new CachingConnectionFactory("bunny.cloudamqp.com");
//        connectionFactory.setUsername("rozcdysg");
//        connectionFactory.setPassword("Mx9GntDW4WBJvmY2_M_Qr2_a4gRGc3_G");
//        connectionFactory.setVirtualHost("rozcdysg");
//
//        SimpleMessageListenerContainer container =
//                new SimpleMessageListenerContainer(connectionFactory);
//        Object listener = new Object() {
//            @Bean
//            public void handleMessage(String foo) {
//                System.out.println(foo);
//            }
//        };
//
//        //Send a message
//        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
//        container.setMessageListener(adapter);
//        container.setQueueNames("push_notification_queue");
//        container.start();
//    }

    @Bean
    public static ConnectionFactory getConnection() {

        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        // set up the connection
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory ("armadillo.rmq.cloudamqp.com");
        connectionFactory.setUsername("ynpktdgu");
        connectionFactory.setPassword("3sU73Ks3p2X7Yld2gqyP5GblOTpCDWR1");
        connectionFactory.setVirtualHost("ynpktdgu");

        //Recommended settings
        connectionFactory.setRequestedHeartBeat(30);
        connectionFactory.setConnectionTimeout(30000);

        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        Queue queue = new Queue("push_notification_queue");
        admin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange("equipment_exchange");
        admin.declareExchange(exchange);
        admin.declareBinding(
                BindingBuilder.bind(queue).to(exchange).with("push_notification_key"));

        return connectionFactory;
    }
}

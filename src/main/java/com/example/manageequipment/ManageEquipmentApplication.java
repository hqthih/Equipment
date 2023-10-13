package com.example.manageequipment;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.manageequipment.model.Role;
import com.example.manageequipment.model.User;
import com.example.manageequipment.service.UserService;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class ManageEquipmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageEquipmentApplication.class, args);

    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dxnftdew6",
                "api_key", "759429928931825",
                "api_secret", "wvoc0pWqfthuGnyT1bWgCMafv8U",
                "secure", true));
        return cloudinary;
    }


//    @Bean
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                ConnectionFactory connectionFactory = new ConnectionFactory();
//                connection = connectionFactory.newConnection("amqp://guest:guest@localhost:5672/");
//            } catch (IOException | TimeoutException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//
//
//    @Bean
//    public static void createExchange() throws IOException, TimeoutException {
//
//        ConnectionFactory factory = new ConnectionFactory();
//        Connection connection = factory.newConnection("amqp://guest:guest@localhost:5672/");
//        Channel channel = connection.createChannel();
//
//        channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.DIRECT, true);
//        channel.close();
//        connection.close();
//    }
//
//    @Bean
//    public static void createQueue() throws IOException, TimeoutException {
//
//        ConnectionFactory factory = new ConnectionFactory();
//        Connection connection = factory.newConnection("amqps://inevcdrz:0k3hTtvlLOk9lNlpA4Ni1P72ZIuquqNK@armadillo.rmq.cloudamqp.com/inevcdrz");
//        Channel channel = connection.createChannel();
//
//        //Create the Queues
//        channel.queueDeclare("MobileQ", true, false, false, null);
//        channel.queueDeclare("ACQ", true, false, false, null);
//        channel.queueDeclare("LightQ", true, false, false, null);
//
//        channel.close();
//        connection.close();
//    }
//
//    @Bean
//    public static void createBinding() throws IOException, TimeoutException {
//
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        Connection connection = connectionFactory.newConnection("amqps://inevcdrz:0k3hTtvlLOk9lNlpA4Ni1P72ZIuquqNK@armadillo.rmq.cloudamqp.com/inevcdrz");
//        try (Channel channel = connection.createChannel()) {
//            //Create bindings - (queue, exchange, routingKey)
//            channel.queueBind("MobileQ", "my-direct-exchange", "personalDevice");
//            channel.queueBind("ACQ", "my-direct-exchange", "homeAppliance");
//            channel.queueBind("LightQ", "my-direct-exchange", "homeAppliance");
//        }
//        connection.close();
//    }


    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(Role.builder().name("ADMIN").build());
//            userService.saveRole(Role.builder().name("USER").build());
//
//            userService.saveUser(User.builder()
//                    .firstName("ha quoc")
//                    .lastName("dat")
//                    .address("648 tay son")
//                    .email("hqdat0809@gmail.com")
//                    .password("0392338494")
//                    .build());
//            userService.saveUser(User.builder()
//                    .firstName("ha quoc")
//                    .lastName("dat")
//                    .address("648 tay son")
//                    .email("hqdat08092001@gmail.com")
//                    .password("0392338494")
//                    .build());
//
//            userService.addRoleToStudent("hqdat0809@gmail.com", "ADMIN");
//            userService.addRoleToStudent("hqdat08092001@gmail.com", "USER");
//        };
//    }


}

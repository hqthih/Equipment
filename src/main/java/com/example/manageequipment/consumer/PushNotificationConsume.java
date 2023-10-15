package com.example.manageequipment.consumer;

import com.example.manageequipment.config.RabbitConfig;
import com.example.manageequipment.dto.NotificationDto;
import com.example.manageequipment.model.Role;
import com.example.manageequipment.model.User;
import com.example.manageequipment.repository.RoleCustomRepo;
import com.example.manageequipment.repository.UserRepository;
import com.example.manageequipment.service.FCMService;
import com.example.manageequipment.service.NotificationService;
import com.example.manageequipment.service.RequestService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PushNotificationConsume implements MessageListener {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RoleCustomRepo roleCustomRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private FCMService fcmService;

    private RabbitTemplate rabbitTemplate;

    public PushNotificationConsume(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("connection from Consume");
        Role role = roleCustomRepo.findByName("ADMIN").get();

        List<User> listUserAdmin = userRepository.findByRole(role);

        listUserAdmin.forEach(user -> {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setDescription("You have new request from user!");
            notificationDto.setUserOwnerId(user.getId());
            notificationDto.setRead(false);
            notificationDto.setType("REQUEST");
            notificationDto.setCreatedAt(new Date());

            notificationService.createNotification(notificationDto);
            try {
                fcmService.sendFCMNotification(user.getDeviceToken());
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

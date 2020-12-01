package com.rabbitmqapp.rabbitmqapp.services;

import com.rabbitmqapp.rabbitmqapp.configs.MessagingConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Reciever {
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void listen(String in) {
        System.out.println("Message read from "+MessagingConfig.QUEUE+" : " + in);
    }
}

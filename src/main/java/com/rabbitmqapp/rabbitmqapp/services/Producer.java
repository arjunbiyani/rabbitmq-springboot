package com.rabbitmqapp.rabbitmqapp.services;

import com.rabbitmqapp.rabbitmqapp.configs.MessagingConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    @Autowired
    private RabbitTemplate template;
    public void sendString(String text,Integer delay){
        template.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,text, message -> {
            message.getMessageProperties().setHeader("x-delay", delay);
            return message;
        });
    }
}
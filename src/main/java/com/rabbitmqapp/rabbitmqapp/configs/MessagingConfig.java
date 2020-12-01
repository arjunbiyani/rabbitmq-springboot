package com.rabbitmqapp.rabbitmqapp.configs;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {

    public static final String QUEUE = "quename";
    public static final String EXCHANGE = "exchnagename";
    public static final String ROUTING_KEY = "delay";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE,true,false,false);
    }

    @Bean
    CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean
    Binding binding(Queue queue, Exchange delayExchange) {
        return BindingBuilder.bind(queue).to(delayExchange).with(ROUTING_KEY).noargs();
    }
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
package com.rabbitmqapp.rabbitmqapp.controllers;

import com.rabbitmqapp.rabbitmqapp.services.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {
    @Autowired
    private Producer producer;
    @GetMapping(value = "/publish/{url}/{delay}")
    public String post (@PathVariable String url, @PathVariable int delay){
        producer.sendString(url,delay);
        return "Published Message...";
    }
}
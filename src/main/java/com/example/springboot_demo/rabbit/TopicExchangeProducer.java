package com.example.springboot_demo.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicExchangeProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg ,String routingKey){
        rabbitTemplate.convertAndSend("boot-topic-exchange", routingKey, msg);
    }
}

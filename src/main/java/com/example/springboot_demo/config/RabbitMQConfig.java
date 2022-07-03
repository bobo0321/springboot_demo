package com.example.springboot_demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange("boot-topic-exchange", true, false);
    }

    @Bean
    public Queue getQueue(){
        return new Queue("boot-queue", true, false, false, null);
    }

    @Bean
    public Binding getBinding(TopicExchange topicExchange, Queue queue){
        return BindingBuilder.bind(queue).to(topicExchange).with("*.red.*");
    }
}

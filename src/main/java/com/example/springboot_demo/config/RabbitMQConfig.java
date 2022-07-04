package com.example.springboot_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange("boot-topic-exchange", true, false);
    }

    @Bean
    public DirectExchange getDirectExchange() {
        return new DirectExchange("fan.test", true, false);
    }

    @Bean(value = "queue1")
    public Queue getQueue1(){
        return new Queue("boot-queue", true, false, false, null);
    }

    @Bean(value = "queue2")
    public Queue getQueue2() {
        return new Queue("fan.test", true,false,false,null);
    }

    @Bean
    public Binding getBinding(TopicExchange topicExchange, @Qualifier(value="queue1") Queue queue){
        return BindingBuilder.bind(queue).to(topicExchange).with("*.red.*");
    }

    @Bean
    public Binding getBinding2(DirectExchange directExchange, @Qualifier(value="queue2") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("fan.routing");
    }
}

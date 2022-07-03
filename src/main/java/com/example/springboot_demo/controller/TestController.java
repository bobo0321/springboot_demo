package com.example.springboot_demo.controller;

import com.example.springboot_demo.rabbit.TopicExchangeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fan/test")
public class TestController {

    @Autowired
    private TopicExchangeProducer topicExchangeProducer;

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @GetMapping("/rabbit")
    public String rabbit(){
        topicExchangeProducer.send("hahahaha", "com.red.fan");
        return null;
    }
}

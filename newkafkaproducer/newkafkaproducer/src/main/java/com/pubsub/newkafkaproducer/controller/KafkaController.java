package com.pubsub.newkafkaproducer.controller;

import com.pubsub.newkafkaproducer.model.Course;
import com.pubsub.newkafkaproducer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaService service;

    @PostMapping("/send")
    public String send(@RequestBody Course course){
        service.send(course);
        return "Sent";
    }
}

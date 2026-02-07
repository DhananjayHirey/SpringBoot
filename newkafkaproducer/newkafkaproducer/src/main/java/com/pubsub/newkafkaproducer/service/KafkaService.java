package com.pubsub.newkafkaproducer.service;

import com.pubsub.newkafkaproducer.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Course> kafkaTemplate;

    public void send(Course course){
        System.out.println("PRODUCER sending: " + course);
        kafkaTemplate.send("demo-topic", course);
    }
}
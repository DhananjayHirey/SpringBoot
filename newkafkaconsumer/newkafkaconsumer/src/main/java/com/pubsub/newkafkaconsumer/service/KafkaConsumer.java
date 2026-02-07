package com.pubsub.newkafkaconsumer.service;

import com.pubsub.newkafkaconsumer.model.Course;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "demo-topic")
    public void consume(Course course){
        System.out.println("CONSUMER received: " + course);
    }
}
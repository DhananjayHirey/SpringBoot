package com.pubsub.kafkaproducer.service;


import com.pubsub.kafkaproducer.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Course>kafkaTemplate;

    public String sendMessage(Course course){
        System.out.println("================================");
        System.out.println("Sending Course: " + course);
        System.out.println("Course ID: " + course.getCourseId());
        System.out.println("Course Title: " + course.getTitle());
        System.out.println("Course Trainer: " + course.getTrainer());
        System.out.println("Course Price: " + course.getPrice());
        System.out.println("================================");
        
        try {
            kafkaTemplate.send("customTopic","course",course);
            return "Course message sent to kafka server";
        } catch (Exception e) {
            System.out.println("Error sending message: " + e.getMessage());
            e.printStackTrace();
            return "Error sending message: " + e.getMessage();
        }
    }
}

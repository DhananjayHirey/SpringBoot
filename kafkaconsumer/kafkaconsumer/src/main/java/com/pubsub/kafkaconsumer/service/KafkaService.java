package com.pubsub.kafkaconsumer.service;


import com.pubsub.kafkaconsumer.model.Course;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class KafkaService {


    private volatile Course lastCourse;

    @PostConstruct
    public void init() {
        System.out.println("KafkaService instance: " + this.hashCode());
        System.out.println("KafkaService initialized and ready to listen!");
    }

    @KafkaListener(topics = "customTopic", groupId = "customConsumerGroup")
    public void consume(@Payload Course course, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("================================");
        System.out.println("Message received from partition: " + partition);
        System.out.println("Raw Received: " + course);
        System.out.println("Course ID: " + course.getCourseId());
        System.out.println("Course Title: " + course.getTitle());
        System.out.println("Course Trainer: " + course.getTrainer());
        System.out.println("Course Price: " + course.getPrice());
        System.out.println("================================");
        lastCourse = course;
    }

}

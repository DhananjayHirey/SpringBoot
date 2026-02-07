package com.pubsub.kafkaconsumer.controller;

import com.pubsub.kafkaconsumer.model.Course;
import com.pubsub.kafkaconsumer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaService service;

    @GetMapping("/addCourse")
    public ResponseEntity<?> getCourse() throws InterruptedException {

        for(int i = 0; i < 30; i++){
            Course c = service.getLastCourse();
            if(c != null){
                return ResponseEntity.ok(c);
            }
            Thread.sleep(100);
        }

        return ResponseEntity.ok("Kafka not consumed yet");
    }
}

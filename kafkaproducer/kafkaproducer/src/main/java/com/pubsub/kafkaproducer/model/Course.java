package com.pubsub.kafkaproducer.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {
    private String courseId;
    private String title;
    private String trainer;
    private double price;

}

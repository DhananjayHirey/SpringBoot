package com.tutorial.springsecurity.controller;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    @GetMapping
    public ResponseEntity<String>simpleResponse(){
        return new ResponseEntity<>("This is it", HttpStatus.CREATED);
    }
}

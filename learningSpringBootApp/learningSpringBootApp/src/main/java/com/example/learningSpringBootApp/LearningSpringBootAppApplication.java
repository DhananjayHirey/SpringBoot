package com.example.learningSpringBootApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningSpringBootAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringBootAppApplication.class, args);
	}

//	@Autowired
	private final PaymentService paymentService ;

//	if using normal constructor based dependency injection, then it is a good practise to add final after private in the above line
	public LearningSpringBootAppApplication(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public void run(String... args) throws Exception {
		String payment =	paymentService.pay();
		System.out.println("Payment done: "+payment);

	}
}

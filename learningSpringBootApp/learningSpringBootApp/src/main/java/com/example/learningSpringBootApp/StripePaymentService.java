package com.example.learningSpringBootApp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

//@Component
//@Service
//@RestController
//@Repository
//all 4 mean the same thing which help convert the class to bean

@Component
@ConditionalOnProperty(name = "payment.provider",havingValue = "stripe")
public class StripePaymentService implements PaymentService{

    @Override
    public String pay() {
        String payment = "Stripe Payment";
        System.out.println("Paying from..."+payment);
        return payment;
    }
}

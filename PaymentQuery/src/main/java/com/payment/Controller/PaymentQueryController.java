package com.payment.Controller;

import com.payment.Entities.Payment;
import com.payment.Service.PaymentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentQueryController {

    @Autowired
    private PaymentQueryService paymentQueryService;

    @GetMapping
    public List<Payment> getAllPaymentDetails(){
        return paymentQueryService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable long id){
        Payment payment= paymentQueryService.getPaymentById(id);
        if(payment!=null)
            return new ResponseEntity<>(payment, HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

}

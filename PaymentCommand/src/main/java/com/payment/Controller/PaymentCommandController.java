package com.payment.Controller;

import com.payment.Entities.Payment;
import com.payment.Service.PaymentCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentCommandController {

    @Autowired
    private PaymentCommandService paymentCommandService;

    @PostMapping("/create")
    public ResponseEntity createPayment(@RequestBody Payment payment){
        Payment savedPayment= paymentCommandService.savePayment(payment);
        if(savedPayment!=null)
            return new ResponseEntity(savedPayment, HttpStatus.CREATED);
        return new ResponseEntity(new RuntimeException("Unable to create Payment Record in Database"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/update")
    public ResponseEntity updatePayment(@RequestBody Payment payment){
        Payment updatedPayment= paymentCommandService.updatePayment(payment);
        if(updatedPayment!=null)
            return new ResponseEntity("Payment Updated Successfully", HttpStatus.OK);
        return new ResponseEntity(new RuntimeException("Unable to update payment in Database"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

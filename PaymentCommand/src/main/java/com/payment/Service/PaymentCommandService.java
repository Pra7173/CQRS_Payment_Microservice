package com.payment.Service;

import com.payment.Entities.Payment;
import com.payment.DTO.PaymentEvent;
import com.payment.Repository.PaymentCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentCommandService {
    @Autowired
    private PaymentCommandRepository paymentCommandRepository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public Payment savePayment(Payment payment) {
        Payment savedpayment= paymentCommandRepository.save(payment);
        PaymentEvent paymentEvent= new PaymentEvent("CreateEvent",savedpayment);
        kafkaTemplate.send("Payment-topic","create",paymentEvent);
        return savedpayment;
    }

    public Payment updatePayment(Payment payment) {
    Payment existingPayment= paymentCommandRepository.findById(payment.getId()).get();
    existingPayment.setPayment_to(payment.getPayment_to());
    existingPayment.setPayment_from(payment.getPayment_from());
    existingPayment.setAmount(payment.getAmount());
    Payment updatedPayment= paymentCommandRepository.save(existingPayment);
    PaymentEvent paymentEvent= new PaymentEvent("UpdateEvent",updatedPayment);
    kafkaTemplate.send("Payment-topic","update",paymentEvent);
    return updatedPayment;
    }

}

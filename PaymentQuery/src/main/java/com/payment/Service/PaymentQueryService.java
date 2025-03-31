package com.payment.Service;

import com.payment.Entities.Payment;
import com.payment.DTO.PaymentEvent;
import com.payment.Repository.PaymentQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentQueryService {

    @Autowired
    private PaymentQueryRepository paymentQueryRepository;

    public List<Payment> getAllPayments() {
        return paymentQueryRepository.findAll();
    }

    public Payment getPaymentById(long id) {
        return paymentQueryRepository.findById(id).get();
    }

    @RetryableTopic(attempts = "4", backoff = @Backoff(delay = 2000, multiplier = 1.5, maxDelay = 5000))
    @KafkaListener(topics = "Payment-topic", groupId = "Payment-Group1")
    public void processPaymentEvent(PaymentEvent paymentEvent) {
        try {
            Payment payment = paymentEvent.getPayment();
            String eventType = paymentEvent.getEventType();

            if (eventType.equalsIgnoreCase("CreateEvent")) {
                System.out.println("Create Event: " + payment);

                if (payment.getId() != null && paymentQueryRepository.existsById(payment.getId())) {
                    throw new IllegalStateException("Payment ID already exists: " + payment.getId());
                }

                paymentQueryRepository.save(payment);
            } else {
                System.out.println("Update Event: " + payment);
                Payment existingPayment = paymentQueryRepository.findById(payment.getId()).orElseThrow(
                        () -> new IllegalStateException("Payment ID not found: " + payment.getId())
                );

                existingPayment.setPayment_to(payment.getPayment_to());
                existingPayment.setPayment_from(payment.getPayment_from());
                existingPayment.setAmount(payment.getAmount());

                paymentQueryRepository.save(existingPayment);
            }

        } catch (ObjectOptimisticLockingFailureException ex) {
            ex.printStackTrace();
        }
    }

    @DltHandler
    public void listenDLT(PaymentEvent paymentEvent){
        System.out.println("DLT Received for Payment: "+ paymentEvent.getPayment().getId());
    }
}

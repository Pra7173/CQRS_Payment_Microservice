package com.payment.DTO;

import com.payment.Entities.Payment;

public class PaymentEvent {
    private String eventType;
    private Payment payment;
    public PaymentEvent(){}
    public PaymentEvent(String eventType, Payment payment) {
        this.eventType = eventType;
        this.payment = payment;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "PaymentEvent{" +
                "eventType='" + eventType + '\'' +
                ", payment=" + payment +
                '}';
    }
}

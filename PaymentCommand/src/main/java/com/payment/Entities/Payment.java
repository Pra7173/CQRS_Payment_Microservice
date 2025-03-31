package com.payment.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;

@Entity
@Table(name= "Payment_Command")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String payment_from;
    private String payment_to;
    private int amount;
    public Payment(){}
    public Payment(String payment_from, String payment_to, int amount) {

        this.payment_from = payment_from;
        this.payment_to = payment_to;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayment_from() {
        return payment_from;
    }

    public void setPayment_from(String payment_from) {
        this.payment_from = payment_from;
    }

    public String getPayment_to() {
        return payment_to;
    }

    public void setPayment_to(String payment_to) {
        this.payment_to = payment_to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



    @Override
    public String toString() {
        return "PaymentCommand{" +
                "id=" + id +
                ", payment_from='" + payment_from + '\'' +
                ", payment_to='" + payment_to + '\'' +
                ", amount=" + amount +
                '}';
    }
}

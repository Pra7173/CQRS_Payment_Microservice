package com.payment.Repository;

import com.payment.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCommandRepository extends JpaRepository<Payment,Long> {
}

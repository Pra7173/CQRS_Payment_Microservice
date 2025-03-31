package com.payment.Repository;

import com.payment.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentQueryRepository extends JpaRepository<Payment,Long> {
}

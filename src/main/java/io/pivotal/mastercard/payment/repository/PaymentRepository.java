package io.pivotal.mastercard.payment.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.mastercard.payment.entity.Payment;

/**
 * JPA repository for a Payment entity
 * @author Brian Jimerson
 *
 */
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}

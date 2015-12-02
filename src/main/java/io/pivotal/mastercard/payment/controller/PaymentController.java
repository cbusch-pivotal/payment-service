package io.pivotal.mastercard.payment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.mastercard.payment.entity.Payment;
import io.pivotal.mastercard.payment.repository.PaymentRepository;
import io.pivotal.mastercard.payment.service.MessagingService;
import io.pivotal.mastercard.payment.service.PaymentService;

/**
 * REST API controller for payments.
 * @author Brian Jimerson
 *
 */
@RestController
public class PaymentController {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	MessagingService messagingService;
	
	private static final Log log = LogFactory.getLog(PaymentController.class);
	
	/**
	 * Charges a payment to a card.
	 * @param payment The payment to charge.
	 * @return The payment passed, with the status of the charge set.
	 */
	@RequestMapping(value="/payment/", method=RequestMethod.POST)
	public @ResponseBody Payment chargePayment(@RequestBody Payment payment) {
		
		String result = paymentService.chargePayment(
				payment.getCardNumber(), 
				payment.getCvc(), 
				payment.getExpirationMonth(), 
				payment.getExpirationYear(), 
				payment.getAmount());
		payment.setStatus(result);
		payment = paymentRepository.save(payment);
		messagingService.sendMessage(payment);
		log.debug(String.format("Payment saved and put on queue = [%s]", payment));
		return payment;
		
	}
	
	/**
	 * Action to initiate shutdown of the system.  In CF, the application 
	 * <em>should</em>f restart.  In other environments, the application
	 * runtime will be shut down.
	 */
	@RequestMapping(value = "/kill", method = RequestMethod.GET)
	public void kill() {
		
		log.warn("*** The Payment app instance is shutting down. ***");
		System.exit(-1);		
	}

}

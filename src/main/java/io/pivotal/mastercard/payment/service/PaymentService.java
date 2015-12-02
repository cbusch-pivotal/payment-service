package io.pivotal.mastercard.payment.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.simplify.payments.PaymentsApi;
import com.simplify.payments.PaymentsMap;
import com.simplify.payments.domain.Payment;
import com.simplify.payments.exception.InvalidRequestException;

/**
 * Service to handle payment operations.
 * @author Brian Jimerson
 *
 */
@Service
@ConfigurationProperties(prefix="simplify.api")
public class PaymentService {

	private static final String CURRENCY = "USD";
	private static final Log log = LogFactory.getLog(PaymentService.class);
	
	private String privateKey;
	private String publicKey;
		
	/**
	 * Gets the private API key.
	 * @return The private API key.
	 */
	public String getPrivateKey() {
		return privateKey;
	}
	
	/**
	 * Sets the private API key.
	 * @param privateKey The private API key to set.
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	/**
	 * Gets the public API key.
	 * @return The public API key.
	 */
	public String getPublicKey() {
		return publicKey;
	}
	
	/**
	 * Sets the public API key.
	 * @param publicKey The public API key to set.
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	public String chargePayment(
			String cardNumber, 
			String cvc, 
			Integer expirationMonth, 
			Integer expirationYear, 
			Double amount) {
		
		PaymentsApi.PUBLIC_KEY = this.getPublicKey();
		PaymentsApi.PRIVATE_KEY = this.getPrivateKey();
		
		Long amountInCents = amount != null ? Math.round(amount * 100) : 0;
		PaymentsMap paymentsMap = new PaymentsMap();
		paymentsMap.set("currency", CURRENCY);
		paymentsMap.set("card.cvc", cvc);
		paymentsMap.set("card.number", cardNumber);
		paymentsMap.set("card.expMonth", expirationMonth);
		paymentsMap.set("card.expYear", expirationYear);
		paymentsMap.set("amount", amountInCents);
		
		Payment payment = null;
		try {
			payment = Payment.create(paymentsMap);
		} catch (InvalidRequestException ire) {
			return ire.getFieldErrors().toString();
		} catch (Exception e) {
			log.error("Error processing payment", e);
		} 
		return payment.get("paymentStatus").toString();
		
	}

}

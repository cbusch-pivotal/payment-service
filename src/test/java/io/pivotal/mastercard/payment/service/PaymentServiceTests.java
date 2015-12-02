package io.pivotal.mastercard.payment.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.pivotal.mastercard.payment.PaymentServiceApplication;
import junit.framework.TestCase;

/**
 * Test case for the Payment Service
 * @author Brian Jimerson
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {PaymentServiceApplication.class})
public class PaymentServiceTests {

	private static final Log log = LogFactory.getLog(PaymentServiceTests.class);
	
	@Autowired
	PaymentService paymentService;
	
	/**
	 * Tests the charge payment method of the Payment Service.
	 */
	@Test
	public void testChargePayment() {
		String result = paymentService.chargePayment(
				"5105105105105100", "123", 8, 20, 50.00d);
		log.info(String.format("Result received from charge payment = [%s]", result));
		TestCase.assertEquals("APPROVED", result);
	}
}

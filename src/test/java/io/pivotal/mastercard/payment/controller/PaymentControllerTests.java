package io.pivotal.mastercard.payment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.pivotal.mastercard.payment.PaymentServiceApplication;
import io.pivotal.mastercard.payment.entity.Payment;
import junit.framework.TestCase;

/**
 * Test case for the PaymentController class.
 * 
 * @author Brian Jimerson
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {PaymentServiceApplication.class})
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PaymentControllerTests {
	
	private static final Log log = LogFactory.getLog(PaymentControllerTests.class);
	
	@Autowired
	EmbeddedWebApplicationContext server;
	
	MockMvc mvc;
	
	/**
	 * Sets up the test cases
	 */
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(server).build();
	}
	
	/**
	 * Tests the controller's payment charge method
	 */
	@Test
	public void testPaymentCharge() {
/*		
		Payment payment = new Payment();
		payment.setCardNumber("5105105105105100");
		payment.setCvc("123");
		payment.setExpirationMonth(8);
		payment.setExpirationYear(17);
		payment.setAmount(50.00);
		payment.setDescription("Test");
		
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.post("/payment/")
					.contentType("application/json")
					.content(new ObjectMapper().writeValueAsString(payment))
					)
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());
		}
*/
	}

}

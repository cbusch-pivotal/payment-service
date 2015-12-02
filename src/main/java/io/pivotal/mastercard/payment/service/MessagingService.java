package io.pivotal.mastercard.payment.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service class to send messages to a queue.
 * @author Brian Jimerson
 *
 */
@Service
public class MessagingService {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	private static final Log log = LogFactory.getLog(MessagingService.class);
	
	public void sendMessage(Object message) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonMessage = null;
		try {
			jsonMessage = mapper.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			log.error(e);
		}
		rabbitTemplate.convertAndSend("payment-queue", jsonMessage);
	}
}

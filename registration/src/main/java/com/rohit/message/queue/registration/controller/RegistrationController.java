package com.rohit.message.queue.registration.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rohit.message.queue.classes.constants.QueueConstants;
import com.rohit.message.queue.classes.dto.Message;
import com.rohit.message.queue.registration.service.RegistrationMessageProducerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/exchange")
public class RegistrationController {

	private RegistrationMessageProducerService producerService;
	
	public RegistrationController( RegistrationMessageProducerService producerService ) {
		this.producerService = producerService;
	}
	
	@PostMapping("/rpcClient")
	public String rpcClient( String message ) {
		return producerService.rpcClientCall( message );
	}

	// default exchange
	@PostMapping("/default")
	public String defaultMessageExchange( Message message) {
		return producerService.sendMessage(message);
	}
	
	// direct exchange key city
	@PostMapping("/direct/city")
	public String directMessageExchangeCity( String message ) {
		return producerService.directExchangeUsingKey(message, QueueConstants.CITY);
	}
	
	// direct exchange key lake
	@PostMapping("/direct/lake")
	public String directMessageExchangeLake( String message ) {
		return producerService.directExchangeUsingKey(message, QueueConstants.LAKE);
	}
	
	// fanout exchange
	@PostMapping("/fanout")
	public String sendNews( String message) {
		return producerService.broadcastMessage(message);
	}
	
	// topic exchange one word extensions
	@PostMapping("/topic/city")
	public String topicExchangeMessageCity( String message ) {
		// only one word can be added after city to define topic 
		// try adding more it will send it to multi-word consumer queue
		return producerService.topicExchangeMessage(message,"topic.one");
	}
	
	// topic exchange multi-word extensions
	@PostMapping("/topic/Lake")
	public String topicExchangeMessageLake( String message) {
		// zero or more word can be added to define topic after lake
		return producerService.topicExchangeMessage(message,"topic.one.two");
	}
	
	// headers exchange extensions
	@PostMapping("/headers")
	public String topicExchangeMessageLake(String hotelName, String message) throws JsonProcessingException {
		return producerService.headersExchangeMessage(message,hotelName);
	}
}

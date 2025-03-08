package com.rohit.message.queue.registration.service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohit.message.queue.classes.constants.QueueConstants;
import com.rohit.message.queue.classes.dto.Message;

@Service
public class RegistrationMessageProducerService {

	private RabbitTemplate rabbitTemplate;
	
	public RegistrationMessageProducerService( RabbitTemplate rabbitTemplate ) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	//	default exchange
	public String sendMessage(Message message) {
		if(message.getHotelId() == 1) {
			rabbitTemplate.convertAndSend("hotel-city-view", message);
			System.out.println("message sent city-view");
		}else if(message.getHotelId() == 2) {
			rabbitTemplate.convertAndSend("hotel-lake-view", message);
			System.out.println("message sent lake-view");
		}
		return "message sent via default exchange";
	}
	
	//	direct exchange
	public String directExchangeUsingKey(String message, String key) {
		rabbitTemplate.convertAndSend(QueueConstants.DIRECT_EXCHANGE, key, message);
		return "message routed to key : "+key;
		
	}

	//  fanout exchange
	public String broadcastMessage(String message) {
		rabbitTemplate.convertAndSend(QueueConstants.FANOUT_EXCHANGE,"", message);
		return "message broadcasted to fanout queues";
	}
	
	// topic exchange
	public String topicExchangeMessage(String message, String topic) {
		rabbitTemplate.convertAndSend(QueueConstants.TOPIC_EXCHANGE, topic, message);
		return "topic message sent for topic : "+topic;
		
	}

	// headers exchange
	public String headersExchangeMessage(String message, String hotelName) throws JsonProcessingException {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("hotel",hotelName);

		org.springframework.amqp.core.Message amqpMessage = new org.springframework.amqp.core.Message(new ObjectMapper().writeValueAsBytes(message), messageProperties);
		rabbitTemplate.convertAndSend(QueueConstants.HEADERS_EXCHANGE, "", amqpMessage);
		return "message send to headers : "+hotelName;
	}

	// RPC call using queue
	public String rpcClientCall(String message) {
		
		// RPC call to lake
		String lakeReport = (String) rabbitTemplate.convertSendAndReceive(QueueConstants.RPC_QUEUE_LAKE, message);
		
		// RPC call to city
		String cityReport = (String) rabbitTemplate.convertSendAndReceive(QueueConstants.RPC_QUEUE_CITY, message);
		
		return new StringBuilder().append("Lake report : "+lakeReport)
				.append("\n")
				.append("City report : "+cityReport)
				.toString();
	}
	
}

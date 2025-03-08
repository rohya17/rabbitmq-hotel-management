package com.rohit.message.queue.hotel_city_view.service;

import java.util.stream.IntStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rohit.message.queue.classes.constants.QueueConstants;
import com.rohit.message.queue.classes.dto.Message;

@Service
public class CityMessageConsumer {

	// default exchange consumer
	@RabbitListener(queues = QueueConstants.CITY_VIEW)
	public void consumeMessage(Message message) {
		System.out.println(message.toString());
	}
	
	// direct exchange consumer
	@RabbitListener(queues = QueueConstants.DIRECT_EXCHANGE_QUEUE_CITY)
	public void directExchangeMessage(String message) {
		System.out.println("Direct Exchange City : "+message);
	}
	
	// fanout exchange consumer
	@RabbitListener(queues = QueueConstants.BROADCAST_QUEUE_TWO)
	public void consumeFanoutExchangeMessage(String message) {
		System.out.println("Fanout Exchange City : "+message);
	}
	
	// topic exchange consumer
	@RabbitListener(queues = QueueConstants.TOPIC_QUEUE_CITY )
	public void topicExchangeMessage(String message) {
		System.out.println("Topic Exchange City : "+message);
	}
	
	// headers exchange consumer
	@RabbitListener(queues = QueueConstants.HEADERS_EXCHANGE_QUEUE_CITY )
	public void headersExchangeMessage(String message) {
		System.out.println("Headers Exchange City : "+message);
	}
	
	// RPC exchange consumer
	@RabbitListener(queues = QueueConstants.RPC_QUEUE_CITY )
	public String rpcReceiver(String message) {
		
		StringBuffer report = new StringBuffer("Room Report : ");
		if( message.equalsIgnoreCase("generate report") ) {
			IntStream.rangeClosed(1, 10).forEach(index -> report.append("["+index+"]").append(index % 2 == 0 ? "occupied " : "vacant ") );	
		}
		return report.toString();
	}
}

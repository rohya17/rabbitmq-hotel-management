package com.rohit.message.queue.hotel_lake_view.service;

import java.util.stream.IntStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rohit.message.queue.classes.constants.QueueConstants;
import com.rohit.message.queue.classes.dto.Message;

@Service
public class LakeMessageConsumer {
	
	// default exchange consumer
	@RabbitListener(queues = QueueConstants.LAKE_VIEW)
	public void consumeMessage(Message message) {
		System.out.println(message.toString());
	}
	
	// direct exchange consumer
	@RabbitListener(queues = QueueConstants.DIRECT_EXCHANGE_QUEUE_LAKE)
	public void directExchangeMessage(String message) {
		System.out.println("Direct Exchange Lake : "+message);
	}
	
	// fanout exchange consumer
	@RabbitListener(queues = QueueConstants.BROADCAST_QUEUE_ONE)
	public void consumeFanoutExchangeMessage(String message) {
		System.out.println("Fanout Exchange Lake : "+message);
	}
	
	// topic exchange consumer
	@RabbitListener(queues = QueueConstants.TOPIC_QUEUE_LAKE )
	public void topicExchangeMessage(String message) {
		System.out.println("Topic Exchange Lake : "+message);
	}
	
	// headers exchange consumer
	@RabbitListener(queues = QueueConstants.HEADERS_EXCHANGE_QUEUE_LAKE )
	public void headersExchangeMessage(String message) {
		System.out.println("Headers Exchange Lake : "+message);
	}
	
	// RPC exchange consumer
	@RabbitListener(queues = QueueConstants.RPC_QUEUE_LAKE )
	public String rpcReceiver(String message) {
		
		StringBuffer report = new StringBuffer("Room Report : ");
		if( message.equalsIgnoreCase("generate report") ) {
			IntStream.rangeClosed(1, 10).forEach(index -> report.append("["+index+"]").append(index % 2 == 0 ? "vacant " : "occupied ") );	
		}
		return report.toString();
	}
	
}

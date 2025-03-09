package com.rohit.message.queue.classes.constants;

public class QueueConstants {
	
	// RPC remote procedure call
	public static final String RPC_QUEUE_LAKE = "rpc-queue-lake";
	public static final String RPC_QUEUE_CITY = "rpc-queue-city";
	
	// default exchange
	public static final String LAKE_VIEW = "hotel-lake-view";
	public static final String CITY_VIEW = "hotel-city-view";
	
	// direct exchange
	public static final String CITY = "city";
	public static final String LAKE = "lake";
	public static final String DIRECT_EXCHANGE = "direct-exchange";
	public static final String DIRECT_EXCHANGE_QUEUE_CITY = "direct-exchange-queue-city";
	public static final String DIRECT_EXCHANGE_QUEUE_LAKE = "direct-exchange-queue-lake";
	
	// fanout exchange
	public static final String FANOUT_EXCHANGE = "fanout-exchange";
	public static final String BROADCAST_QUEUE_ONE = "broadcast-queue-one";
	public static final String BROADCAST_QUEUE_TWO = "broadcast-queue-two";
	
	// topic exchange
	public static final String TOPIC_EXCHANGE = "topic-exchange";
	public static final String TOPIC_QUEUE_CITY = "topic-queue-city";
	public static final String TOPIC_QUEUE_LAKE = "topic-queue-lake";
	public static final String TOPIC_ONE_WORD = "topic.*";
	public static final String TOPIC_MULTI_WORD = "topic.#";
	
	// headers exchange
	public static final String HEADERS_EXCHANGE = "headers-exchange";
	public static final String HEADERS_EXCHANGE_QUEUE_CITY = "headers-exchange-queue-city";
	public static final String HEADERS_EXCHANGE_QUEUE_LAKE = "headers-exchange-queue-lake";
	
}

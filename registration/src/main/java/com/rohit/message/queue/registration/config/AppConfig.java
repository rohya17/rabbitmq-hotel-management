package com.rohit.message.queue.registration.config;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.rohit.message.queue.classes.constants.QueueConstants;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfig {
	
	@Value("${server.address}")
	String serverHost;
	
	@Value("${server.port}")
	String serverPort;
	
	// [ RPC ] remote procedure call
	@Bean
	Queue rpcQueueLake() {
		return new Queue(QueueConstants.RPC_QUEUE_LAKE);
	}
	
	@Bean
	Queue rpcQueueCity() {
		return new Queue(QueueConstants.RPC_QUEUE_CITY);
	}

	// default exchange queues  ============================================================================
    @Bean
    Queue registrationLakeQueue() {
		return new Queue(QueueConstants.LAKE_VIEW,true);
	}
    
    @Bean
    Queue registrationCityQueue() {
		return new Queue(QueueConstants.CITY_VIEW,true);
	}
    
    // Direct exchange queues ============================================================================
    @Bean
    DirectExchange directExchange() {
    	return new DirectExchange(QueueConstants.DIRECT_EXCHANGE);
    }
    
    @Bean
    Queue directExchangeQueueCity() {
    	return new Queue(QueueConstants.DIRECT_EXCHANGE_QUEUE_CITY,true);
    }
    
    @Bean
    Queue directExchangeQueueLake() {
    	return new Queue(QueueConstants.DIRECT_EXCHANGE_QUEUE_LAKE,true);
    }
    
    @Bean 
    @ConditionalOnBean(name = {"directExchangeQueueCity","directExchange"})
    Binding directExchangeBindingCity( Queue directExchangeQueueCity, DirectExchange directExchange ) {
    	return BindingBuilder.bind(directExchangeQueueCity).to(directExchange).with(QueueConstants.CITY);
    }
    
    @Bean 
    @ConditionalOnBean(name = {"directExchangeQueueLake","directExchange"})
    Binding directExchangeBindingLake( Queue directExchangeQueueLake, DirectExchange directExchange ) {
    	return BindingBuilder.bind(directExchangeQueueLake).to(directExchange).with(QueueConstants.LAKE);
    }
    
    // Fanout exchange queues  ============================================================================
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(QueueConstants.FANOUT_EXCHANGE);
    }
    
    @Bean
    Queue broadCastQueueOne() {
		return new Queue(QueueConstants.BROADCAST_QUEUE_ONE,true);
	}
    
    @Bean
    Queue broadCastQueueTwo() {
		return new Queue(QueueConstants.BROADCAST_QUEUE_TWO,true);
	}
    
    @Bean
    @ConditionalOnBean(name = {"broadCastQueueOne","fanoutExchange"})
    Binding fanoutBindingOne(Queue broadCastQueueOne, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(broadCastQueueOne).to(fanoutExchange);
    }
    
    @Bean
    @ConditionalOnBean(name = {"broadCastQueueTwo","fanoutExchange"})
    Binding fanoutBindingTwo(Queue broadCastQueueTwo, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(broadCastQueueTwo).to(fanoutExchange);
    }
	
    // Topic exchange queues ==================================================================================
    @Bean
    TopicExchange topicExchange() {
    	return new TopicExchange(QueueConstants.TOPIC_EXCHANGE);
    }
    
    @Bean 
    Queue topicQueueCity() {
    	return new Queue(QueueConstants.TOPIC_QUEUE_CITY,true);
    }
    
    @Bean 
    Queue topicQueueLake() {
    	return new Queue(QueueConstants.TOPIC_QUEUE_LAKE,true);
    }
    
    @Bean // topic.* (one word after "topic.", ex. "topic.city")
    @ConditionalOnBean(name = {"topicQueueCity","topicExchange"})
    Binding topicBindingCity( Queue topicQueueCity, TopicExchange topicExchange) {
    	return BindingBuilder.bind(topicQueueCity).to(topicExchange).with(QueueConstants.TOPIC_ONE_WORD);
    }
    
    @Bean // topic.# (zero or more words after "topic.", ex. "topic.lake1.lake2")
    @ConditionalOnBean(name = {"topicQueueLake","topicExchange"})
    Binding topicBindingLake( Queue topicQueueLake, TopicExchange topicExchange) {
    	return BindingBuilder.bind(topicQueueLake).to(topicExchange).with(QueueConstants.TOPIC_MULTI_WORD);
    }
    
    // header exchange queues ==================================================================================
    @Bean
    HeadersExchange headersExchange() {
    	return new HeadersExchange(QueueConstants.HEADERS_EXCHANGE);
    }
    
    @Bean
    Queue headersExchangeQueueCity() {
    	return new Queue(QueueConstants.HEADERS_EXCHANGE_QUEUE_CITY,true);
    }
    
    @Bean
    Queue headersExchangeQueueLake() {
    	return new Queue(QueueConstants.HEADERS_EXCHANGE_QUEUE_LAKE,true);
    }
    
    @Bean
    @ConditionalOnBean(name = {"headersExchangeQueueCity","headersExchange"})
    Binding headersBindingCity( Queue headersExchangeQueueCity, HeadersExchange headersExchange) {
    	return BindingBuilder.bind(headersExchangeQueueCity).to(headersExchange)
    			.whereAll(Map.of("hotel", "city")).match();
    }
    
    @Bean
    @ConditionalOnBean(name = {"headersExchangeQueueLake","headersExchange"})
    Binding headersBindingLake( Queue headersExchangeQueueLake, HeadersExchange headersExchange) {
    	return BindingBuilder.bind(headersExchangeQueueLake).to(headersExchange)
    			.whereAll(Map.of("hotel", "lake")).match();
    }
    
 // other beans ===========================================================================================
 	@Bean
 	MessageConverter jsonMesageConverter() {
 		return new Jackson2JsonMessageConverter();
 	}
 	
 	
 	// rabbitmq template
 	@Bean
 	RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
 		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
 		rabbitTemplate.setMessageConverter(jsonMesageConverter());
 		return rabbitTemplate;
 	}
 	
 	@Bean
     OpenAPI defineOpenApi() {
 	   Server server = new Server();
 	   server.setUrl( String.format("http://%s:%s", serverHost, serverPort) );
 	   server.setDescription("Development");
 	
 	   Contact myContact = new Contact();
 	   myContact.setName("Rohit Thorave");
 	   myContact.setEmail("rohitthorave17895@gmail.com");
 	
 	   Info information = new Info()
 	           .title("Registration Server API's")
 	           .version("1.0")
 	           .description("This API exposes endpoints to email API")
 	           .contact(myContact);
 	   return new OpenAPI().info(information).servers(List.of(server));
 	}
}

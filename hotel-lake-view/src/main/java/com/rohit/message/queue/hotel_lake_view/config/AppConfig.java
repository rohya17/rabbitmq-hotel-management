package com.rohit.message.queue.hotel_lake_view.config;

import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfig {

//    @Bean
//    Queue registrationQueue() {
//		return new Queue("hotel-lake-view");
//	}
	
	@Bean
	MessageConverter jsonMesageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMesageConverter());
		return rabbitTemplate;
	}
	
//	@Bean
//    OpenAPI defineOpenApi() {
//	   Server server = new Server();
//	   server.setUrl("http://localhost:8082");
//	   server.setDescription("Development");
//	
//	   Contact myContact = new Contact();
//	   myContact.setName("Rohit Thorave");
//	   myContact.setEmail("rohitthorave17895@gmail.com");
//	
//	   Info information = new Info()
//	           .title("Registration Server API's")
//	           .version("1.0")
//	           .description("This API exposes endpoints to email API")
//	           .contact(myContact);
//	   return new OpenAPI().info(information).servers(List.of(server));
//	}
}

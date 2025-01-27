package com.rohit.message.queue.mail_server.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfig {

	@Bean
    OpenAPI defineOpenApi() {
	   Server server = new Server();
	   server.setUrl("http://localhost:8084");
	   server.setDescription("Development");
	
	   Contact myContact = new Contact();
	   myContact.setName("Rohit Thorave");
	   myContact.setEmail("rohitthorave17895@gmail.com");
	
	   Info information = new Info()
	           .title("Email Server API's")
	           .version("1.0")
	           .description("This API exposes endpoints to email API")
	           .contact(myContact);
	   return new OpenAPI().info(information).servers(List.of(server));
	}
}

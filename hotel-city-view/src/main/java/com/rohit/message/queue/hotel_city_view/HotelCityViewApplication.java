package com.rohit.message.queue.hotel_city_view;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class HotelCityViewApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelCityViewApplication.class, args);
	}
	
	@Bean
    OpenAPI defineOpenApi() {
	   Server server = new Server();
	   server.setUrl("http://localhost:8082");
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

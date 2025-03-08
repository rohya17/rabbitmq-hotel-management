package com.rohit.message.queue.hotel_lake_view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class HotelLakeViewApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelLakeViewApplication.class, args);

	}
	
	@Bean
    OpenAPI defineOpenApi() {
	   Server server = new Server();
	   server.setUrl("http://localhost:8081");
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

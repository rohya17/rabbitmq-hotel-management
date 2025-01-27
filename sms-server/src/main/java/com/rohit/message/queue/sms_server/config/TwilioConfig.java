package com.rohit.message.queue.sms_server.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.annotation.PostConstruct;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }
    
    @Bean
    OpenAPI defineOpenApi() {
	   Server server = new Server();
	   server.setUrl("http://localhost:8085");
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

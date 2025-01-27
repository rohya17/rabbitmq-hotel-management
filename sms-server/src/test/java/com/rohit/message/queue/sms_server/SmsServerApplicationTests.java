package com.rohit.message.queue.sms_server;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.rohit.message.queue.sms_server.service.SmsService;

@SpringBootTest
class SmsServerApplicationTests {

	@Test
	void contextLoads() {
		
		SmsService smsService = spy(new SmsService());
		
		// Act: Call the sendSms method with test data
        String toPhoneNumber = "+1234567890";
        String messageBody = "Test SMS Message";
        String response = smsService.sendSms(toPhoneNumber, messageBody);

        // Assert: Validate that the response (message ID) is not null
        assertNotNull(response, "The response (message ID) should not be null");
        
        // Verify: Ensure the log was triggered
        verify(smsService).sendSms(toPhoneNumber, messageBody);
	}

}

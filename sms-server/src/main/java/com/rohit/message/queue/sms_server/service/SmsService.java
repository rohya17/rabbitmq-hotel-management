package com.rohit.message.queue.sms_server.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.phone-number}")
    private String fromPhoneNumber;
    
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    public String sendSms(String toPhoneNumber, String messageBody) {
        
    	/*
        Message message = Message.creator(
                new PhoneNumber(toPhoneNumber),   // To phone number
                new PhoneNumber(fromPhoneNumber), // From Twilio phone number
                messageBody                       // Message body
        ).create();

        return message.getSid(); // Return message SID for tracking
        */
    	
        // Simulate SMS sending
        logger.info("Simulating sending SMS to {} with message: {}", toPhoneNumber, messageBody);

        // Return a fake message ID for tracking
        return "mock-sms-id-" + System.currentTimeMillis();
    }
}

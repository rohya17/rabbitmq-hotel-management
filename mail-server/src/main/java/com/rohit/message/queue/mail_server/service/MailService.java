package com.rohit.message.queue.mail_server.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rohit.message.queue.mail_server.models.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.xml.bind.PropertyException;

@Service
public class MailService {
	
	@Autowired private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}") private String sender;
	@Value("${email.template.location}") private String emailTemplateLocation;

	public ResponseEntity<String> sendEmail( EmailDetails emailDetails ) throws MessagingException, PropertyException, IOException{
		
		// Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom( sender );
        mimeMessageHelper.setTo( emailDetails.getRecipents() );
//        mimeMessageHelper.setCc( emailDetails.getCc() == null ? "" : emailDetails.getCc() );
//        mimeMessageHelper.setBcc( emailDetails.getBcc() == null ? "" : emailDetails.getBcc() );
        mimeMessageHelper.setSubject( emailDetails.getSubject() );
        
        if(emailDetails.getMessageBody() == null || emailDetails.getMessageBody().isEmpty()) {
        	
        	if( emailTemplateLocation.isEmpty() ) {
        		throw new PropertyException("Property not defined : emailTemplateLocation");
        	}
        	if( !emailTemplateLocation.endsWith(File.separator) ) {
        		emailTemplateLocation = emailTemplateLocation.concat(File.separator);
        	}
        	HashMap<String, String> emailParams = emailDetails.getParams();
        	String emailTemplateName = emailParams.get("email-template-name");
        	String fileContent = Files.readString(Path.of(emailTemplateLocation.concat(emailTemplateName)));
        	
        	for (String key : emailParams.keySet()) {
        		String replace = String.format("${%s}", key);
        		fileContent = fileContent.replace(replace, emailParams.get(key));
			}
        	
        	mimeMessageHelper.setText(fileContent, true);
        }else {
        	mimeMessageHelper.setText( emailDetails.getMessageBody() );
        }
        
        // Adding the attachment
        Resource file = emailDetails.getAttachment();
        if(file != null) {
        	mimeMessageHelper.addAttachment( file.getFilename(), file );	
        }

        // Sending the mail
        javaMailSender.send(mimeMessage);
		
		return ResponseEntity.ok("Mail sent successfully.");
		
	}
}

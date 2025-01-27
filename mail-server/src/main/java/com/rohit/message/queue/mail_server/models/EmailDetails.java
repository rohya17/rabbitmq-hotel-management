package com.rohit.message.queue.mail_server.models;
import java.util.HashMap;

import org.springframework.core.io.Resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmailDetails {


	private String recipents;
	private String cc;
	private String bcc;
	private String subject;
	private String messageBody;
	private Resource attachment;
	private HashMap<String, String> params;

	public String getRecipents() {
		return recipents;
	}

	public void setRecipents(String recipents) {
		this.recipents = recipents;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public Resource getAttachment() {
		return attachment;
	}

	public void setAttachment(Resource attachment) {
		this.attachment = attachment;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	
}

package com.rohit.message.queue.sms_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rohit.message.queue.sms_server.service.SmsService;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public String sendSms(@RequestParam String to, @RequestParam String message) {
        return smsService.sendSms(to, message);
    }
}


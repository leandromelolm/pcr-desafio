package com.lm.pcr.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "env.telesign")
public class PhoneMessage {

    private String customerId;
    private String apiKey;
    private String phoneNumber;
    private String message;
    private String messageType;
    private String restEndpoint;

    public PhoneMessage() {}

    public PhoneMessage(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getRestEndpoint() {
        return restEndpoint;
    }

    public void setRestEndpoint(String restEndpoint) {
        this.restEndpoint = restEndpoint;
    } 
}

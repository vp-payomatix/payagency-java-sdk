package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CryptoPaymentLinkOutput {
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private String data; // The payment link URL

    public CryptoPaymentLinkOutput() {}

    // Getters
    public String getMessage() { return message; }
    public String getData() { return data; }

    // Setters for Jackson
    public void setMessage(String message) { this.message = message; }
    public void setData(String data) { this.data = data; }
}

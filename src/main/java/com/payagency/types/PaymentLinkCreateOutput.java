package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response for payment link creation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentLinkCreateOutput {
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private String data;

    // Default constructor
    public PaymentLinkCreateOutput() {}

    // Getters and setters
    public String getMessage() { return message; }
    public String getData() { return data; }

    public void setMessage(String message) { this.message = message; }
    public void setData(String data) { this.data = data; }
}

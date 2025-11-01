package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoPayinOutput {
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("redirect_url")
    private String redirectUrl;
    
    @JsonProperty("data")
    private CryptoPayinData data;

    public static class CryptoPayinData {
        @JsonProperty("amount")
        private Integer amount;
        
        @JsonProperty("currency")
        private String currency;
        
        @JsonProperty("order_id")
        private String orderId;
        
        @JsonProperty("transaction_id")
        private String transactionId;
        
        @JsonProperty("crypto_currency")
        private String cryptoCurrency;
        
        @JsonProperty("customer")
        private Customer customer;

        public static class Customer {
            @JsonProperty("first_name")
            private String firstName;
            
            @JsonProperty("last_name")
            private String lastName;
            
            @JsonProperty("email")
            private String email;

            public Customer() {}

            // Getters
            public String getFirstName() { return firstName; }
            public String getLastName() { return lastName; }
            public String getEmail() { return email; }

            // Setters for Jackson
            public void setFirstName(String firstName) { this.firstName = firstName; }
            public void setLastName(String lastName) { this.lastName = lastName; }
            public void setEmail(String email) { this.email = email; }
        }

        public CryptoPayinData() {}

        // Getters
        public Integer getAmount() { return amount; }
        public String getCurrency() { return currency; }
        public String getOrderId() { return orderId; }
        public String getTransactionId() { return transactionId; }
        public String getCryptoCurrency() { return cryptoCurrency; }
        public Customer getCustomer() { return customer; }

        // Setters for Jackson
        public void setAmount(Integer amount) { this.amount = amount; }
        public void setCurrency(String currency) { this.currency = currency; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
        public void setCryptoCurrency(String cryptoCurrency) { this.cryptoCurrency = cryptoCurrency; }
        public void setCustomer(Customer customer) { this.customer = customer; }
    }

    public CryptoPayinOutput() {}

    // Getters
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public String getRedirectUrl() { return redirectUrl; }
    public CryptoPayinData getData() { return data; }

    // Setters for Jackson
    public void setStatus(String status) { this.status = status; }
    public void setMessage(String message) { this.message = message; }
    public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }
    public void setData(CryptoPayinData data) { this.data = data; }
}

package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response for crypto OffRamp operations.
 */
public class CryptoOffRampOutput {
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("redirect_url")
    private String redirectUrl;
    
    @JsonProperty("data")
    private CryptoOffRampData data;

    // Default constructor
    public CryptoOffRampOutput() {}

    // Getters and setters
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public String getRedirectUrl() { return redirectUrl; }
    public CryptoOffRampData getData() { return data; }

    public void setStatus(String status) { this.status = status; }
    public void setMessage(String message) { this.message = message; }
    public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }
    public void setData(CryptoOffRampData data) { this.data = data; }

    public static class CryptoOffRampData {
        @JsonProperty("transaction_id")
        private String transactionId;
        
        @JsonProperty("order_id")
        private String orderId;
        
        @JsonProperty("fiat")
        private String fiat;
        
        @JsonProperty("fiat_amount")
        private Integer fiatAmount;
        
        @JsonProperty("crypto")
        private String crypto;
        
        @JsonProperty("crypto_amount")
        private String cryptoAmount;
        
        @JsonProperty("customer")
        private Customer customer;

        // Default constructor
        public CryptoOffRampData() {}

        // Getters and setters
        public String getTransactionId() { return transactionId; }
        public String getOrderId() { return orderId; }
        public String getFiat() { return fiat; }
        public Integer getFiatAmount() { return fiatAmount; }
        public String getCrypto() { return crypto; }
        public String getCryptoAmount() { return cryptoAmount; }
        public Customer getCustomer() { return customer; }

        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public void setFiat(String fiat) { this.fiat = fiat; }
        public void setFiatAmount(Integer fiatAmount) { this.fiatAmount = fiatAmount; }
        public void setCrypto(String crypto) { this.crypto = crypto; }
        public void setCryptoAmount(String cryptoAmount) { this.cryptoAmount = cryptoAmount; }
        public void setCustomer(Customer customer) { this.customer = customer; }

        public static class Customer {
            @JsonProperty("first_name")
            private String firstName;
            
            @JsonProperty("last_name")
            private String lastName;
            
            @JsonProperty("email")
            private String email;

            // Default constructor
            public Customer() {}

            // Getters and setters
            public String getFirstName() { return firstName; }
            public String getLastName() { return lastName; }
            public String getEmail() { return email; }

            public void setFirstName(String firstName) { this.firstName = firstName; }
            public void setLastName(String lastName) { this.lastName = lastName; }
            public void setEmail(String email) { this.email = email; }
        }
    }
}

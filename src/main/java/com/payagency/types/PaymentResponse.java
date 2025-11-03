package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response payload for payment operations (S2S, Hosted, APM).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("message") 
    private String message;
    
    @JsonProperty("data")
    private PaymentData data;
    
    @JsonProperty("redirect_url")
    private String redirectUrl;

    // Default constructor
    public PaymentResponse() {}

    // Getters
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public PaymentData getData() { return data; }
    public String getRedirectUrl() { return redirectUrl; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setMessage(String message) { this.message = message; }
    public void setData(PaymentData data) { this.data = data; }
    public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }

    /**
     * Check if the payment was successful.
     */
    public boolean isSuccess() {
        return "SUCCESS".equals(status);
    }

    /**
     * Check if the payment requires a redirect.
     */
    public boolean isRedirect() {
        return "REDIRECT".equals(status);
    }

    /**
     * Check if the payment failed.
     */
    public boolean isFailed() {
        return "FAILED".equals(status);
    }

    public static class PaymentData {
        @JsonProperty("amount")
        private Integer amount;
        
        @JsonProperty("currency")
        private String currency;
        
        @JsonProperty("order_id")
        private String orderId;
        
        @JsonProperty("transaction_id")
        private String transactionId;
        
        @JsonProperty("customer")
        private Customer customer;
        
        @JsonProperty("refund")
        private RefundInfo refund;
        
        @JsonProperty("chargeback")
        private ChargebackInfo chargeback;

        // Default constructor
        public PaymentData() {}

        // Getters
        public Integer getAmount() { return amount; }
        public String getCurrency() { return currency; }
        public String getOrderId() { return orderId; }
        public String getTransactionId() { return transactionId; }
        public Customer getCustomer() { return customer; }
        public RefundInfo getRefund() { return refund; }
        public ChargebackInfo getChargeback() { return chargeback; }

        // Setters
        public void setAmount(Integer amount) { this.amount = amount; }
        public void setCurrency(String currency) { this.currency = currency; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
        public void setCustomer(Customer customer) { this.customer = customer; }
        public void setRefund(RefundInfo refund) { this.refund = refund; }
        public void setChargeback(ChargebackInfo chargeback) { this.chargeback = chargeback; }
    }

    public static class Customer {
        @JsonProperty("first_name")
        private String firstName;
        
        @JsonProperty("last_name")
        private String lastName;
        
        @JsonProperty("email")
        private String email;

        // Default constructor
        public Customer() {}

        // Getters
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getEmail() { return email; }

        // Setters
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setEmail(String email) { this.email = email; }
    }

    public static class RefundInfo {
        @JsonProperty("status")
        private boolean status;
        
        @JsonProperty("refund_date")
        private String refundDate;

        // Default constructor
        public RefundInfo() {}

        // Getters
        public boolean isStatus() { return status; }
        public String getRefundDate() { return refundDate; }

        // Setters
        public void setStatus(boolean status) { this.status = status; }
        public void setRefundDate(String refundDate) { this.refundDate = refundDate; }
    }

    public static class ChargebackInfo {
        @JsonProperty("status")
        private boolean status;
        
        @JsonProperty("chargeback_date")
        private String chargebackDate;

        // Default constructor
        public ChargebackInfo() {}

        // Getters
        public boolean isStatus() { return status; }
        public String getChargebackDate() { return chargebackDate; }

        // Setters
        public void setStatus(boolean status) { this.status = status; }
        public void setChargebackDate(String chargebackDate) { this.chargebackDate = chargebackDate; }
    }
}

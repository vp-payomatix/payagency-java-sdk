package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Input payload for refund operations.
 */
public class RefundInput {
    @JsonProperty("reason")
    private String reason;
    
    @JsonProperty("transaction_id")
    private String transactionId;
    
    @JsonProperty("amount")
    private String amount; // Optional: for partial refunds

    // Private constructor for builder
    private RefundInput(Builder builder) {
        this.reason = builder.reason;
        this.transactionId = builder.transactionId;
        this.amount = builder.amount;
    }

    // Default constructor for Jackson
    public RefundInput() {}

    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public String getReason() { return reason; }
    public String getTransactionId() { return transactionId; }
    public String getAmount() { return amount; }

    // Setters for Jackson
    public void setReason(String reason) { this.reason = reason; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public void setAmount(String amount) { this.amount = amount; }

    public static class Builder {
        private String reason;
        private String transactionId;
        private String amount;

        public Builder reason(String reason) { this.reason = reason; return this; }
        public Builder transactionId(String transactionId) { this.transactionId = transactionId; return this; }
        public Builder amount(String amount) { this.amount = amount; return this; }

        public RefundInput build() {
            if (reason == null || reason.trim().isEmpty()) {
                throw new IllegalArgumentException("Reason is required for refund");
            }
            if (transactionId == null || transactionId.trim().isEmpty()) {
                throw new IllegalArgumentException("Transaction ID is required for refund");
            }
            return new RefundInput(this);
        }
    }
}

package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Input parameters for transaction queries.
 */
public class TransactionsInput {
    @JsonProperty("transaction_start_date")
    private String transactionStartDate;
    
    @JsonProperty("transaction_end_date")
    private String transactionEndDate;
    
    @JsonProperty("nextCursor")
    private String nextCursor;
    
    @JsonProperty("prevCursor")
    private String prevCursor;

    // Private constructor for builder
    private TransactionsInput(Builder builder) {
        this.transactionStartDate = builder.transactionStartDate;
        this.transactionEndDate = builder.transactionEndDate;
        this.nextCursor = builder.nextCursor;
        this.prevCursor = builder.prevCursor;
    }

    // Default constructor for Jackson
    public TransactionsInput() {}

    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public String getTransactionStartDate() { return transactionStartDate; }
    public String getTransactionEndDate() { return transactionEndDate; }
    public String getNextCursor() { return nextCursor; }
    public String getPrevCursor() { return prevCursor; }

    // Setters for Jackson
    public void setTransactionStartDate(String transactionStartDate) { this.transactionStartDate = transactionStartDate; }
    public void setTransactionEndDate(String transactionEndDate) { this.transactionEndDate = transactionEndDate; }
    public void setNextCursor(String nextCursor) { this.nextCursor = nextCursor; }
    public void setPrevCursor(String prevCursor) { this.prevCursor = prevCursor; }

    public static class Builder {
        private String transactionStartDate;
        private String transactionEndDate;
        private String nextCursor;
        private String prevCursor;

        public Builder transactionStartDate(String transactionStartDate) { this.transactionStartDate = transactionStartDate; return this; }
        public Builder transactionEndDate(String transactionEndDate) { this.transactionEndDate = transactionEndDate; return this; }
        public Builder nextCursor(String nextCursor) { this.nextCursor = nextCursor; return this; }
        public Builder prevCursor(String prevCursor) { this.prevCursor = prevCursor; return this; }

        public TransactionsInput build() {
            return new TransactionsInput(this);
        }
    }
}

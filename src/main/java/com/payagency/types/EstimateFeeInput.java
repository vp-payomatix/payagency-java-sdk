package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Input for estimating payout fees.
 */
public class EstimateFeeInput {
    @JsonProperty("wallet_id")
    private String walletId;
    
    @JsonProperty("amount")
    private Integer amount;
    
    @JsonProperty("card_number")
    private String cardNumber;

    // Private constructor for builder
    private EstimateFeeInput(Builder builder) {
        this.walletId = builder.walletId;
        this.amount = builder.amount;
        this.cardNumber = builder.cardNumber;
    }

    // Default constructor for Jackson
    public EstimateFeeInput() {}

    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public String getWalletId() { return walletId; }
    public Integer getAmount() { return amount; }
    public String getCardNumber() { return cardNumber; }

    // Setters for Jackson
    public void setWalletId(String walletId) { this.walletId = walletId; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public static class Builder {
        private String walletId;
        private Integer amount;
        private String cardNumber;

        public Builder walletId(String walletId) { this.walletId = walletId; return this; }
        public Builder amount(Integer amount) { this.amount = amount; return this; }
        public Builder cardNumber(String cardNumber) { this.cardNumber = cardNumber; return this; }

        public EstimateFeeInput build() {
            return new EstimateFeeInput(this);
        }
    }
}

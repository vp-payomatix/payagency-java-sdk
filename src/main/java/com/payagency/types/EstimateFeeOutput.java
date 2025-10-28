package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response for fee estimation.
 */
public class EstimateFeeOutput {
    @JsonProperty("data")
    private EstimateData data;

    // Default constructor
    public EstimateFeeOutput() {}

    // Getters and setters
    public EstimateData getData() { return data; }
    public void setData(EstimateData data) { this.data = data; }

    public static class EstimateData {
        @JsonProperty("amount_requried")
        private String amountRequired;
        
        @JsonProperty("wallet_balance")
        private String walletBalance;
        
        @JsonProperty("total_fee")
        private String totalFee;

        // Default constructor
        public EstimateData() {}

        // Getters and setters
        public String getAmountRequired() { return amountRequired; }
        public String getWalletBalance() { return walletBalance; }
        public String getTotalFee() { return totalFee; }

        public void setAmountRequired(String amountRequired) { this.amountRequired = amountRequired; }
        public void setWalletBalance(String walletBalance) { this.walletBalance = walletBalance; }
        public void setTotalFee(String totalFee) { this.totalFee = totalFee; }
    }
}

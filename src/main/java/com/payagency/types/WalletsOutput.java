package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response type for wallets query.
 */
public class WalletsOutput {
    @JsonProperty("data")
    private List<WalletInfo> data;

    // Default constructor
    public WalletsOutput() {}

    // Getters and setters
    public List<WalletInfo> getData() { return data; }
    public void setData(List<WalletInfo> data) { this.data = data; }

    public static class WalletInfo {
        @JsonProperty("wallet_id")
        private String walletId;
        
        @JsonProperty("currency")
        private String currency;
        
        @JsonProperty("amount")
        private String amount;
        
        @JsonProperty("payment_method")
        private String paymentMethod;
        
        @JsonProperty("status")
        private String status;

        // Default constructor
        public WalletInfo() {}

        // Getters and setters
        public String getWalletId() { return walletId; }
        public String getCurrency() { return currency; }
        public String getAmount() { return amount; }
        public String getPaymentMethod() { return paymentMethod; }
        public String getStatus() { return status; }

        public void setWalletId(String walletId) { this.walletId = walletId; }
        public void setCurrency(String currency) { this.currency = currency; }
        public void setAmount(String amount) { this.amount = amount; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public void setStatus(String status) { this.status = status; }
    }
}

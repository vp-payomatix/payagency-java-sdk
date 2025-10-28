package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoOffRampLinkInput {
    @JsonProperty("payment_template_id")
    private String paymentTemplateId;
    
    @JsonProperty("crypto_amount")
    private String cryptoAmount;
    
    @JsonProperty("crypto_currency")
    private String cryptoCurrency;
    
    @JsonProperty("fiat_currency")
    private String fiatCurrency;
    
    @JsonProperty("order_id")
    private String orderId; // Optional
    
    @JsonProperty("terminal_id")
    private String terminalId; // Optional
    
    @JsonProperty("expiry_date")
    private String expiryDate; // Optional

    public CryptoOffRampLinkInput() {}

    private CryptoOffRampLinkInput(Builder builder) {
        this.paymentTemplateId = builder.paymentTemplateId;
        this.cryptoAmount = builder.cryptoAmount;
        this.cryptoCurrency = builder.cryptoCurrency;
        this.fiatCurrency = builder.fiatCurrency;
        this.orderId = builder.orderId;
        this.terminalId = builder.terminalId;
        this.expiryDate = builder.expiryDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String paymentTemplateId;
        private String cryptoAmount;
        private String cryptoCurrency;
        private String fiatCurrency;
        private String orderId;
        private String terminalId;
        private String expiryDate;

        public Builder paymentTemplateId(String paymentTemplateId) {
            this.paymentTemplateId = paymentTemplateId;
            return this;
        }

        public Builder cryptoAmount(String cryptoAmount) {
            this.cryptoAmount = cryptoAmount;
            return this;
        }

        public Builder cryptoCurrency(String cryptoCurrency) {
            this.cryptoCurrency = cryptoCurrency;
            return this;
        }

        public Builder fiatCurrency(String fiatCurrency) {
            this.fiatCurrency = fiatCurrency;
            return this;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder terminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public Builder expiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public CryptoOffRampLinkInput build() {
            return new CryptoOffRampLinkInput(this);
        }
    }

    // Getters
    public String getPaymentTemplateId() { return paymentTemplateId; }
    public String getCryptoAmount() { return cryptoAmount; }
    public String getCryptoCurrency() { return cryptoCurrency; }
    public String getFiatCurrency() { return fiatCurrency; }
    public String getOrderId() { return orderId; }
    public String getTerminalId() { return terminalId; }
    public String getExpiryDate() { return expiryDate; }
}

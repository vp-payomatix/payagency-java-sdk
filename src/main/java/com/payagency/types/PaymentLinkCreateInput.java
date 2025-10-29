package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Input for creating payment links.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentLinkCreateInput {
    @JsonProperty("payment_template_id")
    private String paymentTemplateId;
    
    @JsonProperty("amount")
    private Integer amount;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("expiry_date")
    private String expiryDate;
    
    @JsonProperty("terminal_id")
    private String terminalId;
    
    @JsonProperty("order_id")
    private String orderId;

    // Private constructor for builder
    private PaymentLinkCreateInput(Builder builder) {
        this.paymentTemplateId = builder.paymentTemplateId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.expiryDate = builder.expiryDate;
        this.terminalId = builder.terminalId;
        this.orderId = builder.orderId;
    }

    // Default constructor for Jackson
    public PaymentLinkCreateInput() {}

    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public String getPaymentTemplateId() { return paymentTemplateId; }
    public Integer getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getExpiryDate() { return expiryDate; }
    public String getTerminalId() { return terminalId; }
    public String getOrderId() { return orderId; }

    // Setters for Jackson
    public void setPaymentTemplateId(String paymentTemplateId) { this.paymentTemplateId = paymentTemplateId; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public static class Builder {
        private String paymentTemplateId;
        private Integer amount;
        private String currency;
        private String expiryDate;
        private String terminalId;
        private String orderId;

        public Builder paymentTemplateId(String paymentTemplateId) { this.paymentTemplateId = paymentTemplateId; return this; }
        public Builder amount(Integer amount) { this.amount = amount; return this; }
        public Builder currency(String currency) { this.currency = currency; return this; }
        public Builder expiryDate(String expiryDate) { this.expiryDate = expiryDate; return this; }
        public Builder terminalId(String terminalId) { this.terminalId = terminalId; return this; }
        public Builder orderId(String orderId) { this.orderId = orderId; return this; }

        public PaymentLinkCreateInput build() {
            return new PaymentLinkCreateInput(this);
        }
    }
}

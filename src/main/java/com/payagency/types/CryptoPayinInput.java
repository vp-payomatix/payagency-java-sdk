package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoPayinInput {
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("amount")
    private Integer amount;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("crypto_currency")
    private String cryptoCurrency;
    
    @JsonProperty("ip_address")
    private String ipAddress;
    
    @JsonProperty("phone_number")
    private String phoneNumber;
    
    @JsonProperty("address")
    private String address;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("crypto_network")
    private String cryptoNetwork;
    
    @JsonProperty("redirect_url")
    private String redirectUrl;
    
    @JsonProperty("order_id")
    private String orderId; // Optional
    
    @JsonProperty("webhook_url")
    private String webhookUrl; // Optional
    
    @JsonProperty("terminal_id")
    private String terminalId; // Optional

    public CryptoPayinInput() {}

    private CryptoPayinInput(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.cryptoCurrency = builder.cryptoCurrency;
        this.ipAddress = builder.ipAddress;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.country = builder.country;
        this.cryptoNetwork = builder.cryptoNetwork;
        this.redirectUrl = builder.redirectUrl;
        this.orderId = builder.orderId;
        this.webhookUrl = builder.webhookUrl;
        this.terminalId = builder.terminalId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private Integer amount;
        private String currency;
        private String cryptoCurrency;
        private String ipAddress;
        private String phoneNumber;
        private String address;
        private String country;
        private String cryptoNetwork;
        private String redirectUrl;
        private String orderId;
        private String webhookUrl;
        private String terminalId;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder cryptoCurrency(String cryptoCurrency) {
            this.cryptoCurrency = cryptoCurrency;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder cryptoNetwork(String cryptoNetwork) {
            this.cryptoNetwork = cryptoNetwork;
            return this;
        }

        public Builder redirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder webhookUrl(String webhookUrl) {
            this.webhookUrl = webhookUrl;
            return this;
        }

        public Builder terminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public CryptoPayinInput build() {
            return new CryptoPayinInput(this);
        }
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public Integer getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getCryptoCurrency() { return cryptoCurrency; }
    public String getIpAddress() { return ipAddress; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getCountry() { return country; }
    public String getCryptoNetwork() { return cryptoNetwork; }
    public String getRedirectUrl() { return redirectUrl; }
    public String getOrderId() { return orderId; }
    public String getWebhookUrl() { return webhookUrl; }
    public String getTerminalId() { return terminalId; }
}

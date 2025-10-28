package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Input for crypto OffRamp operations (Crypto to Fiat).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoOffRampInput {
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;
    
    @JsonProperty("phone_number")
    private String phoneNumber;
    
    @JsonProperty("fiat_currency")
    private String fiatCurrency;
    
    @JsonProperty("crypto_currency")
    private String cryptoCurrency;
    
    @JsonProperty("wallet_address")
    private String walletAddress;
    
    @JsonProperty("ip_address")
    private String ipAddress;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("crypto_network")
    private String cryptoNetwork;
    
    @JsonProperty("redirect_url")
    private String redirectUrl;
    
    @JsonProperty("webhook_url")
    private String webhookUrl;
    
    @JsonProperty("order_id")
    private String orderId;
    
    @JsonProperty("terminal_id")
    private String terminalId;
    
    @JsonProperty("crypto_amount")
    private String cryptoAmount; // OffRamp uses crypto amount (String for precision)

    // Private constructor for builder
    private CryptoOffRampInput(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.fiatCurrency = builder.fiatCurrency;
        this.cryptoCurrency = builder.cryptoCurrency;
        this.walletAddress = builder.walletAddress;
        this.ipAddress = builder.ipAddress;
        this.email = builder.email;
        this.country = builder.country;
        this.cryptoNetwork = builder.cryptoNetwork;
        this.redirectUrl = builder.redirectUrl;
        this.webhookUrl = builder.webhookUrl;
        this.orderId = builder.orderId;
        this.terminalId = builder.terminalId;
        this.cryptoAmount = builder.cryptoAmount;
    }

    // Default constructor for Jackson
    public CryptoOffRampInput() {}

    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getFiatCurrency() { return fiatCurrency; }
    public String getCryptoCurrency() { return cryptoCurrency; }
    public String getWalletAddress() { return walletAddress; }
    public String getIpAddress() { return ipAddress; }
    public String getEmail() { return email; }
    public String getCountry() { return country; }
    public String getCryptoNetwork() { return cryptoNetwork; }
    public String getRedirectUrl() { return redirectUrl; }
    public String getWebhookUrl() { return webhookUrl; }
    public String getOrderId() { return orderId; }
    public String getTerminalId() { return terminalId; }
    public String getCryptoAmount() { return cryptoAmount; }

    // Setters for Jackson
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setFiatCurrency(String fiatCurrency) { this.fiatCurrency = fiatCurrency; }
    public void setCryptoCurrency(String cryptoCurrency) { this.cryptoCurrency = cryptoCurrency; }
    public void setWalletAddress(String walletAddress) { this.walletAddress = walletAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public void setEmail(String email) { this.email = email; }
    public void setCountry(String country) { this.country = country; }
    public void setCryptoNetwork(String cryptoNetwork) { this.cryptoNetwork = cryptoNetwork; }
    public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }
    public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }
    public void setCryptoAmount(String cryptoAmount) { this.cryptoAmount = cryptoAmount; }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String fiatCurrency;
        private String cryptoCurrency;
        private String walletAddress;
        private String ipAddress;
        private String email;
        private String country;
        private String cryptoNetwork;
        private String redirectUrl;
        private String webhookUrl;
        private String orderId;
        private String terminalId;
        private String cryptoAmount;

        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder fiatCurrency(String fiatCurrency) { this.fiatCurrency = fiatCurrency; return this; }
        public Builder cryptoCurrency(String cryptoCurrency) { this.cryptoCurrency = cryptoCurrency; return this; }
        public Builder walletAddress(String walletAddress) { this.walletAddress = walletAddress; return this; }
        public Builder ipAddress(String ipAddress) { this.ipAddress = ipAddress; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder country(String country) { this.country = country; return this; }
        public Builder cryptoNetwork(String cryptoNetwork) { this.cryptoNetwork = cryptoNetwork; return this; }
        public Builder redirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; return this; }
        public Builder webhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; return this; }
        public Builder orderId(String orderId) { this.orderId = orderId; return this; }
        public Builder terminalId(String terminalId) { this.terminalId = terminalId; return this; }
        public Builder cryptoAmount(String cryptoAmount) { this.cryptoAmount = cryptoAmount; return this; }

        public CryptoOffRampInput build() {
            return new CryptoOffRampInput(this);
        }
    }
}

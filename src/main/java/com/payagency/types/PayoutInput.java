package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Input payload for payout operations.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayoutInput {
    @JsonProperty("wallet_id")
    private String walletId;
    
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("address")
    private String address;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("city")
    private String city;
    
    @JsonProperty("state")
    private String state;
    
    @JsonProperty("zip")
    private String zip;
    
    @JsonProperty("ip_address")
    private String ipAddress;
    
    @JsonProperty("phone_number")
    private String phoneNumber;
    
    @JsonProperty("amount")
    private Integer amount;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("card_number")
    private String cardNumber;
    
    @JsonProperty("card_expiry_month")
    private String cardExpiryMonth;
    
    @JsonProperty("card_expiry_year")
    private String cardExpiryYear;
    
    @JsonProperty("webhook_url")
    private String webhookUrl;
    
    @JsonProperty("order_id")
    private String orderId;

    // Private constructor for builder
    private PayoutInput(Builder builder) {
        this.walletId = builder.walletId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.address = builder.address;
        this.country = builder.country;
        this.city = builder.city;
        this.state = builder.state;
        this.zip = builder.zip;
        this.ipAddress = builder.ipAddress;
        this.phoneNumber = builder.phoneNumber;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.cardNumber = builder.cardNumber;
        this.cardExpiryMonth = builder.cardExpiryMonth;
        this.cardExpiryYear = builder.cardExpiryYear;
        this.webhookUrl = builder.webhookUrl;
        this.orderId = builder.orderId;
    }

    // Default constructor for Jackson
    public PayoutInput() {}

    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public String getWalletId() { return walletId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZip() { return zip; }
    public String getIpAddress() { return ipAddress; }
    public String getPhoneNumber() { return phoneNumber; }
    public Integer getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getCardNumber() { return cardNumber; }
    public String getCardExpiryMonth() { return cardExpiryMonth; }
    public String getCardExpiryYear() { return cardExpiryYear; }
    public String getWebhookUrl() { return webhookUrl; }
    public String getOrderId() { return orderId; }

    // Setters for Jackson
    public void setWalletId(String walletId) { this.walletId = walletId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZip(String zip) { this.zip = zip; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public void setCardExpiryMonth(String cardExpiryMonth) { this.cardExpiryMonth = cardExpiryMonth; }
    public void setCardExpiryYear(String cardExpiryYear) { this.cardExpiryYear = cardExpiryYear; }
    public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public static class Builder {
        private String walletId;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String country;
        private String city;
        private String state;
        private String zip;
        private String ipAddress;
        private String phoneNumber;
        private Integer amount;
        private String currency;
        private String cardNumber;
        private String cardExpiryMonth;
        private String cardExpiryYear;
        private String webhookUrl;
        private String orderId;

        public Builder walletId(String walletId) { this.walletId = walletId; return this; }
        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder address(String address) { this.address = address; return this; }
        public Builder country(String country) { this.country = country; return this; }
        public Builder city(String city) { this.city = city; return this; }
        public Builder state(String state) { this.state = state; return this; }
        public Builder zip(String zip) { this.zip = zip; return this; }
        public Builder ipAddress(String ipAddress) { this.ipAddress = ipAddress; return this; }
        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder amount(Integer amount) { this.amount = amount; return this; }
        public Builder currency(String currency) { this.currency = currency; return this; }
        public Builder cardNumber(String cardNumber) { this.cardNumber = cardNumber; return this; }
        public Builder cardExpiryMonth(String cardExpiryMonth) { this.cardExpiryMonth = cardExpiryMonth; return this; }
        public Builder cardExpiryYear(String cardExpiryYear) { this.cardExpiryYear = cardExpiryYear; return this; }
        public Builder webhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; return this; }
        public Builder orderId(String orderId) { this.orderId = orderId; return this; }

        public PayoutInput build() {
            return new PayoutInput(this);
        }
    }
}

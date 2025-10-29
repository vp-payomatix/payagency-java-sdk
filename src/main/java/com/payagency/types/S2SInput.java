package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request payload for Server-to-Server (S2S) card payments.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class S2SInput {
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
    
    @JsonProperty("card_cvv")
    private String cardCvv;
    
    @JsonProperty("redirect_url")
    private String redirectUrl;
    
    @JsonProperty("webhook_url")
    private String webhookUrl;
    
    @JsonProperty("order_id")
    private String orderId;
    
    @JsonProperty("terminal_id")
    private String terminalId;

    // Private constructor for builder
    private S2SInput(Builder builder) {
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
        this.cardCvv = builder.cardCvv;
        this.redirectUrl = builder.redirectUrl;
        this.webhookUrl = builder.webhookUrl;
        this.orderId = builder.orderId;
        this.terminalId = builder.terminalId;
    }

    // Default constructor for Jackson
    public S2SInput() {}

    public static Builder builder() {
        return new Builder();
    }

    // Getters
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
    public String getCardCvv() { return cardCvv; }
    public String getRedirectUrl() { return redirectUrl; }
    public String getWebhookUrl() { return webhookUrl; }
    public String getOrderId() { return orderId; }
    public String getTerminalId() { return terminalId; }

    // Setters for Jackson
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
    public void setCardCvv(String cardCvv) { this.cardCvv = cardCvv; }
    public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }
    public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }

    public static class Builder {
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
        private String cardCvv;
        private String redirectUrl;
        private String webhookUrl;
        private String orderId;
        private String terminalId;

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
        public Builder cardCvv(String cardCvv) { this.cardCvv = cardCvv; return this; }
        public Builder redirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; return this; }
        public Builder webhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; return this; }
        public Builder orderId(String orderId) { this.orderId = orderId; return this; }
        public Builder terminalId(String terminalId) { this.terminalId = terminalId; return this; }

        public S2SInput build() {
            return new S2SInput(this);
        }
    }
}

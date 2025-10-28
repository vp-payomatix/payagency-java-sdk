package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response for transactions query.
 */
public class TransactionsOutput {
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private List<Transaction> data;
    
    @JsonProperty("meta")
    private PaginationMeta meta;

    // Default constructor
    public TransactionsOutput() {}

    // Getters and setters
    public String getMessage() { return message; }
    public List<Transaction> getData() { return data; }
    public PaginationMeta getMeta() { return meta; }

    public void setMessage(String message) { this.message = message; }
    public void setData(List<Transaction> data) { this.data = data; }
    public void setMeta(PaginationMeta meta) { this.meta = meta; }

    public static class Transaction {
        @JsonProperty("first_name")
        private String firstName;
        
        @JsonProperty("last_name")
        private String lastName;
        
        @JsonProperty("converted_amount")
        private String convertedAmount;
        
        @JsonProperty("converted_currency")
        private String convertedCurrency;
        
        @JsonProperty("transaction_id")
        private String transactionId;
        
        @JsonProperty("amount")
        private String amount;
        
        @JsonProperty("currency")
        private String currency;
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("card_type")
        private String cardType;
        
        @JsonProperty("card_number")
        private String cardNumber;
        
        @JsonProperty("transaction_type")
        private String transactionType;
        
        @JsonProperty("order_id")
        private String orderId;
        
        @JsonProperty("country")
        private String country;
        
        @JsonProperty("email")
        private String email;
        
        @JsonProperty("created_at")
        private String createdAt;
        
        @JsonProperty("transaction_date")
        private String transactionDate;
        
        @JsonProperty("chargeback_date")
        private String chargebackDate;
        
        @JsonProperty("refund_date")
        private String refundDate;
        
        @JsonProperty("suspicious_date")
        private String suspiciousDate;
        
        @JsonProperty("merchant_connector")
        private MerchantConnector merchantConnector;
        
        @JsonProperty("user")
        private User user;

        // Default constructor
        public Transaction() {}

        // Getters and setters
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getConvertedAmount() { return convertedAmount; }
        public String getConvertedCurrency() { return convertedCurrency; }
        public String getTransactionId() { return transactionId; }
        public String getAmount() { return amount; }
        public String getCurrency() { return currency; }
        public String getStatus() { return status; }
        public String getCardType() { return cardType; }
        public String getCardNumber() { return cardNumber; }
        public String getTransactionType() { return transactionType; }
        public String getOrderId() { return orderId; }
        public String getCountry() { return country; }
        public String getEmail() { return email; }
        public String getCreatedAt() { return createdAt; }
        public String getTransactionDate() { return transactionDate; }
        public String getChargebackDate() { return chargebackDate; }
        public String getRefundDate() { return refundDate; }
        public String getSuspiciousDate() { return suspiciousDate; }
        public MerchantConnector getMerchantConnector() { return merchantConnector; }
        public User getUser() { return user; }

        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setConvertedAmount(String convertedAmount) { this.convertedAmount = convertedAmount; }
        public void setConvertedCurrency(String convertedCurrency) { this.convertedCurrency = convertedCurrency; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
        public void setAmount(String amount) { this.amount = amount; }
        public void setCurrency(String currency) { this.currency = currency; }
        public void setStatus(String status) { this.status = status; }
        public void setCardType(String cardType) { this.cardType = cardType; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public void setCountry(String country) { this.country = country; }
        public void setEmail(String email) { this.email = email; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
        public void setTransactionDate(String transactionDate) { this.transactionDate = transactionDate; }
        public void setChargebackDate(String chargebackDate) { this.chargebackDate = chargebackDate; }
        public void setRefundDate(String refundDate) { this.refundDate = refundDate; }
        public void setSuspiciousDate(String suspiciousDate) { this.suspiciousDate = suspiciousDate; }
        public void setMerchantConnector(MerchantConnector merchantConnector) { this.merchantConnector = merchantConnector; }
        public void setUser(User user) { this.user = user; }

        public static class MerchantConnector {
            @JsonProperty("name")
            private String name;

            public MerchantConnector() {}

            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
        }

        public static class User {
            @JsonProperty("name")
            private String name;
            
            @JsonProperty("user_kyc")
            private UserKyc userKyc;

            public User() {}

            public String getName() { return name; }
            public UserKyc getUserKyc() { return userKyc; }

            public void setName(String name) { this.name = name; }
            public void setUserKyc(UserKyc userKyc) { this.userKyc = userKyc; }

            public static class UserKyc {
                @JsonProperty("name")
                private String name;

                public UserKyc() {}

                public String getName() { return name; }
                public void setName(String name) { this.name = name; }
            }
        }
    }

    public static class PaginationMeta {
        @JsonProperty("current_page")
        private int currentPage;
        
        @JsonProperty("per_page")
        private int perPage;
        
        @JsonProperty("total")
        private int total;
        
        @JsonProperty("last_page")
        private int lastPage;

        // Default constructor
        public PaginationMeta() {}

        // Getters and setters
        public int getCurrentPage() { return currentPage; }
        public int getPerPage() { return perPage; }
        public int getTotal() { return total; }
        public int getLastPage() { return lastPage; }

        public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }
        public void setPerPage(int perPage) { this.perPage = perPage; }
        public void setTotal(int total) { this.total = total; }
        public void setLastPage(int lastPage) { this.lastPage = lastPage; }
    }
}

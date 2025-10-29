package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoCurrenciesInput {
    @JsonProperty("country")
    private String country; // ISO 3166-1 alpha-2 country code
    
    @JsonProperty("amount")
    private Integer amount;

    public CryptoCurrenciesInput() {}

    private CryptoCurrenciesInput(Builder builder) {
        this.country = builder.country;
        this.amount = builder.amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String country;
        private Integer amount;

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public CryptoCurrenciesInput build() {
            return new CryptoCurrenciesInput(this);
        }
    }

    // Getters
    public String getCountry() { return country; }
    public Integer getAmount() { return amount; }
}

package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoCurrenciesOutput {
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private List<CryptoCurrency> data;

    public static class CryptoCurrency {
        @JsonProperty("name")
        private String name;
        
        @JsonProperty("code")
        private String code;
        
        @JsonProperty("symbol")
        private String symbol;

        public CryptoCurrency() {}

        // Getters
        public String getName() { return name; }
        public String getCode() { return code; }
        public String getSymbol() { return symbol; }

        // Setters for Jackson
        public void setName(String name) { this.name = name; }
        public void setCode(String code) { this.code = code; }
        public void setSymbol(String symbol) { this.symbol = symbol; }
    }

    public CryptoCurrenciesOutput() {}

    // Getters
    public String getMessage() { return message; }
    public List<CryptoCurrency> getData() { return data; }

    // Setters for Jackson
    public void setMessage(String message) { this.message = message; }
    public void setData(List<CryptoCurrency> data) { this.data = data; }
}

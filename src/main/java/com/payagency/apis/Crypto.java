package com.payagency.apis;

import com.payagency.client.ApiClient;
import com.payagency.exceptions.PayAgencyException;
import com.payagency.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Crypto API for cryptocurrency operations.
 */
public class Crypto {
    private static final Logger logger = LoggerFactory.getLogger(Crypto.class);
    private final ApiClient apiClient;
    private final String environment;

    /**
     * Create a new Crypto API instance.
     * 
     * @param apiClient Configured API client
     */
    public Crypto(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.environment = apiClient.getEnvironment();
    }

    /**
     * Create an OnRamp crypto payment (Fiat to Crypto).
     * 
     * @param data OnRamp payment data
     * @return Crypto OnRamp response
     * @throws PayAgencyException if the request fails
     */
    public CryptoOnRampOutput onRamp(CryptoOnRampInput data) {
        try {
            // Convert to generic payment input with transaction type
            CryptoPaymentInput paymentInput = CryptoPaymentInput.builder()
                .transactionType("ONRAMP")
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .phoneNumber(data.getPhoneNumber())
                .fiatCurrency(data.getFiatCurrency())
                .cryptoCurrency(data.getCryptoCurrency())
                .walletAddress(data.getWalletAddress())
                .ipAddress(data.getIpAddress())
                .email(data.getEmail())
                .country(data.getCountry())
                .cryptoNetwork(data.getCryptoNetwork())
                .redirectUrl(data.getRedirectUrl())
                .webhookUrl(data.getWebhookUrl())
                .orderId(data.getOrderId())
                .terminalId(data.getTerminalId())
                .fiatAmount(data.getFiatAmount()) // OnRamp only has fiat_amount
                .build();
            
            CryptoPaymentOutput response = payment(paymentInput);
            
            // Convert to OnRamp-specific response
            CryptoOnRampOutput onRampResponse = new CryptoOnRampOutput();
            onRampResponse.setStatus(response.getStatus());
            onRampResponse.setMessage(response.getMessage());
            onRampResponse.setRedirectUrl(response.getRedirectUrl());
            
            if (response.getData() != null) {
                CryptoOnRampOutput.CryptoOnRampData onRampData = new CryptoOnRampOutput.CryptoOnRampData();
                onRampData.setTransactionId(response.getData().getTransactionId());
                onRampData.setOrderId(response.getData().getOrderId());
                onRampData.setFiat(response.getData().getFiat());
                onRampData.setFiatAmount(response.getData().getFiatAmount());
                onRampData.setCrypto(response.getData().getCrypto());
                onRampData.setCryptoAmount(response.getData().getCryptoAmount());
                
                if (response.getData().getCustomer() != null) {
                    CryptoOnRampOutput.CryptoOnRampData.Customer customer = new CryptoOnRampOutput.CryptoOnRampData.Customer();
                    customer.setFirstName(response.getData().getCustomer().getFirstName());
                    customer.setLastName(response.getData().getCustomer().getLastName());
                    customer.setEmail(response.getData().getCustomer().getEmail());
                    onRampData.setCustomer(customer);
                }
                
                onRampResponse.setData(onRampData);
            }
            
            return onRampResponse;
        } catch (Exception e) {
            logger.error("Error creating crypto OnRamp: {}", e.getMessage());
            throw new PayAgencyException("Crypto OnRamp failed: " + e.getMessage(), e);
        }
    }

    /**
     * Create an OffRamp crypto payment (Crypto to Fiat).
     * 
     * @param data OffRamp payment data
     * @return Crypto OffRamp response
     * @throws PayAgencyException if the request fails
     */
    public CryptoOffRampOutput offRamp(CryptoOffRampInput data) {
        try {
            // Convert to generic payment input with transaction type
            CryptoPaymentInput paymentInput = CryptoPaymentInput.builder()
                .transactionType("OFFRAMP")
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .phoneNumber(data.getPhoneNumber())
                .fiatCurrency(data.getFiatCurrency())
                .cryptoCurrency(data.getCryptoCurrency())
                .walletAddress(data.getWalletAddress())
                .ipAddress(data.getIpAddress())
                .email(data.getEmail())
                .country(data.getCountry())
                .cryptoNetwork(data.getCryptoNetwork())
                .redirectUrl(data.getRedirectUrl())
                .webhookUrl(data.getWebhookUrl())
                .orderId(data.getOrderId())
                .terminalId(data.getTerminalId())
                .cryptoAmount(data.getCryptoAmount()) // OffRamp only has crypto_amount
                .build();
            
            CryptoPaymentOutput response = payment(paymentInput);
            
            // Convert to OffRamp-specific response
            CryptoOffRampOutput offRampResponse = new CryptoOffRampOutput();
            offRampResponse.setStatus(response.getStatus());
            offRampResponse.setMessage(response.getMessage());
            offRampResponse.setRedirectUrl(response.getRedirectUrl());
            
            if (response.getData() != null) {
                CryptoOffRampOutput.CryptoOffRampData offRampData = new CryptoOffRampOutput.CryptoOffRampData();
                offRampData.setTransactionId(response.getData().getTransactionId());
                offRampData.setOrderId(response.getData().getOrderId());
                offRampData.setFiat(response.getData().getFiat());
                offRampData.setFiatAmount(response.getData().getFiatAmount());
                offRampData.setCrypto(response.getData().getCrypto());
                offRampData.setCryptoAmount(response.getData().getCryptoAmount());
                
                if (response.getData().getCustomer() != null) {
                    CryptoOffRampOutput.CryptoOffRampData.Customer customer = new CryptoOffRampOutput.CryptoOffRampData.Customer();
                    customer.setFirstName(response.getData().getCustomer().getFirstName());
                    customer.setLastName(response.getData().getCustomer().getLastName());
                    customer.setEmail(response.getData().getCustomer().getEmail());
                    offRampData.setCustomer(customer);
                }
                
                offRampResponse.setData(offRampData);
            }
            
            return offRampResponse;
        } catch (Exception e) {
            logger.error("Error creating crypto OffRamp: {}", e.getMessage());
            throw new PayAgencyException("Crypto OffRamp failed: " + e.getMessage(), e);
        }
    }

    /**
     * Create a crypto payment (OnRamp/OffRamp).
     * 
     * @param data Crypto payment data
     * @return Crypto payment response
     * @throws PayAgencyException if the request fails
     */
    public CryptoPaymentOutput payment(CryptoPaymentInput data) {
        try {
            String endpoint = "test".equals(environment) 
                ? "/api/v1/test/crypto" 
                : "/api/v1/live/crypto";
            
            logger.debug("Creating crypto payment via endpoint: {}", endpoint);
            return apiClient.post(endpoint, data, CryptoPaymentOutput.class);
        } catch (Exception e) {
            logger.error("Error creating crypto payment: {}", e.getMessage());
            throw new PayAgencyException("Crypto payment failed: " + e.getMessage(), e);
        }
    }

    /**
     * Create crypto payment link.
     * 
     * @param input Crypto payment link input
     * @return Crypto payment link response
     * @throws PayAgencyException if the request fails
     */
    public CryptoPaymentLinkOutput paymentLink(CryptoPaymentLinkInput input) throws PayAgencyException {
        try {
            logger.info("Creating crypto payment link for transaction type: {}", input.getTransactionType());
            
            String endpoint = "/api/v1/crypto/payment-link";
            return apiClient.post(endpoint, input, CryptoPaymentLinkOutput.class, true); // Skip encryption = true
        } catch (Exception e) {
            logger.error("Error creating crypto payment link: {}", e.getMessage());
            throw new PayAgencyException("Crypto payment link failed: " + e.getMessage(), e);
        }
    }

    /**
     * Create OnRamp payment link (Fiat to Crypto).
     * 
     * @param input OnRamp link input
     * @return Crypto payment link response
     * @throws PayAgencyException if the request fails
     */
    public CryptoPaymentLinkOutput onRampLink(CryptoOnRampLinkInput input) throws PayAgencyException {
        try {
            logger.info("Creating OnRamp payment link");
            
            CryptoPaymentLinkInput paymentLinkInput = CryptoPaymentLinkInput.builder()
                .transactionType("ONRAMP")
                .paymentTemplateId(input.getPaymentTemplateId())
                .fiatAmount(input.getFiatAmount())
                .fiatCurrency(input.getFiatCurrency())
                .cryptoCurrency(input.getCryptoCurrency())
                .orderId(input.getOrderId())
                .terminalId(input.getTerminalId())
                .expiryDate(input.getExpiryDate())
                .build();
            
            return paymentLink(paymentLinkInput);
        } catch (Exception e) {
            logger.error("Error creating OnRamp payment link: {}", e.getMessage());
            throw new PayAgencyException("OnRamp payment link failed: " + e.getMessage(), e);
        }
    }

    /**
     * Create OffRamp payment link (Crypto to Fiat).
     * 
     * @param input OffRamp link input
     * @return Crypto payment link response
     * @throws PayAgencyException if the request fails
     */
    public CryptoPaymentLinkOutput offRampLink(CryptoOffRampLinkInput input) throws PayAgencyException {
        try {
            logger.info("Creating OffRamp payment link");
            
            CryptoPaymentLinkInput paymentLinkInput = CryptoPaymentLinkInput.builder()
                .transactionType("OFFRAMP")
                .paymentTemplateId(input.getPaymentTemplateId())
                .cryptoAmount(input.getCryptoAmount())
                .cryptoCurrency(input.getCryptoCurrency())
                .fiatCurrency(input.getFiatCurrency())
                .orderId(input.getOrderId())
                .terminalId(input.getTerminalId())
                .expiryDate(input.getExpiryDate())
                .build();
            
            return paymentLink(paymentLinkInput);
        } catch (Exception e) {
            logger.error("Error creating OffRamp payment link: {}", e.getMessage());
            throw new PayAgencyException("OffRamp payment link failed: " + e.getMessage(), e);
        }
    }

    /**
     * Create PayIn payment link.
     * 
     * @param input PayIn link input
     * @return Crypto payment link response
     * @throws PayAgencyException if the request fails
     */
    public CryptoPaymentLinkOutput payinLink(CryptoPayinLinkInput input) throws PayAgencyException {
        try {
            logger.info("Creating PayIn payment link");
            
            CryptoPaymentLinkInput paymentLinkInput = CryptoPaymentLinkInput.builder()
                .transactionType("PAYIN")
                .paymentTemplateId(input.getPaymentTemplateId())
                .fiatAmount(input.getFiatAmount())
                .fiatCurrency(input.getFiatCurrency())
                .cryptoCurrency(input.getCryptoCurrency())
                .orderId(input.getOrderId())
                .terminalId(input.getTerminalId())
                .expiryDate(input.getExpiryDate())
                .build();
            
            return paymentLink(paymentLinkInput);
        } catch (Exception e) {
            logger.error("Error creating PayIn payment link: {}", e.getMessage());
            throw new PayAgencyException("PayIn payment link failed: " + e.getMessage(), e);
        }
    }

    /**
     * Get supported crypto currencies for a country.
     * 
     * @param input Crypto currencies input
     * @return Crypto currencies response
     * @throws PayAgencyException if the request fails
     */
    public CryptoCurrenciesOutput getCurrencies(CryptoCurrenciesInput input) throws PayAgencyException {
        try {
            logger.info("Getting crypto currencies for country: {}", input.getCountry());
            
            String endpoint = String.format("/api/v1/%s/crypto/currencies", environment);
            return apiClient.post(endpoint, input, CryptoCurrenciesOutput.class, true); // Skip encryption = true
        } catch (Exception e) {
            logger.error("Error getting crypto currencies: {}", e.getMessage());
            throw new PayAgencyException("Get crypto currencies failed: " + e.getMessage(), e);
        }
    }

    /**
     * Create crypto PayIn transaction.
     * 
     * @param input Crypto PayIn input
     * @return Crypto PayIn response
     * @throws PayAgencyException if the request fails
     */
    public CryptoPayinOutput payin(CryptoPayinInput input) throws PayAgencyException {
        try {
            logger.info("Creating crypto PayIn for customer: {} {}", input.getFirstName(), input.getLastName());
            
            String endpoint = String.format("/api/v1/%s/crypto/payin", environment);
            return apiClient.post(endpoint, input, CryptoPayinOutput.class);
        } catch (Exception e) {
            logger.error("Error creating crypto PayIn: {}", e.getMessage());
            throw new PayAgencyException("Crypto PayIn failed: " + e.getMessage(), e);
        }
    }
}

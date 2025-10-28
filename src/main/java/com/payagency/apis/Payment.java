package com.payagency.apis;

import com.payagency.client.ApiClient;
import com.payagency.exceptions.PayAgencyException;
import com.payagency.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Payment API for handling card payments, hosted payments, and alternative payment methods.
 * 
 * This class provides methods for:
 * - Server-to-Server (S2S) card payments
 * - Hosted payment page creation
 * - Alternative Payment Methods (APM) like PayPal, Google Pay, Apple Pay
 */
public class Payment {
    private static final Logger logger = LoggerFactory.getLogger(Payment.class);
    private final ApiClient apiClient;
    private final String environment;

    /**
     * Create a new Payment API instance.
     * 
     * @param apiClient Configured API client
     */
    public Payment(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.environment = apiClient.getEnvironment();
    }

    /**
     * Process a Server-to-Server (S2S) card payment.
     * 
     * This method allows you to process card payments directly by providing
     * card details in the request payload.
     * 
     * @param data S2S payment request data including card details
     * @return S2S payment response
     * @throws PayAgencyException if the payment request fails
     */
    public S2SOutput s2s(S2SInput data) {
        try {
            String endpoint = "test".equals(environment) 
                ? "/api/v1/test/card" 
                : "/api/v1/live/card";
            
            logger.debug("Processing S2S payment via endpoint: {}", endpoint);
            return apiClient.post(endpoint, data, S2SOutput.class);
        } catch (Exception e) {
            logger.error("Error creating S2S payment: {}", e.getMessage());
            throw new PayAgencyException("S2S payment failed: " + e.getMessage(), e);
        }
    }

    /**
     * Create a hosted payment page.
     * 
     * This method creates a hosted payment page where customers can enter
     * their payment details securely on PayAgency's infrastructure.
     * 
     * @param data Hosted payment request data
     * @return Hosted payment response with payment URL
     * @throws PayAgencyException if the payment request fails
     */
    public HostedOutput hosted(HostedInput data) {
        try {
            String endpoint = "test".equals(environment) 
                ? "/api/v1/test/hosted/card" 
                : "/api/v1/live/hosted/card";
            
            logger.debug("Creating hosted payment via endpoint: {}", endpoint);
            return apiClient.post(endpoint, data, HostedOutput.class);
        } catch (Exception e) {
            logger.error("Error creating hosted payment: {}", e.getMessage());
            throw new PayAgencyException("Hosted payment failed: " + e.getMessage(), e);
        }
    }

    /**
     * Process an Alternative Payment Method (APM) payment.
     * 
     * This method supports various alternative payment methods such as
     * PayPal, Google Pay, Apple Pay, and other digital wallets.
     * 
     * @param data APM payment request data
     * @return APM payment response
     * @throws PayAgencyException if the payment request fails
     */
    public APMOutput apm(APMInput data) {
        try {
            String endpoint = "test".equals(environment) 
                ? "/api/v1/test/apm" 
                : "/api/v1/live/apm";
            
            logger.debug("Processing APM payment via endpoint: {}", endpoint);
            return apiClient.post(endpoint, data, APMOutput.class);
        } catch (Exception e) {
            logger.error("Error creating APM payment: {}", e.getMessage());
            throw new PayAgencyException("APM payment failed: " + e.getMessage(), e);
        }
    }
}

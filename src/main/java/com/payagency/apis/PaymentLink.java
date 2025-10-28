package com.payagency.apis;

import com.payagency.client.ApiClient;
import com.payagency.exceptions.PayAgencyException;
import com.payagency.lib.DummyResponse;
import com.payagency.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PaymentLink API for creating and managing payment links.
 */
public class PaymentLink {
    private static final Logger logger = LoggerFactory.getLogger(PaymentLink.class);
    private final ApiClient apiClient;
    private final String environment;

    /**
     * Create a new PaymentLink API instance.
     * 
     * @param apiClient Configured API client
     */
    public PaymentLink(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.environment = apiClient.getEnvironment();
    }

    /**
     * Create a payment link.
     * 
     * @param data Payment link creation data
     * @return Payment link creation response
     * @throws PayAgencyException if the request fails
     */
    public PaymentLinkCreateOutput create(PaymentLinkCreateInput data) {
        try {
            String endpoint = "/api/v1/payment-link";
            
            logger.debug("Creating payment link via endpoint: {}", endpoint);
            return apiClient.post(endpoint, data, PaymentLinkCreateOutput.class);
        } catch (Exception e) {
            logger.error("Error creating payment link: {}", e.getMessage());
            throw new PayAgencyException("Payment link creation failed: " + e.getMessage(), e);
        }
    }

    /**
     * Get available payment templates.
     * Returns mock data in test environment to match npm-payagency behavior.
     * 
     * @return Payment templates response
     * @throws PayAgencyException if the request fails
     */
    public PaymentTemplatesOutput getTemplates() {
        try {
            // Mock response for test environment - matches npm behavior
            if ("test".equals(environment)) {
                logger.debug("Returning mock payment templates for test environment");
                return DummyResponse.getTestPaymentTemplates();
            }
            
            String endpoint = "/api/v1/payment-templates";
            
            logger.debug("Fetching payment templates via endpoint: {}", endpoint);
            return apiClient.get(endpoint, PaymentTemplatesOutput.class);
        } catch (Exception e) {
            logger.error("Error fetching payment templates: {}", e.getMessage());
            throw new PayAgencyException("Failed to fetch payment templates: " + e.getMessage(), e);
        }
    }
}

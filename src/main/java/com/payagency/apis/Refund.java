package com.payagency.apis;

import com.payagency.client.ApiClient;
import com.payagency.exceptions.PayAgencyException;
import com.payagency.types.RefundInput;
import com.payagency.types.RefundOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Refund API for processing refund transactions.
 * 
 * This class provides methods for processing refunds on existing transactions.
 */
public class Refund {
    private static final Logger logger = LoggerFactory.getLogger(Refund.class);
    private final ApiClient apiClient;

    /**
     * Create a new Refund API instance.
     * 
     * @param apiClient Configured API client
     */
    public Refund(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create a refund for an existing transaction.
     * 
     * This method processes a refund for a previously completed transaction.
     * You can specify an amount for partial refunds, or leave it empty for full refunds.
     * 
     * @param data Refund request data including transaction ID and reason
     * @return Refund response
     * @throws PayAgencyException if the refund request fails
     */
    public RefundOutput create(RefundInput data) {
        try {
            String endpoint = "/api/v1/refund";
            
            logger.debug("Processing refund for transaction: {}", data.getTransactionId());
            return apiClient.post(endpoint, data, RefundOutput.class);
        } catch (Exception e) {
            logger.error("Error creating refund: {}", e.getMessage());
            throw new PayAgencyException("Refund failed: " + e.getMessage(), e);
        }
    }
}

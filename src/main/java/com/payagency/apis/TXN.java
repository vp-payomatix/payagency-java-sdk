package com.payagency.apis;

import com.payagency.client.ApiClient;
import com.payagency.exceptions.PayAgencyException;
import com.payagency.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TXN API for transaction management and queries.
 */
public class TXN {
    private static final Logger logger = LoggerFactory.getLogger(TXN.class);
    private final ApiClient apiClient;
    private final String environment;

    /**
     * Create a new TXN API instance.
     * 
     * @param apiClient Configured API client
     */
    public TXN(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.environment = apiClient.getEnvironment();
    }

    /**
     * Get transactions with optional filters.
     * 
     * @param params Transaction query parameters
     * @return Transactions response
     * @throws PayAgencyException if the request fails
     */
    public TransactionsOutput getTransactions(TransactionsInput params) {
        try {
            String endpoint = "test".equals(environment) 
                ? "/api/v1/test-transactions" 
                : "/api/v1/live-transactions";
            
            logger.debug("Fetching transactions via endpoint: {}", endpoint);
            return apiClient.get(endpoint, TransactionsOutput.class);
        } catch (Exception e) {
            logger.error("Error fetching transactions: {}", e.getMessage());
            throw new PayAgencyException("Failed to fetch transactions: " + e.getMessage(), e);
        }
    }

    /**
     * Get wallet transactions with optional filters.
     * 
     * @param params Transaction query parameters
     * @return Wallet transactions response
     * @throws PayAgencyException if the request fails
     */
    public TransactionsOutput getWalletTransactions(TransactionsInput params) {
        try {
            String endpoint = "test".equals(environment) 
                ? "/api/v1/test-wallet-transactions" 
                : "/api/v1/live-wallet-transactions";
            
            logger.debug("Fetching wallet transactions via endpoint: {}", endpoint);
            return apiClient.get(endpoint, TransactionsOutput.class);
        } catch (Exception e) {
            logger.error("Error fetching wallet transactions: {}", e.getMessage());
            throw new PayAgencyException("Failed to fetch wallet transactions: " + e.getMessage(), e);
        }
    }

    /**
     * Get payment status by transaction ID.
     * 
     * @param transactionId Transaction ID
     * @return Payment status response
     * @throws PayAgencyException if the request fails
     */
    public PaymentStatusOutput getPaymentStatus(String transactionId) {
        try {
            String endpoint = String.format("test".equals(environment) 
                ? "/api/test/status/%s"
                : "/api/live/status/%s", transactionId);
            
            logger.debug("Getting payment status via endpoint: {}", endpoint);
            return apiClient.get(endpoint, PaymentStatusOutput.class);
        } catch (Exception e) {
            logger.error("Error getting payment status: {}", e.getMessage());
            throw new PayAgencyException("Failed to get payment status: " + e.getMessage(), e);
        }
    }
}

package com.payagency.apis;

import com.payagency.client.ApiClient;
import com.payagency.exceptions.PayAgencyException;
import com.payagency.lib.DummyResponse;
import com.payagency.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Payout API for sending money to wallets and bank accounts.
 * 
 * This class provides methods for processing payouts and managing wallet operations.
 */
public class Payout {
    private static final Logger logger = LoggerFactory.getLogger(Payout.class);
    private final ApiClient apiClient;
    private final String environment;

    /**
     * Create a new Payout API instance.
     * 
     * @param apiClient Configured API client
     */
    public Payout(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.environment = apiClient.getEnvironment();
    }

    /**
     * Create a payout transaction.
     * 
     * @param data Payout request data
     * @return Payout response
     * @throws PayAgencyException if the payout request fails
     */
    public PayoutOutput create(PayoutInput data) {
        try {
            String endpoint = "test".equals(environment) 
                ? "/api/v1/test/payout" 
                : "/api/v1/live/payout";
            
            logger.debug("Creating payout via endpoint: {}", endpoint);
            return apiClient.post(endpoint, data, PayoutOutput.class);
        } catch (Exception e) {
            logger.error("Error creating payout: {}", e.getMessage());
            throw new PayAgencyException("Payout creation failed: " + e.getMessage(), e);
        }
    }

    /**
     * Get available wallets.
     * Returns mock data in test environment to match npm-payagency behavior.
     * 
     * @return Wallets response with available wallet information
     * @throws PayAgencyException if the request fails
     */
    public WalletsOutput getWallets() {
        try {
            // Mock response for test environment - matches npm behavior
            if ("test".equals(environment)) {
                logger.debug("Returning mock wallets for test environment");
                return DummyResponse.getTestWallets();
            }
            
            String endpoint = "/api/v1/wallet";
            
            logger.debug("Fetching wallets via endpoint: {}", endpoint);
            return apiClient.get(endpoint, WalletsOutput.class);
        } catch (Exception e) {
            logger.error("Error fetching wallets: {}", e.getMessage());
            throw new PayAgencyException("Failed to fetch wallets: " + e.getMessage(), e);
        }
    }

    /**
     * Estimate payout fees.
     * Returns mock data in test environment to match npm-payagency behavior.
     * 
     * @param data Fee estimation request data
     * @return Fee estimation response
     * @throws PayAgencyException if the request fails
     */
    public EstimateFeeOutput estimateFee(EstimateFeeInput data) {
        try {
            // Mock response for test environment - matches npm behavior
            if ("test".equals(environment)) {
                logger.debug("Returning mock estimate fee for test environment");
                return DummyResponse.getTestEstimatePayoutResponse();
            }
            
            String endpoint = "/api/v1/wallet/estimate-payout";
            
            logger.debug("Estimating payout fee via endpoint: {}", endpoint);
            return apiClient.post(endpoint, data, EstimateFeeOutput.class);
        } catch (Exception e) {
            logger.error("Error estimating payout fee: {}", e.getMessage());
            throw new PayAgencyException("Fee estimation failed: " + e.getMessage(), e);
        }
    }

    /**
     * Get payout status by reference ID.
     * 
     * @param referenceId Payout reference ID
     * @return Payout status response
     * @throws PayAgencyException if the request fails
     */
    public PayoutStatusOutput getStatus(String referenceId) {
        try {
            String endpoint = String.format("test".equals(environment) 
                ? "/api/v1/test/payout/%s/status"
                : "/api/v1/live/payout/%s/status", referenceId);
            
            logger.debug("Getting payout status via endpoint: {}", endpoint);
            return apiClient.get(endpoint, PayoutStatusOutput.class);
        } catch (Exception e) {
            logger.error("Error getting payout status: {}", e.getMessage());
            throw new PayAgencyException("Failed to get payout status: " + e.getMessage(), e);
        }
    }
}

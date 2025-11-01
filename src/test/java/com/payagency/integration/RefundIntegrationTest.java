package com.payagency.integration;

import com.payagency.apis.Refund;
import com.payagency.types.*;
import com.payagency.exceptions.PayAgencyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RefundIntegrationTest {
    
    private final Refund refundApi = TestUtility.api.getRefund();

    @Test
    public void testCreateFullRefund() throws Exception {
        // Test full refund using transaction ID from npm test
        RefundInput request = RefundInput.builder()
            .transactionId("PA8526657613328459")
            .reason("Customer request")
            .build();

        RefundOutput response = refundApi.create(request);
        
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getStatus(), "Status should not be null");
        
        System.out.println("Full Refund Response:");
        System.out.println("  Status: " + response.getStatus());
        System.out.println("  Message: " + response.getMessage());
        
        if (response.getData() != null) {
            System.out.println("  Transaction ID: " + response.getData().getTransactionId());
            System.out.println("  Amount: " + response.getData().getAmount() + " " + response.getData().getCurrency());
            
            // Check if refund data is included
            if (response.getData().getRefund() != null) {
                System.out.println("  Refund Status: " + response.getData().getRefund().isStatus());
                System.out.println("  Refund Date: " + response.getData().getRefund().getRefundDate());
            }
        }
    }

    @Test
    public void testCreatePartialRefund() throws Exception {
        // Test partial refund with specific amount
        RefundInput request = RefundInput.builder()
            .transactionId("PA8526657613328459")
            .reason("Partial product return")
            .amount("50.00") // Partial refund amount
            .build();

        RefundOutput response = refundApi.create(request);
        
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getStatus(), "Status should not be null");
        
        System.out.println("Partial Refund Response:");
        System.out.println("  Status: " + response.getStatus());
        System.out.println("  Message: " + response.getMessage());
        
        if (response.getData() != null) {
            System.out.println("  Transaction ID: " + response.getData().getTransactionId());
            System.out.println("  Refund Amount: " + request.getAmount());
            
            if (response.getData().getRefund() != null) {
                System.out.println("  Refund Status: " + response.getData().getRefund().isStatus());
            }
        }
    }

    @Test
    public void testRefundWithDifferentReasons() throws Exception {
        // Test refund with different reason codes
        String[] reasons = {
            "Product returned",
            "Duplicate payment", 
            "Customer dissatisfaction",
            "Technical error",
            "Fraudulent transaction"
        };
        
        for (String reason : reasons) {
            try {
                RefundInput request = RefundInput.builder()
                    .transactionId("PA8526657613328459")
                    .reason(reason)
                    .build();

                RefundOutput response = refundApi.create(request);
                
                assertNotNull(response, "Response should not be null for reason: " + reason);
                System.out.println("Refund with reason '" + reason + "' - Status: " + response.getStatus());
                
                // Small delay between requests to avoid rate limiting
                Thread.sleep(100);
                
            } catch (Exception e) {
                System.out.println("Refund with reason '" + reason + "' - API response: " + e.getMessage());
            }
        }
    }

    @Test
    public void testRefundValidation() {
        // Test that required fields are validated
        
        // Test missing transaction ID
        assertThrows(IllegalArgumentException.class, () -> {
            RefundInput.builder()
                .reason("Test reason")
                .build();
        }, "Should throw exception for missing transaction ID");
        
        // Test empty transaction ID
        assertThrows(IllegalArgumentException.class, () -> {
            RefundInput.builder()
                .transactionId("")
                .reason("Test reason")
                .build();
        }, "Should throw exception for empty transaction ID");
        
        // Test missing reason
        assertThrows(IllegalArgumentException.class, () -> {
            RefundInput.builder()
                .transactionId("PA123456789")
                .build();
        }, "Should throw exception for missing reason");
        
        // Test empty reason
        assertThrows(IllegalArgumentException.class, () -> {
            RefundInput.builder()
                .transactionId("PA123456789")
                .reason("")
                .build();
        }, "Should throw exception for empty reason");
        
        System.out.println("Refund validation tests passed successfully");
    }

    @Test
    public void testRefundWithInvalidTransactionId() throws Exception {
        // Test refund with invalid transaction ID
        try {
            RefundInput request = RefundInput.builder()
                .transactionId("INVALID_TXN_ID_12345")
                .reason("Testing invalid transaction ID")
                .build();

            RefundOutput response = refundApi.create(request);
            
            System.out.println("Invalid Transaction ID Refund Response:");
            System.out.println("  Status: " + response.getStatus());
            System.out.println("  Message: " + response.getMessage());
            
        } catch (PayAgencyException e) {
            System.out.println("Expected error for invalid transaction ID: " + e.getMessage());
            // This is expected behavior for invalid transaction IDs
        }
    }

    @Test
    public void testRefundWithLargeAmount() throws Exception {
        // Test refund with amount larger than original transaction
        try {
            RefundInput request = RefundInput.builder()
                .transactionId("PA8526657613328459")
                .reason("Testing large refund amount")
                .amount("999999.99") // Very large amount
                .build();

            RefundOutput response = refundApi.create(request);
            
            System.out.println("Large Amount Refund Response:");
            System.out.println("  Status: " + response.getStatus());
            System.out.println("  Message: " + response.getMessage());
            
        } catch (PayAgencyException e) {
            System.out.println("Expected error for large refund amount: " + e.getMessage());
            // This is expected behavior for amounts larger than original transaction
        }
    }

    @Test
    public void testRefundMultipleDifferentTransactions() throws Exception {
        // Test refund for multiple different transaction IDs
        String[] transactionIds = {
            "PA8526657613328459",
            "PA1234567890123456", // Likely invalid, but good for testing
            "PA9876543210987654"  // Likely invalid, but good for testing
        };
        
        for (String txnId : transactionIds) {
            try {
                RefundInput request = RefundInput.builder()
                    .transactionId(txnId)
                    .reason("Bulk refund test for transaction: " + txnId)
                    .build();

                RefundOutput response = refundApi.create(request);
                
                System.out.println("Refund for " + txnId + " - Status: " + response.getStatus());
                
                if (response.getData() != null) {
                    System.out.println("  Transaction ID: " + response.getData().getTransactionId());
                }
                
                // Small delay between requests
                Thread.sleep(100);
                
            } catch (Exception e) {
                System.out.println("Refund for " + txnId + " - API response: " + e.getMessage());
            }
        }
    }

    @Test
    public void testRefundResponseStructure() throws Exception {
        // Test that the response structure matches expectations
        try {
            RefundInput request = RefundInput.builder()
                .transactionId("PA8526657613328459")
                .reason("Response structure validation test")
                .build();

            RefundOutput response = refundApi.create(request);
            
            // Validate response structure
            assertNotNull(response, "Response should not be null");
            assertTrue(response instanceof PaymentResponse, "RefundOutput should extend PaymentResponse");
            
            // Test inherited methods from PaymentResponse
            assertNotNull(response.getStatus(), "Status should be accessible");
            
            System.out.println("Response Structure Validation:");
            System.out.println("  Response class: " + response.getClass().getSimpleName());
            System.out.println("  Status: " + response.getStatus());
            System.out.println("  Message: " + response.getMessage());
            System.out.println("  Success flag: " + response.isSuccess());
            
            // If data is present, validate structure
            if (response.getData() != null) {
                System.out.println("  Has transaction data: true");
                System.out.println("  Transaction ID: " + response.getData().getTransactionId());
                
                if (response.getData().getCustomer() != null) {
                    System.out.println("  Customer data available: true");
                }
                
                if (response.getData().getRefund() != null) {
                    System.out.println("  Refund data available: true");
                    System.out.println("  Refund status: " + response.getData().getRefund().isStatus());
                }
            }
            
        } catch (Exception e) {
            System.out.println("Response structure test - API response: " + e.getMessage());
        }
    }

    @Test
    public void testRefundWithSpecialCharactersInReason() throws Exception {
        // Test refund with special characters and Unicode in reason
        String[] specialReasons = {
            "Customer request - 100% satisfaction guarantee",
            "Refund requested via email: customer@email.com",
            "返品要求 (Japanese: Return request)",
            "Remboursement demandé (French: Refund requested)",
            "Reason with emojis: 😊 Customer happy with return process 🔄",
            "Multi-line\nreason with\ntabs\tand spaces"
        };
        
        for (String reason : specialReasons) {
            try {
                RefundInput request = RefundInput.builder()
                    .transactionId("PA8526657613328459")
                    .reason(reason)
                    .build();

                RefundOutput response = refundApi.create(request);
                
                System.out.println("Special reason refund - Status: " + response.getStatus());
                System.out.println("  Reason length: " + reason.length() + " characters");
                
                // Small delay between requests
                Thread.sleep(100);
                
            } catch (Exception e) {
                System.out.println("Special reason refund - API response: " + e.getMessage());
            }
        }
    }
}
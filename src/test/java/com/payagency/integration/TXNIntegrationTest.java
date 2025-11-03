package com.payagency.integration;

import com.payagency.apis.TXN;
import com.payagency.types.*;
import com.payagency.exceptions.PayAgencyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TXNIntegrationTest {
    
    private final TXN txnApi = TestUtility.api.getTxn();

    // @Test
    // public void testGetTransactions() throws Exception {
    //     // Test basic transactions query matching npm test pattern
    //     TransactionsInput request = TransactionsInput.builder()
    //         .transactionStartDate("2023-01-01")
    //         .transactionEndDate("2023-12-31")
    //         .build();

    //     TransactionsOutput response = txnApi.getTransactions(request);

    //     System.out.println("Transactions Response:");
    //     System.out.println("  Message: " + response.getMessage());
 
    // }

    // @Test
    // public void testGetTransactionsWithoutDateFilter() throws Exception {
    //     // Test transactions query without date filters
    //     TransactionsInput request = TransactionsInput.builder().build();

    //     TransactionsOutput response = txnApi.getTransactions(request);
        
    //     System.out.println("Transactions (No Date Filter) Response:");
    //     System.out.println("  Message: " + response.getMessage());
        
     
    // }

    // @Test
    // public void testGetTransactionsWithPagination() throws Exception {
    //     // Test transactions with cursor-based pagination
    //     String[] testCursors = {
    //         "next_cursor_value_123",
    //         "prev_cursor_value_456",
    //         null // Test with null cursor
    //     };
        
    //     for (String cursor : testCursors) {
    //         try {
    //             TransactionsInput.Builder requestBuilder = TransactionsInput.builder()
    //                 .transactionStartDate("2023-01-01")
    //                 .transactionEndDate("2023-12-31");
                
    //             if (cursor != null) {
    //                 if (cursor.startsWith("next")) {
    //                     requestBuilder.nextCursor(cursor);
    //                 } else {
    //                     requestBuilder.prevCursor(cursor);
    //                 }
    //             }
                
    //             TransactionsInput request = requestBuilder.build();
    //             TransactionsOutput response = txnApi.getTransactions(request);
                
    //             assertNotNull(response, "Response should not be null");
                
    //             String cursorType = cursor == null ? "no cursor" : 
    //                 (cursor.startsWith("next") ? "next cursor" : "prev cursor");
    //             System.out.println("Transactions with " + cursorType + " - Message: " + response.getMessage());
                
    //             if (response.getData() != null) {
    //                 System.out.println("  Number of transactions: " + response.getData().size());
    //             }
                
    //             Thread.sleep(100); // Avoid rate limiting
                
    //         } catch (Exception e) {
    //             System.out.println("Pagination test with cursor '" + cursor + "' - API response: " + e.getMessage());
    //         }
    //     }
    // }

    @Test
    public void testGetWalletTransactions() throws Exception {
        // Test wallet transactions query matching npm test pattern
        // TransactionsInput request = TransactionsInput.builder()
        //     .transactionStartDate("2023-01-01")
        //     .transactionEndDate("2023-12-31")
        //     .build();

        // TransactionsOutput response = txnApi.getWalletTransactions(request);
  
        
        // System.out.println("Wallet Transactions Response:");
        // System.out.println("  Message: " + response.getMessage());
        TransactionsInput walletTxnInput = TransactionsInput.builder()
    .transactionStartDate("2023-01-01") // Optional
    .transactionEndDate("2023-12-31") // Optional
    .nextCursor("cursor_value") // Optional
    .prevCursor("cursor_value") // Optional
    .build();

TransactionsOutput walletTransactions = txnApi.getWalletTransactions(walletTxnInput);
    System.out.println("Wallet Transactions Response:");
        System.out.println("  Message: " + walletTransactions.getMessage());
    }

    // @Test
    // public void testGetWalletTransactionsWithDifferentDateRanges() throws Exception {
    //     // Test wallet transactions with different date ranges
    //     String[][] dateRanges = {
    //         {"2024-01-01", "2024-01-31"}, // January 2024
    //         {"2023-06-01", "2023-06-30"}, // June 2023
    //         {"2023-01-01", "2024-12-31"}, // Full year range
    //         {"2024-10-01", "2024-10-31"}  // Recent month
    //     };
        
    //     for (String[] dateRange : dateRanges) {
    //         try {
    //             TransactionsInput request = TransactionsInput.builder()
    //                 .transactionStartDate(dateRange[0])
    //                 .transactionEndDate(dateRange[1])
    //                 .build();

    //             TransactionsOutput response = txnApi.getWalletTransactions(request);
                
    //             assertNotNull(response, "Response should not be null");
                
    //             System.out.println("Wallet transactions from " + dateRange[0] + " to " + dateRange[1] + ":");
    //             System.out.println("  Message: " + response.getMessage());
                
    //             if (response.getData() != null) {
    //                 System.out.println("  Count: " + response.getData().size());
    //             }
                
    //             Thread.sleep(100); // Avoid rate limiting
                
    //         } catch (Exception e) {
    //             System.out.println("Date range " + dateRange[0] + " to " + dateRange[1] + " - API response: " + e.getMessage());
    //         }
    //     }
    // }

    // @Test
    // public void testGetPaymentStatus() throws Exception {
    //     // Test payment status retrieval for different transaction IDs
    //     String[] transactionIds = {
    //         "PA8526657613328459", // From npm test
    //         "PA1877208010353680", // From payout test
    //         "PA5108743179386871", // From documentation examples
    //         "INVALID_TXN_ID_123"  // Invalid ID for error testing
    //     };
        
    //     for (String txnId : transactionIds) {
    //         try {
    //             PaymentStatusOutput response = txnApi.getPaymentStatus(txnId);
                
    //             assertNotNull(response, "Response should not be null for txn: " + txnId);
                
    //             System.out.println("Payment status for " + txnId + ":");
    //             System.out.println("  Status: " + response.getStatus());
    //             System.out.println("  Message: " + response.getMessage());
                
    //             if (response.getData() != null) {
    //                 System.out.println("  Transaction ID: " + response.getData().getTransactionId());
    //                 System.out.println("  Amount: " + response.getData().getAmount() + " " + response.getData().getCurrency());
                    
    //                 if (response.getData().getCustomer() != null) {
    //                     System.out.println("  Customer: " + response.getData().getCustomer().getFirstName() + 
    //                         " " + response.getData().getCustomer().getLastName());
    //                 }
                    
    //                 // Check refund status
    //                 if (response.getData().getRefund() != null) {
    //                     System.out.println("  Refund Status: " + response.getData().getRefund().isStatus());
    //                     System.out.println("  Refund Date: " + response.getData().getRefund().getRefundDate());
    //                 }
                    
    //                 // Check chargeback status
    //                 if (response.getData().getChargeback() != null) {
    //                     System.out.println("  Chargeback Status: " + response.getData().getChargeback().isStatus());
    //                     System.out.println("  Chargeback Date: " + response.getData().getChargeback().getChargebackDate());
    //                 }
    //             }
                
    //             Thread.sleep(100); // Avoid rate limiting
                
    //         } catch (PayAgencyException e) {
    //             System.out.println("Payment status for " + txnId + " - Expected error: " + e.getMessage());
    //             // Invalid transaction IDs should throw exceptions
    //             if (txnId.equals("INVALID_TXN_ID_123")) {
    //                 // This is expected behavior
    //                 assertTrue(true, "Invalid transaction ID should cause an exception");
    //             }
    //         }
    //     }
    // }

    // @Test
    // public void testTransactionResponseStructureValidation() throws Exception {
    //     // Test that transaction response structure is properly mapped
    //     try {
    //         TransactionsInput request = TransactionsInput.builder()
    //             .transactionStartDate("2023-01-01")
    //             .transactionEndDate("2023-12-31")
    //             .build();

    //         TransactionsOutput response = txnApi.getTransactions(request);
            
    //         // Validate main response structure
    //         assertNotNull(response, "Response should not be null");
    //         assertTrue(response instanceof TransactionsOutput, "Response should be TransactionsOutput type");
            
    //         System.out.println("Transaction Response Structure Validation:");
    //         System.out.println("  Response class: " + response.getClass().getSimpleName());
    //         System.out.println("  Has message: " + (response.getMessage() != null));
    //         System.out.println("  Has data: " + (response.getData() != null));
    //         System.out.println("  Has meta: " + (response.getMeta() != null));
            
    //         // If transactions are present, validate transaction structure
    //         if (response.getData() != null && !response.getData().isEmpty()) {
    //             TransactionsOutput.Transaction txn = response.getData().get(0);
                
    //             System.out.println("  Transaction structure validation:");
    //             System.out.println("    Has transaction_id: " + (txn.getTransactionId() != null));
    //             System.out.println("    Has amount: " + (txn.getAmount() != null));
    //             System.out.println("    Has currency: " + (txn.getCurrency() != null));
    //             System.out.println("    Has status: " + (txn.getStatus() != null));
    //             System.out.println("    Has customer info: " + (txn.getFirstName() != null || txn.getLastName() != null));
    //             System.out.println("    Has email: " + (txn.getEmail() != null));
    //             System.out.println("    Has created_at: " + (txn.getCreatedAt() != null));
    //             System.out.println("    Has transaction_date: " + (txn.getTransactionDate() != null));
    //             System.out.println("    Has card info: " + (txn.getCardType() != null || txn.getCardNumber() != null));
    //             System.out.println("    Has order_id: " + (txn.getOrderId() != null));
    //             System.out.println("    Has country: " + (txn.getCountry() != null));
                
    //             // Validate nested objects
    //             if (txn.getMerchantConnector() != null) {
    //                 System.out.println("    Has merchant_connector: " + (txn.getMerchantConnector().getName() != null));
    //             }
                
    //             if (txn.getUser() != null) {
    //                 System.out.println("    Has user info: " + (txn.getUser().getName() != null));
    //                 if (txn.getUser().getUserKyc() != null) {
    //                     System.out.println("    Has user KYC: " + (txn.getUser().getUserKyc().getName() != null));
    //                 }
    //             }
                
    //             // Validate date fields
    //             System.out.println("    Has refund_date: " + (txn.getRefundDate() != null));
    //             System.out.println("    Has chargeback_date: " + (txn.getChargebackDate() != null));
    //             System.out.println("    Has suspicious_date: " + (txn.getSuspiciousDate() != null));
    //         }
            
    //         // Validate pagination metadata
    //         if (response.getMeta() != null) {
    //             TransactionsOutput.PaginationMeta meta = response.getMeta();
    //             System.out.println("  Pagination metadata validation:");
    //             System.out.println("    Has next page: " + meta.isHasNextPage());
    //             System.out.println("    Has previous page: " + meta.isHasPreviousPage());
    //             System.out.println("    Total count: " + meta.getTotalCount());
    //             System.out.println("    Next cursor: " + meta.getNextCursor());
    //             System.out.println("    Previous cursor: " + meta.getPrevCursor());
                
    //             // Validate pagination values are reasonable  
    //             assertTrue(meta.getTotalCount() >= 0, "Total count should be non-negative");
    //             // Boolean values and cursors can be null/false based on actual API response
    //         }
            
    //     } catch (Exception e) {
    //         System.out.println("Transaction structure validation - API response: " + e.getMessage());
    //     }
    // }

    // @Test
    // public void testTransactionEnvironmentSpecificEndpoints() throws Exception {
    //     // Test that the TXN API uses correct endpoints based on environment
    //     // This test validates the endpoint logic by checking responses
        
    //     System.out.println("Testing environment-specific endpoints:");
    //     System.out.println("  Current environment: " + TestUtility.api.getEnvironment());
        
    //     try {
    //         // Test regular transactions endpoint
    //         TransactionsInput request = TransactionsInput.builder()
    //             .transactionStartDate("2023-01-01")
    //             .transactionEndDate("2023-12-31")
    //             .build();

    //         TransactionsOutput response = txnApi.getTransactions(request);
    //         assertNotNull(response, "Transactions response should not be null");
    //         System.out.println("  Regular transactions endpoint - Success");
            
    //         // Test wallet transactions endpoint
    //         TransactionsOutput walletResponse = txnApi.getWalletTransactions(request);
    //         assertNotNull(walletResponse, "Wallet transactions response should not be null");
    //         System.out.println("  Wallet transactions endpoint - Success");
            
    //     } catch (Exception e) {
    //         System.out.println("  Environment endpoint test - API response: " + e.getMessage());
    //     }
    // }

    // @Test
    // public void testTransactionInputValidation() throws Exception {
    //     // Test various input parameter combinations
        
    //     // Valid input with all parameters
    //     TransactionsInput fullRequest = TransactionsInput.builder()
    //         .transactionStartDate("2023-01-01")
    //         .transactionEndDate("2023-12-31")
    //         .nextCursor("test_cursor")
    //         .prevCursor("prev_cursor")
    //         .build();
        
    //     assertNotNull(fullRequest.getTransactionStartDate(), "Start date should be set");
    //     assertNotNull(fullRequest.getTransactionEndDate(), "End date should be set");
    //     assertNotNull(fullRequest.getNextCursor(), "Next cursor should be set");
    //     assertNotNull(fullRequest.getPrevCursor(), "Prev cursor should be set");
        
    //     // Empty input (all parameters null/optional)
    //     TransactionsInput emptyRequest = TransactionsInput.builder().build();
    //     assertNull(emptyRequest.getTransactionStartDate(), "Start date should be null");
    //     assertNull(emptyRequest.getTransactionEndDate(), "End date should be null");
    //     assertNull(emptyRequest.getNextCursor(), "Next cursor should be null");
    //     assertNull(emptyRequest.getPrevCursor(), "Prev cursor should be null");
        
    //     // Partial input - only dates
    //     TransactionsInput dateOnlyRequest = TransactionsInput.builder()
    //         .transactionStartDate("2024-01-01")
    //         .transactionEndDate("2024-01-31")
    //         .build();
        
    //     assertEquals("2024-01-01", dateOnlyRequest.getTransactionStartDate(), "Start date should match");
    //     assertEquals("2024-01-31", dateOnlyRequest.getTransactionEndDate(), "End date should match");
    //     assertNull(dateOnlyRequest.getNextCursor(), "Next cursor should be null");
    //     assertNull(dateOnlyRequest.getPrevCursor(), "Prev cursor should be null");
        
    //     System.out.println("Transaction input validation tests passed");
    // }

    // @Test
    // public void testErrorHandlingForInvalidDates() throws Exception {
    //     // Test transactions with invalid date formats
    //     String[][] invalidDates = {
    //         {"invalid-date", "2023-12-31"},
    //         {"2023-01-01", "invalid-date"},
    //         {"2023-13-01", "2023-12-31"}, // Invalid month
    //         {"2023-01-32", "2023-12-31"}, // Invalid day
    //         {"2023-12-31", "2023-01-01"}  // End date before start date
    //     };
        
    //     for (String[] dates : invalidDates) {
    //         try {
    //             TransactionsInput request = TransactionsInput.builder()
    //                 .transactionStartDate(dates[0])
    //                 .transactionEndDate(dates[1])
    //                 .build();

    //             TransactionsOutput response = txnApi.getTransactions(request);
                
    //             System.out.println("Invalid dates " + dates[0] + " to " + dates[1] + " - Response: " + response.getMessage());
                
    //         } catch (Exception e) {
    //             System.out.println("Invalid dates " + dates[0] + " to " + dates[1] + " - Expected error: " + e.getMessage());
    //             // Invalid dates might cause exceptions, which is expected
    //         }
    //     }
    // }
}
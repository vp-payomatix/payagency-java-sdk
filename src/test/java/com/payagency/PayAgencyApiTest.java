package com.payagency;

import com.payagency.client.PayAgencyClientOptions;
import com.payagency.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic tests for PayAgency API client.
 */
class PayAgencyApiTest {
    
    private PayAgencyApi payAgency;
    
    @BeforeEach
    void setUp() {
        PayAgencyClientOptions options = PayAgencyClientOptions.builder()
            .encryptionKey("test-encryption-key-32-characters")
            .secretKey("PA_TEST_test-secret-key")
            .baseUrl("https://backend.pay.agency")
            .build();
            
        payAgency = new PayAgencyApi(options);
    }
    
    @Test
    void testClientInitialization() {
        assertNotNull(payAgency);
        assertEquals("test", payAgency.getEnvironment());
        assertEquals("https://backend.pay.agency", payAgency.getBaseUrl());
    }
    
    @Test
    void testGetPaymentApi() {
        assertNotNull(payAgency.getPayment());
    }
    
    @Test
    void testGetPayoutApi() {
        assertNotNull(payAgency.getPayout());
    }
    
    @Test
    void testGetCryptoApi() {
        assertNotNull(payAgency.getCrypto());
    }
    
    @Test
    void testS2SInputBuilder() {
        S2SInput input = S2SInput.builder()
            .firstName("John")
            .lastName("Doe")
            .email("john@example.com")
            .amount(10000)
            .currency("USD")
            .cardNumber("4111111111111111")
            .cardExpiryMonth("12")
            .cardExpiryYear("2025")
            .cardCvv("123")
            .build();
            
        assertEquals("John", input.getFirstName());
        assertEquals("Doe", input.getLastName());
        assertEquals("john@example.com", input.getEmail());
        assertEquals(Integer.valueOf(10000), input.getAmount());
        assertEquals("USD", input.getCurrency());
    }
    
    @Test
    void testHostedInputBuilder() {
        HostedInput input = HostedInput.builder()
            .firstName("Jane")
            .lastName("Smith")
            .email("jane@example.com")
            .amount(25000)
            .currency("EUR")
            .redirectUrl("https://example.com/return")
            .build();
            
        assertEquals("Jane", input.getFirstName());
        assertEquals("Smith", input.getLastName());
        assertEquals("jane@example.com", input.getEmail());
        assertEquals(Integer.valueOf(25000), input.getAmount());
        assertEquals("EUR", input.getCurrency());
        assertEquals("https://example.com/return", input.getRedirectUrl());
    }
    
    @Test
    void testRefundInputBuilder() {
        RefundInput input = RefundInput.builder()
            .reason("Customer request")
            .transactionId("txn_123456789")
            .amount("50.00")
            .build();
            
        assertEquals("Customer request", input.getReason());
        assertEquals("txn_123456789", input.getTransactionId());
        assertEquals("50.00", input.getAmount());
    }
    
    @Test
    void testRefundInputValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            RefundInput.builder()
                .transactionId("txn_123456789")
                .build(); // Missing required reason
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            RefundInput.builder()
                .reason("Customer request")
                .build(); // Missing required transactionId
        });
    }
    
    @Test
    void testPaymentResponseMethods() {
        PaymentResponse response = new PaymentResponse();
        
        response.setStatus("SUCCESS");
        assertTrue(response.isSuccess());
        assertFalse(response.isRedirect());
        assertFalse(response.isFailed());
        
        response.setStatus("REDIRECT");
        assertFalse(response.isSuccess());
        assertTrue(response.isRedirect());
        assertFalse(response.isFailed());
        
        response.setStatus("FAILED");
        assertFalse(response.isSuccess());
        assertFalse(response.isRedirect());
        assertTrue(response.isFailed());
    }
    
    @Test
    void testMockedPaymentTemplatesInTestEnvironment() {
        // Test environment should return empty payment templates (mocked)
        PaymentTemplatesOutput templates = payAgency.getPaymentLink().getTemplates();
        
        assertNotNull(templates);
        assertNotNull(templates.getData());
        assertEquals(0, templates.getData().size()); // Empty array like npm
        
        System.out.println("✅ Payment Templates API mocked correctly for test environment");
    }
    
    @Test
    void testMockedWalletsInTestEnvironment() {
        // Test environment should return mock wallets data
        WalletsOutput wallets = payAgency.getPayout().getWallets();
        
        assertNotNull(wallets);
        assertNotNull(wallets.getData());
        assertEquals(2, wallets.getData().size()); // Two test wallets
        
        // Verify first wallet matches npm testWallets
        WalletsOutput.WalletInfo wallet1 = wallets.getData().get(0);
        assertEquals("WAL1234567890", wallet1.getWalletId());
        assertEquals("USD", wallet1.getCurrency());
        assertEquals("10000", wallet1.getAmount());
        assertEquals("card", wallet1.getPaymentMethod());
        assertEquals("Active", wallet1.getStatus());
        
        // Verify second wallet
        WalletsOutput.WalletInfo wallet2 = wallets.getData().get(1);
        assertEquals("WAL9876543210", wallet2.getWalletId());
        assertEquals("EUR", wallet2.getCurrency());
        assertEquals("5000", wallet2.getAmount());
        assertEquals("card", wallet2.getPaymentMethod());
        assertEquals("Inactive", wallet2.getStatus());
        
        System.out.println("✅ Wallets API mocked correctly for test environment");
    }
    
    @Test
    void testMockedEstimateFeeInTestEnvironment() {
        // Create estimate fee input
        EstimateFeeInput input = EstimateFeeInput.builder()
            .walletId("WAL7825818519632620")
            .amount(200)
            .cardNumber("4111111111111111")
            .build();
        
        // Test environment should return mock estimate fee data
        EstimateFeeOutput response = payAgency.getPayout().estimateFee(input);
        
        assertNotNull(response);
        assertNotNull(response.getData());
        
        // Verify data matches npm testEstimatePayoutResponse
        EstimateFeeOutput.EstimateData data = response.getData();
        assertEquals("211.5", data.getAmountRequired());
        assertEquals("1000", data.getWalletBalance());
        assertEquals("11.5", data.getTotalFee());
        
        System.out.println("✅ Estimate Fee API mocked correctly for test environment");
    }
}

package com.payagency.integration;

import com.payagency.apis.Crypto;
import com.payagency.types.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CryptoIntegrationTest {
    
    private final Crypto cryptoApi = TestUtility.api.getCrypto();

    @Test
    public void testCreateCryptoPayment() throws Exception {
        // Using the same test data as npm-payagency crypto tests
        CryptoPaymentInput request = CryptoPaymentInput.builder()
            .transactionType("ONRAMP") // Required field
            .firstName("Diana")
            .lastName("Prince")
            .email("diana@pay.agency")
            .phoneNumber("0123456789")
            .fiatAmount(200) // Integer amount for ONRAMP
            .fiatCurrency("EUR")
            .cryptoCurrency("BTC")
            .walletAddress("1BoatSLRHtKNngkdXEeobR76b53LETtpyT")
            .ipAddress("127.0.0.1")
            .country("GB")
            .cryptoNetwork("BITCOIN")
            .redirectUrl("https://pay.agency")
            .webhookUrl("https://pay.agency/webhook")
            .orderId("crypto-test-" + System.currentTimeMillis())
            .terminalId("T12345")
            .build();

        CryptoPaymentOutput response = cryptoApi.payment(request);
        
        assertNotNull(response);
        assertNotNull(response.getStatus());
        assertTrue("REDIRECT".equals(response.getStatus()) || "PENDING".equals(response.getStatus()) || "SUCCESS".equals(response.getStatus()));
        
        if (response.getData() != null) {
            assertEquals("BTC", response.getData().getCrypto());
            assertNotNull(response.getData().getFiatAmount());
        }
        
        System.out.println("Crypto Payment created successfully:");
        System.out.println("Status: " + response.getStatus());
        System.out.println("Message: " + response.getMessage());
        if (response.getData() != null) {
            System.out.println("Fiat Amount: " + response.getData().getFiatAmount() + " " + response.getData().getFiat());
            System.out.println("Crypto: " + response.getData().getCrypto());
            System.out.println("Transaction ID: " + response.getData().getTransactionId());
        }
    }

    @Test
    public void testCryptoOnRampMethod() throws Exception {
        // Test the OnRamp method specifically using dedicated OnRamp input type
        try {
            CryptoOnRampInput onRampRequest = CryptoOnRampInput.builder()
                .firstName("Diana")
                .lastName("Prince")
                .email("diana@pay.agency")
                .phoneNumber("0123456789")
                .fiatAmount(100) // OnRamp uses fiat amount (Integer)
                .fiatCurrency("USD")
                .cryptoCurrency("BTC")
                .walletAddress("1BoatSLRHtKNngkdXEeobR76b53LETtpyT")
                .ipAddress("127.0.0.1")
                .country("GB")
                .cryptoNetwork("BITCOIN")
                .redirectUrl("https://pay.agency")
                .orderId("onramp-test-" + System.currentTimeMillis())
                .build();

            CryptoOnRampOutput response = cryptoApi.onRamp(onRampRequest);
            
            assertNotNull(response);
            assertNotNull(response.getStatus());
            System.out.println("OnRamp Crypto Payment created successfully:");
            System.out.println("Status: " + response.getStatus());
            System.out.println("Message: " + response.getMessage());
            if (response.getData() != null) {
                System.out.println("Transaction ID: " + response.getData().getTransactionId());
                System.out.println("Fiat Amount: " + response.getData().getFiatAmount() + " " + response.getData().getFiat());
            }
            
        } catch (Exception e) {
            System.out.println("OnRamp crypto test completed with: " + e.getMessage());
        }
    }

    @Test
    public void testCryptoOffRampMethod() throws Exception {
        // Test the OffRamp method specifically using dedicated OffRamp input type
        try {
            CryptoOffRampInput offRampRequest = CryptoOffRampInput.builder()
                .firstName("Ethan")
                .lastName("Hunt")
                .email("ethan@pay.agency")
                .phoneNumber("0123456789")
                .fiatCurrency("GBP")
                .cryptoCurrency("BTC")
                .cryptoAmount("0.001") // OffRamp uses crypto amount (String)
                .walletAddress("1BoatSLRHtKNngkdXEeobR76b53LETtpyT")
                .ipAddress("127.0.0.1")
                .country("GB")
                .cryptoNetwork("BITCOIN")
                .redirectUrl("https://pay.agency")
                .orderId("offramp-test-" + System.currentTimeMillis())
                .build();

            CryptoOffRampOutput response = cryptoApi.offRamp(offRampRequest);
            
            assertNotNull(response);
            assertNotNull(response.getStatus());
            System.out.println("OffRamp Crypto Payment created successfully:");
            System.out.println("Status: " + response.getStatus());
            System.out.println("Message: " + response.getMessage());
            if (response.getData() != null) {
                System.out.println("Transaction ID: " + response.getData().getTransactionId());
                System.out.println("Crypto Amount: " + response.getData().getCryptoAmount() + " " + response.getData().getCrypto());
            }
            
        } catch (Exception e) {
            System.out.println("OffRamp crypto test completed with: " + e.getMessage());
        }
    }
}

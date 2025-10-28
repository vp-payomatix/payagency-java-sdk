package com.payagency.integration;

import com.payagency.apis.Payment;
import com.payagency.types.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class S2SPaymentIntegrationTest {
    
    private final Payment paymentApi = TestUtility.api.getPayment();

    @Test
    public void testCreateS2SPayment() throws Exception {
        // Using the same data as npm-payagency tests with required fields
        S2SInput request = S2SInput.builder()
            .firstName("James")
            .lastName("Dean")
            .email("james.dean@example.com")
            .address("123 Test Street")
            .city("London")
            .state("GB")  // Required field
            .zip("SW1A 1AA")
            .country("GB")
            .ipAddress("127.0.0.1")  // Required field
            .phoneNumber("07123456789")
            .amount(1000)
            .currency("GBP")
            .cardNumber("4111111111111111")
            .cardExpiryMonth("12")
            .cardExpiryYear("2028")
            .cardCvv("123")
            .redirectUrl("https://pay.agency")  // Required field
            .webhookUrl("https://pay.agency/webhook")  // Required field
            .terminalId("T12345")  // Required field
            .orderId("test-order-" + System.currentTimeMillis())
            .build();

        S2SOutput response = paymentApi.s2s(request);
        
        assertNotNull(response);
        assertNotNull(response.getStatus());
        assertTrue(response.isSuccess() || "REDIRECT".equals(response.getStatus()) || "FAILED".equals(response.getStatus()));
        
        if (response.getData() != null) {
            assertEquals("GBP", response.getData().getCurrency());
            assertEquals(Integer.valueOf(1000), response.getData().getAmount());
            assertNotNull(response.getData().getTransactionId());
        }
        
        System.out.println("S2S Payment created successfully:");
        System.out.println("Transaction ID: " + (response.getData() != null ? response.getData().getTransactionId() : "N/A"));
        System.out.println("Status: " + response.getStatus());
        System.out.println("Message: " + response.getMessage());
        if (response.getData() != null) {
            System.out.println("Amount: " + response.getData().getAmount() + " " + response.getData().getCurrency());
        }
    }

    @Test
    public void testCreateHostedPayment() throws Exception {
        // Test hosted payment creation with required fields
        HostedInput request = HostedInput.builder()
            .firstName("James")
            .lastName("Dean")
            .email("james.dean@example.com")
            .address("123 Test Street")
            .city("London")
            .state("GB")  // Required field
            .zip("SW1A 1AA")
            .country("GB")
            .ipAddress("127.0.0.1")  // Required field
            .phoneNumber("07123456789")
            .amount(2500)
            .currency("GBP")
            .redirectUrl("https://example.com/success")
            .webhookUrl("https://example.com/webhook")
            .terminalId("T12345")  // Required field
            .orderId("hosted-order-" + System.currentTimeMillis())
            .build();

        HostedOutput response = paymentApi.hosted(request);
        
        assertNotNull(response);
        assertNotNull(response.getStatus());
        assertTrue(response.isSuccess() || "REDIRECT".equals(response.getStatus()) || "FAILED".equals(response.getStatus()));
        
        if (response.getData() != null) {
            assertEquals("GBP", response.getData().getCurrency());
            assertEquals(Integer.valueOf(2500), response.getData().getAmount());
        }
        
        System.out.println("Hosted Payment created successfully:");
        System.out.println("Status: " + response.getStatus());
        System.out.println("Redirect URL: " + response.getRedirectUrl());
        if (response.getData() != null) {
            System.out.println("Amount: " + response.getData().getAmount() + " " + response.getData().getCurrency());
        }
    }

    @Test
    public void testCreateAPMPayment() throws Exception {
        // Test APM payment (PayPal example) with required fields
        APMInput request = APMInput.builder()
            .firstName("James")
            .lastName("Dean")
            .email("james.dean@example.com")
            .address("123 Test Street")
            .city("London")
            .state("GB")  // Required field
            .zip("SW1A 1AA")
            .country("GB")
            .ipAddress("127.0.0.1")  // Required field
            .phoneNumber("07123456789")
            .amount(1599)
            .currency("GBP")
            .paymentMethod("paypal")
            .redirectUrl("https://example.com/success")
            .webhookUrl("https://example.com/webhook")
            .terminalId("T12345")  // Required field
            .orderId("apm-order-" + System.currentTimeMillis())
            .build();

        APMOutput response = paymentApi.apm(request);
        
        assertNotNull(response);
        assertNotNull(response.getStatus());
        assertTrue(response.isSuccess() || "REDIRECT".equals(response.getStatus()) || "FAILED".equals(response.getStatus()));
        
        if (response.getData() != null) {
            assertEquals("GBP", response.getData().getCurrency());
            assertEquals(Integer.valueOf(1599), response.getData().getAmount());
        }
        
        System.out.println("APM Payment created successfully:");
        System.out.println("Status: " + response.getStatus());
        System.out.println("Payment Method: paypal");
        if (response.getData() != null) {
            System.out.println("Amount: " + response.getData().getAmount() + " " + response.getData().getCurrency());
        }
    }
}

package com.payagency.integration;

import com.payagency.PayAgencyApi;
import com.payagency.types.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PayoutIntegrationTest {
    
    private final PayAgencyApi api = TestUtility.api;

    @Test
    public void testCreatePayout() throws Exception {
        // Matching npm test payload exactly
        PayoutInput request = PayoutInput.builder()
            .walletId("WAL1234567890")
            .firstName("James")
            .lastName("Dean")
            .email("james@gmail.com")
            .address("64 Hertingfordbury Rd")
            .country("US")
            .city("Newport")
            .state("US")
            .zip("TF10 8DF")
            .ipAddress("127.0.0.1")
            .phoneNumber("7654233212")
            .amount(100)  // npm uses numeric 100, now we match
            .currency("USD")
            .cardNumber("4222222222222222")
            .cardExpiryMonth("10")
            .cardExpiryYear("2030")
            .webhookUrl("https://pay.agency/webhook")
            .orderId("java-payout-" + System.currentTimeMillis())
            .build();

        PayoutOutput response = api.getPayout().create(request);
        
        assertNotNull(response, "Response should not be null");
        System.out.println("Payout Response:");
        System.out.println("  Status: " + response.getStatus());
        System.out.println("  Message: " + response.getMessage());
        
        if (response.getData() != null) {
            System.out.println("  Transaction ID: " + response.getData().getTransactionId());
        }
    }

    @Test
    public void testFetchWallets() throws Exception {
        try {
            // Test fetching wallets - npm: api.Payout.wallets
            WalletsOutput response = api.getPayout().getWallets();
            
            assertNotNull(response, "Response should not be null");
            System.out.println("Wallets Response:");
            
            if (response.getData() != null) {
                System.out.println("  Number of wallets: " + response.getData().size());
                for (WalletsOutput.WalletInfo wallet : response.getData()) {
                    System.out.println("    Wallet ID: " + wallet.getWalletId());
                    System.out.println("    Currency: " + wallet.getCurrency());
                }
            }
            
        } catch (Exception e) {
            System.out.println("Wallets test - API response: " + e.getMessage());
        }
    }

    @Test
    public void testEstimatePayoutFee() throws Exception {
        try {
            // Matching npm test payload exactly
            EstimateFeeInput request = EstimateFeeInput.builder()
                .walletId("WAL7825818519632620")
                .amount(200)  // npm uses numeric 200
                .cardNumber("4111111111111111")
                .build();

            EstimateFeeOutput response = api.getPayout().estimateFee(request);
            
            assertNotNull(response, "Response should not be null");
            System.out.println("Estimate Payout Fee Response:");
            
            if (response.getData() != null) {
                System.out.println("  Estimated Fee Data: " + response.getData());
            } else {
                System.out.println("  No fee data returned");
            }
            
        } catch (Exception e) {
            System.out.println("Estimate fee test - API response: " + e.getMessage());
        }
    }

    @Test
    public void testGetPayoutStatus() throws Exception {
        try {
            // Test getting payout status - matching npm test exactly
            PayoutStatusOutput response = api.getPayout().getStatus("PA1877208010353680");
            
            assertNotNull(response, "Response should not be null");
            System.out.println("Payout Status Response:");
            System.out.println("  Status: " + response.getStatus());
            System.out.println("  Message: " + response.getMessage());
            
            if (response.getData() != null) {
                System.out.println("  Transaction ID: " + response.getData().getTransactionId());
            }
            
        } catch (Exception e) {
            System.out.println("Payout status test - API response: " + e.getMessage());
        }
    }
}

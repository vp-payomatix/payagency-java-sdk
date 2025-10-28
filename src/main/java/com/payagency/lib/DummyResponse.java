package com.payagency.lib;

import com.payagency.types.EstimateFeeOutput;
import com.payagency.types.PaymentTemplatesOutput;
import com.payagency.types.WalletsOutput;

import java.util.Arrays;
import java.util.Collections;

/**
 * Dummy/Mock responses for test environment.
 * Matches the npm-payagency library's test data exactly.
 */
public class DummyResponse {
    
    /**
     * Test wallets data - matches npm testWallets
     */
    public static WalletsOutput getTestWallets() {
        WalletsOutput response = new WalletsOutput();
        
        WalletsOutput.WalletInfo wallet1 = new WalletsOutput.WalletInfo();
        wallet1.setWalletId("WAL1234567890");
        wallet1.setCurrency("USD");
        wallet1.setAmount("10000");
        wallet1.setPaymentMethod("card");
        wallet1.setStatus("Active");
        
        WalletsOutput.WalletInfo wallet2 = new WalletsOutput.WalletInfo();
        wallet2.setWalletId("WAL9876543210");
        wallet2.setCurrency("EUR");
        wallet2.setAmount("5000");
        wallet2.setPaymentMethod("card");
        wallet2.setStatus("Inactive");
        
        response.setData(Arrays.asList(wallet1, wallet2));
        return response;
    }
    
    /**
     * Test estimate payout response - matches npm testEstimatePayoutResponse
     */
    public static EstimateFeeOutput getTestEstimatePayoutResponse() {
        EstimateFeeOutput response = new EstimateFeeOutput();
        
        EstimateFeeOutput.EstimateData data = new EstimateFeeOutput.EstimateData();
        data.setAmountRequired("211.5");
        data.setWalletBalance("1000");
        data.setTotalFee("11.5");
        
        response.setData(data);
        return response;
    }
    
    /**
     * Test payment templates response - matches npm empty array response
     */
    public static PaymentTemplatesOutput getTestPaymentTemplates() {
        PaymentTemplatesOutput response = new PaymentTemplatesOutput();
        response.setData(Collections.emptyList());
        return response;
    }
}

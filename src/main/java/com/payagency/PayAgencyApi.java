package com.payagency;

import com.payagency.apis.*;
import com.payagency.client.ApiClient;
import com.payagency.client.PayAgencyClientOptions;
import com.payagency.types.RefundInput;
import com.payagency.types.RefundOutput;

/**
 * Main PayAgency API client for Java applications.
 * 
 * This class provides access to all PayAgency API endpoints including:
 * - Card payments (S2S and Hosted)
 * - Alternative Payment Methods (APM)
 * - Crypto payments and exchanges
 * - Payment links
 * - Payouts
 * - Refunds
 * - Transaction management
 * 
 * Example usage:
 * <pre>
 * {@code
 * PayAgencyClientOptions options = PayAgencyClientOptions.builder()
 *     .encryptionKey("your-encryption-key")
 *     .secretKey("PA_TEST_your-secret-key")
 *     .build();
 *     
 * PayAgencyApi payAgency = new PayAgencyApi(options);
 * 
 * // Create a hosted payment
 * HostedInput input = HostedInput.builder()
 *     .amount("100.00")
 *     .currency("USD")
 *     .orderReference("ORDER-123")
 *     .build();
 *     
 * HostedOutput result = payAgency.getPayment().hosted(input);
 * }
 * </pre>
 */
public class PayAgencyApi {
    private final ApiClient client;
    private final Payment paymentInstance;
    private final Payout payoutInstance;
    private final Refund refundInstance;
    private final PaymentLink paymentLinkInstance;
    private final TXN txnInstance;
    private final Crypto cryptoInstance;

    /**
     * Creates a new PayAgency API client instance.
     * 
     * @param options Configuration options including encryption key and secret key
     * @throws IllegalArgumentException if required options are missing
     */
    public PayAgencyApi(PayAgencyClientOptions options) {
        if (options == null) {
            throw new IllegalArgumentException("PayAgencyClientOptions cannot be null");
        }
        
        this.client = new ApiClient(options);
        
        // Initialize API modules
        this.paymentInstance = new Payment(client);
        this.payoutInstance = new Payout(client);
        this.refundInstance = new Refund(client);
        this.paymentLinkInstance = new PaymentLink(client);
        this.txnInstance = new TXN(client);
        this.cryptoInstance = new Crypto(client);
    }

    /**
     * Get the Payment API instance for card payments, hosted payments, and APM.
     * 
     * @return Payment API instance
     */
    public Payment getPayment() {
        return paymentInstance;
    }

    /**
     * Get the Payout API instance for sending money to wallets and bank accounts.
     * 
     * @return Payout API instance
     */
    public Payout getPayout() {
        return payoutInstance;
    }

    /**
     * Get the PaymentLink API instance for creating and managing payment links.
     * 
     * @return PaymentLink API instance
     */
    public PaymentLink getPaymentLink() {
        return paymentLinkInstance;
    }

    /**
     * Get the TXN API instance for transaction management and queries.
     * 
     * @return TXN API instance
     */
    public TXN getTxn() {
        return txnInstance;
    }

    /**
     * Get the Crypto API instance for cryptocurrency operations.
     * 
     * @return Crypto API instance
     */
    public Crypto getCrypto() {
        return cryptoInstance;
    }

    /**
     * Get the Refund API instance for processing refund transactions.
     * 
     * @return Refund API instance
     */
    public Refund getRefund() {
        return refundInstance;
    }

    /**
     * Get the environment (test or live) based on the secret key.
     * 
     * @return "test" or "live"
     */
    public String getEnvironment() {
        return client.getEnvironment();
    }

    /**
     * Get the base URL being used for API calls.
     * 
     * @return Base URL string
     */
    public String getBaseUrl() {
        return client.getBaseUrl();
    }
}

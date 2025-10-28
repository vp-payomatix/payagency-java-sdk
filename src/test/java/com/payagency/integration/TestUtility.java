package com.payagency.integration;

import com.payagency.PayAgencyApi;
import com.payagency.client.PayAgencyClientOptions;

/**
 * Test utility class with shared API client configuration.
 * Uses the same credentials as npm-payagency tests for consistency.
 */
public class TestUtility {
    
    /**
     * Shared API client instance configured with test credentials.
     * Using the same credentials as npm-payagency for cross-platform verification.
     */
    public static final PayAgencyApi api = new PayAgencyApi(
        PayAgencyClientOptions.builder()
            .baseUrl("https://api.pay.agency")
            .encryptionKey(getEncryptionKey())
            .secretKey(getSecretKey())
            .build()
    );
    
    private static String getEncryptionKey() {
        String envKey = System.getenv("ENCRYPTION_KEY");
        return envKey != null ? envKey : "89ca59fb3b49ada55851021df12cfbc5";
    }
    
    private static String getSecretKey() {
        String envToken = System.getenv("AUTH_TOKEN");
        return envToken != null ? envToken : "PA_TEST_94bf3520bcbe435f2ed558c31ac664f3e72dfa3114a3232e436e25f9";
    }
}

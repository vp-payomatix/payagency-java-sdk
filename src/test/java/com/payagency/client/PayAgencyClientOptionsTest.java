package com.payagency.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for PayAgencyClientOptions.
 */
class PayAgencyClientOptionsTest {
    
    @Test
    void testValidOptionsBuilding() {
        PayAgencyClientOptions options = PayAgencyClientOptions.builder()
            .encryptionKey("test-encryption-key-32-characters")
            .secretKey("PA_TEST_test-secret-key")
            .baseUrl("https://custom.api.com")
            .build();
            
        assertEquals("test-encryption-key-32-characters", options.getEncryptionKey());
        assertEquals("PA_TEST_test-secret-key", options.getSecretKey());
        assertEquals("https://custom.api.com", options.getBaseUrl());
    }
    
    @Test
    void testDefaultBaseUrl() {
        PayAgencyClientOptions options = PayAgencyClientOptions.builder()
            .encryptionKey("test-encryption-key")
            .secretKey("PA_TEST_test-secret-key")
            .build();
            
        assertEquals("https://backend.pay.agency", options.getBaseUrl());
    }
    
    @Test
    void testMissingEncryptionKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            PayAgencyClientOptions.builder()
                .secretKey("PA_TEST_test-secret-key")
                .build();
        });
    }
    
    @Test
    void testMissingSecretKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            PayAgencyClientOptions.builder()
                .encryptionKey("test-encryption-key")
                .build();
        });
    }
    
    @Test
    void testEmptyEncryptionKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            PayAgencyClientOptions.builder()
                .encryptionKey("")
                .secretKey("PA_TEST_test-secret-key")
                .build();
        });
    }
    
    @Test
    void testEmptySecretKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            PayAgencyClientOptions.builder()
                .encryptionKey("test-encryption-key")
                .secretKey("")
                .build();
        });
    }
    
    @Test
    void testInvalidSecretKeyPrefix() {
        assertThrows(IllegalArgumentException.class, () -> {
            PayAgencyClientOptions.builder()
                .encryptionKey("test-encryption-key")
                .secretKey("INVALID_test-secret-key")
                .build();
        });
    }
    
    @Test
    void testValidTestSecretKey() {
        PayAgencyClientOptions options = PayAgencyClientOptions.builder()
            .encryptionKey("test-encryption-key")
            .secretKey("PA_TEST_test-secret-key")
            .build();
            
        assertNotNull(options);
    }
    
    @Test
    void testValidLiveSecretKey() {
        PayAgencyClientOptions options = PayAgencyClientOptions.builder()
            .encryptionKey("test-encryption-key")
            .secretKey("PA_LIVE_live-secret-key")
            .build();
            
        assertNotNull(options);
    }
}

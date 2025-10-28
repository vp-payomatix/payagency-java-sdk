package com.payagency.client;

/**
 * Configuration options for PayAgency API client.
 * 
 * This class contains all the necessary configuration parameters to initialize
 * the PayAgency API client, including encryption keys, authentication, and
 * environment settings.
 */
public class PayAgencyClientOptions {
    private final String encryptionKey;
    private final String secretKey;
    private final String baseUrl;

    private PayAgencyClientOptions(Builder builder) {
        this.encryptionKey = builder.encryptionKey;
        this.secretKey = builder.secretKey;
        this.baseUrl = builder.baseUrl;
    }

    /**
     * Get the encryption key used for AES-256-CBC encryption.
     * 
     * @return encryption key
     */
    public String getEncryptionKey() {
        return encryptionKey;
    }

    /**
     * Get the secret key for API authentication.
     * 
     * @return secret key (PA_TEST_ or PA_LIVE_ prefixed)
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Get the base URL for API endpoints.
     * 
     * @return base URL, defaults to https://backend.pay.agency if not specified
     */
    public String getBaseUrl() {
        return baseUrl != null ? baseUrl : "https://backend.pay.agency";
    }

    /**
     * Create a new builder instance.
     * 
     * @return new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for PayAgencyClientOptions.
     */
    public static class Builder {
        private String encryptionKey;
        private String secretKey;
        private String baseUrl;

        private Builder() {}

        /**
         * Set the encryption key for data encryption.
         * 
         * @param encryptionKey AES encryption key
         * @return builder instance
         */
        public Builder encryptionKey(String encryptionKey) {
            this.encryptionKey = encryptionKey;
            return this;
        }

        /**
         * Set the secret key for API authentication.
         * 
         * @param secretKey API secret key (should start with PA_TEST_ or PA_LIVE_)
         * @return builder instance
         */
        public Builder secretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        /**
         * Set the base URL for API endpoints.
         * 
         * @param baseUrl custom base URL (optional)
         * @return builder instance
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Build the PayAgencyClientOptions instance.
         * 
         * @return configured PayAgencyClientOptions
         * @throws IllegalArgumentException if required fields are missing
         */
        public PayAgencyClientOptions build() {
            if (encryptionKey == null || encryptionKey.trim().isEmpty()) {
                throw new IllegalArgumentException("Encryption key is required");
            }
            if (secretKey == null || secretKey.trim().isEmpty()) {
                throw new IllegalArgumentException("Secret key is required");
            }
            if (!secretKey.startsWith("PA_TEST_") && !secretKey.startsWith("PA_LIVE_")) {
                throw new IllegalArgumentException("Secret key must start with PA_TEST_ or PA_LIVE_");
            }
            
            return new PayAgencyClientOptions(this);
        }
    }
}

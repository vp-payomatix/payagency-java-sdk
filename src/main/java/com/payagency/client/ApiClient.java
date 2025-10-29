package com.payagency.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payagency.exceptions.PayAgencyException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * HTTP client for PayAgency API with automatic encryption and authentication.
 * 
 * This class handles:
 * - HTTP requests using OkHttp
 * - Automatic AES-256-CBC encryption of request payloads
 * - Bearer token authentication
 * - Environment detection (test/live)
 * - Request/response logging
 */
public class ApiClient {
    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String ENCRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";
    
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String encryptionKey;
    private final String secretKey;
    private final String baseUrl;
    private final String environment;

    /**
     * Create a new API client with the specified options.
     * 
     * @param options Client configuration options
     */
    public ApiClient(PayAgencyClientOptions options) {
        this.encryptionKey = options.getEncryptionKey();
        this.secretKey = options.getSecretKey();
        this.environment = options.getSecretKey().startsWith("PA_LIVE_") ? "live" : "test";
        
        // Normalize base URL
        String url = options.getBaseUrl();
        if (url != null) {
            url = url.replaceAll("/+$", ""); // Remove trailing slashes
            if (!url.startsWith("https://") && !url.startsWith("http://")) {
                url = "https://" + url;
            }
        }
        this.baseUrl = url != null ? url : "https://backend.pay.agency";
        
        this.objectMapper = new ObjectMapper();
        
        // Configure HTTP client
        this.httpClient = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(this::addAuthenticationHeaders)
            .addInterceptor(this::encryptRequestBody)
            .addInterceptor(this::logRequests)
            .build();
    }

    /**
     * Execute a POST request to the specified endpoint.
     * 
     * @param endpoint API endpoint path
     * @param requestBody Request body object
     * @param responseType Expected response type
     * @param <T> Response type
     * @return Parsed response object
     * @throws PayAgencyException if the request fails
     */
    public <T> T post(String endpoint, Object requestBody, Class<T> responseType) {
        try {
            String json = objectMapper.writeValueAsString(requestBody);
            RequestBody body = RequestBody.create(json, JSON);
            
            Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .post(body)
                .build();
                
            try (Response response = httpClient.newCall(request).execute()) {
                return handleResponse(response, responseType);
            }
        } catch (IOException e) {
            logger.error("Failed to execute POST request to {}: {}", endpoint, e.getMessage());
            throw new PayAgencyException("Request failed: " + e.getMessage(), e);
        }
    }

    /**
     * Execute a POST request to the specified endpoint with query parameters.
     * 
     * @param endpoint API endpoint path
     * @param requestBody Request body object
     * @param responseType Expected response type
     * @param skipEncryption Whether to skip encryption for this request
     * @param <T> Response type
     * @return Parsed response object
     * @throws PayAgencyException if the request fails
     */
    public <T> T post(String endpoint, Object requestBody, Class<T> responseType, boolean skipEncryption) {
        try {
            String json = objectMapper.writeValueAsString(requestBody);
            RequestBody body = RequestBody.create(json, JSON);
            
            String url = baseUrl + endpoint;
            if (skipEncryption) {
                url += "?Skip-Encryption=true";
            }
            
            Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
                
            try (Response response = httpClient.newCall(request).execute()) {
                return handleResponse(response, responseType);
            }
        } catch (IOException e) {
            logger.error("Failed to execute POST request to {}: {}", endpoint, e.getMessage());
            throw new PayAgencyException("Request failed: " + e.getMessage(), e);
        }
    }

    /**
     * Execute a GET request to the specified endpoint.
     * 
     * @param endpoint API endpoint path
     * @param responseType Expected response type
     * @param <T> Response type
     * @return Parsed response object
     * @throws PayAgencyException if the request fails
     */
    public <T> T get(String endpoint, Class<T> responseType) {
        try {
            Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .get()
                .build();
                
            try (Response response = httpClient.newCall(request).execute()) {
                return handleResponse(response, responseType);
            }
        } catch (IOException e) {
            logger.error("Failed to execute GET request to {}: {}", endpoint, e.getMessage());
            throw new PayAgencyException("Request failed: " + e.getMessage(), e);
        }
    }

    private <T> T handleResponse(Response response, Class<T> responseType) throws IOException {
        if (!response.isSuccessful()) {
            String errorBody = response.body() != null ? response.body().string() : "No error body";
            logger.error("API request failed with status {}: {}", response.code(), errorBody);
            throw new PayAgencyException("API request failed with status " + response.code() + ": " + errorBody);
        }
        
        if (response.body() == null) {
            throw new PayAgencyException("Response body is null");
        }
        
        String responseBody = response.body().string();
        logger.debug("Response body: {}", responseBody);
        
        return objectMapper.readValue(responseBody, responseType);
    }

    private Response addAuthenticationHeaders(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + secretKey)
            .build();
        return chain.proceed(request);
    }

    private Response encryptRequestBody(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        
        // Skip encryption if explicitly requested
        if (original.url().queryParameter("Skip-Encryption") != null && 
            "true".equals(original.url().queryParameter("Skip-Encryption"))) {
            return chain.proceed(original);
        }
        
        RequestBody originalBody = original.body();
        if (originalBody != null && original.method().equals("POST")) {
            try {
                // Read the original body
                okio.Buffer buffer = new okio.Buffer();
                originalBody.writeTo(buffer);
                String originalBodyString = buffer.readUtf8();
                
                // Encrypt the body
                String encryptedPayload = encryptData(originalBodyString, encryptionKey);
                
                // Create new encrypted body
                String encryptedJson = "{\"payload\":\"" + encryptedPayload + "\"}";
                RequestBody encryptedBody = RequestBody.create(encryptedJson, JSON);
                
                Request encryptedRequest = original.newBuilder()
                    .method(original.method(), encryptedBody)
                    .build();
                    
                return chain.proceed(encryptedRequest);
            } catch (Exception e) {
                logger.error("Failed to encrypt request body: {}", e.getMessage());
                throw new IOException("Encryption failed", e);
            }
        }
        
        return chain.proceed(original);
    }

    private Response logRequests(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.nanoTime();
        
        logger.debug("Sending {} request to {}", request.method(), request.url());
        
        Response response = chain.proceed(request);
        
        long endTime = System.nanoTime();
        long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        
        logger.debug("Received response {} in {}ms", response.code(), duration);
        
        return response;
    }

    /**
     * Encrypt data using AES-256-CBC encryption.
     * 
     * @param data Data to encrypt
     * @param key Encryption key
     * @return Encrypted data as hex string with IV prefix
     */
    private String encryptData(String data, String key) {
        try {
            // Generate random IV
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            // Create cipher
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            
            // Encrypt data
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            
            // Return IV + encrypted data as hex
            return bytesToHex(iv) + ":" + bytesToHex(encrypted);
        } catch (Exception e) {
            throw new PayAgencyException("Failed to encrypt data: " + e.getMessage(), e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    /**
     * Get the current environment (test or live).
     * 
     * @return "test" or "live"
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * Get the base URL being used for API calls.
     * 
     * @return Base URL
     */
    public String getBaseUrl() {
        return baseUrl;
    }
}

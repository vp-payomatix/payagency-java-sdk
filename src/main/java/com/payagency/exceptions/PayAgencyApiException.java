package com.payagency.exceptions;

/**
 * Exception thrown when PayAgency API returns an error response.
 * 
 * This exception contains additional information about API errors,
 * including error codes and detailed error messages from the API.
 */
public class PayAgencyApiException extends PayAgencyException {
    private final String errorCode;
    private final int httpStatusCode;
    
    /**
     * Create a new PayAgencyApiException with the specified message.
     * 
     * @param message Error message from the API
     */
    public PayAgencyApiException(String message) {
        super(message);
        this.errorCode = null;
        this.httpStatusCode = 0;
    }
    
    /**
     * Create a new PayAgencyApiException with the specified message and HTTP status code.
     * 
     * @param message Error message from the API
     * @param httpStatusCode HTTP status code from the API response
     */
    public PayAgencyApiException(String message, int httpStatusCode) {
        super(message);
        this.errorCode = null;
        this.httpStatusCode = httpStatusCode;
    }
    
    /**
     * Create a new PayAgencyApiException with detailed error information.
     * 
     * @param message Error message from the API
     * @param errorCode Specific error code from the API
     * @param httpStatusCode HTTP status code from the API response
     */
    public PayAgencyApiException(String message, String errorCode, int httpStatusCode) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }
    
    /**
     * Create a new PayAgencyApiException with the specified message and cause.
     * 
     * @param message Error message
     * @param cause The underlying cause of the exception
     */
    public PayAgencyApiException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
        this.httpStatusCode = 0;
    }
    
    /**
     * Get the API-specific error code, if available.
     * 
     * @return Error code or null if not available
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * Get the HTTP status code from the API response.
     * 
     * @return HTTP status code or 0 if not available
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}

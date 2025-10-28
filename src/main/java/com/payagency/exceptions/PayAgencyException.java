package com.payagency.exceptions;

/**
 * Base exception class for all PayAgency SDK related errors.
 * 
 * This exception is thrown when there are issues with SDK operations,
 * configuration, or internal processing errors.
 */
public class PayAgencyException extends RuntimeException {
    
    /**
     * Create a new PayAgencyException with the specified message.
     * 
     * @param message Error message
     */
    public PayAgencyException(String message) {
        super(message);
    }
    
    /**
     * Create a new PayAgencyException with the specified message and cause.
     * 
     * @param message Error message
     * @param cause The underlying cause of the exception
     */
    public PayAgencyException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Create a new PayAgencyException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public PayAgencyException(Throwable cause) {
        super(cause);
    }
}

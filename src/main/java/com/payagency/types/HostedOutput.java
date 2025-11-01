package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Response type for hosted payment operations.
 * Extends PaymentResponse with hosted-specific functionality.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HostedOutput extends PaymentResponse {
    // Inherits all functionality from PaymentResponse
    // Additional hosted-specific fields can be added here if needed
}

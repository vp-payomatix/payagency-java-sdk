package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Response type for Alternative Payment Method (APM) operations.
 * Extends PaymentResponse with APM-specific functionality.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APMOutput extends PaymentResponse {
    // Inherits all functionality from PaymentResponse
    // Additional APM-specific fields can be added here if needed
}

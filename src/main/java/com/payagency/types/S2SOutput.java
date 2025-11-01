package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Response type for Server-to-Server (S2S) payment operations.
 * Extends PaymentResponse with S2S-specific functionality.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class S2SOutput extends PaymentResponse {
    // Inherits all functionality from PaymentResponse
    // Additional S2S-specific fields can be added here if needed
}

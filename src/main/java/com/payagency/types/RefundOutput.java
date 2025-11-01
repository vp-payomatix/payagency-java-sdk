package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Response type for refund operations.
 * Extends PaymentResponse with refund-specific functionality.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefundOutput extends PaymentResponse {
    // Inherits all functionality from PaymentResponse
    // Additional refund-specific fields can be added here if needed
}

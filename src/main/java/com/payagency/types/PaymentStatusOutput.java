package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Response type for payment status queries.
 * Extends PaymentResponse and adds transaction status information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentStatusOutput extends PaymentResponse {
    // Inherits from PaymentResponse for consistency
    // Additional status-specific fields can be added here if needed
}

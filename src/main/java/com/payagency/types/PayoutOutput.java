package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Response type for payout operations.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayoutOutput extends PaymentResponse {
    // Inherits from PaymentResponse for consistency
}

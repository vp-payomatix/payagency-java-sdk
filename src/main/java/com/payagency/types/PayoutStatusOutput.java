package com.payagency.types;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Response type for payout status queries.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayoutStatusOutput extends PaymentResponse {
    // Inherits from PaymentResponse for consistency
}

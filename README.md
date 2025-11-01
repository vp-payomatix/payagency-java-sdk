# PayAgency Java SDK

A comprehensive Java SDK for integrating with PayAgency's payment processing API. This SDK provides support for various payment methods including card payments, crypto payments, payouts, and payment links with built-in encryption.

[![JitPack](https://jitpack.io/v/vp-payomatix/payagency-java-sdk.svg)](https://jitpack.io/#vp-payomatix/payagency-java-sdk)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Features

- **Card Payments**: Server-to-Server (S2S) and Hosted payment solutions
- **Alternative Payment Methods (APM)**: Support for various payment providers
- **Crypto Payments**: On-ramp, off-ramp, and crypto payment processing
- **Payment Links**: Create and manage payment links
- **Payouts**: Send money to wallets and bank accounts
- **Refunds**: Process refund transactions
- **Transaction Management**: Query and manage transactions with cursor-based pagination
- **Built-in Encryption**: Automatic AES-256-CBC encryption for sensitive data
- **Environment Support**: Test and live environment configurations
- **Robust Error Handling**: Comprehensive exception handling with detailed error messages
- **JSON Optimization**: Smart null value handling with @JsonInclude annotations

## Installation

### Maven (Recommended - No Authentication Required)

Add the JitPack repository and dependency to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.vp-payomatix</groupId>
        <artifactId>payagency-java-sdk</artifactId>
        <version>v1.0.0</version>
    </dependency>
</dependencies>
```

### Gradle (JitPack)

Add JitPack repository and dependency to your `build.gradle`:

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.vp-payomatix:payagency-java-sdk:v1.0.0'
}
```

### Alternative: GitHub Packages (Enterprise)

For enterprise users who prefer GitHub Packages, see [GitHub Packages Installation Guide](#github-packages-installation).

## Quick Start

```java
import com.payagency.PayAgencyApi;
import com.payagency.client.PayAgencyClientOptions;
import com.payagency.types.*;

public class Example {
    public static void main(String[] args) {
        // Initialize the client
        PayAgencyClientOptions options = PayAgencyClientOptions.builder()
            .encryptionKey("89ca59fb3b49ada55851021df12cfbc5") // 32-character encryption key
            .secretKey("PA_TEST_your-secret-key") // Use PA_LIVE_ for production
            .baseUrl("https://backend.pay.agency") // Optional - defaults to https://backend.pay.agency
            .build();

        PayAgencyApi payAgency = new PayAgencyApi(options);

        try {
            // Create a Server-to-Server payment
            S2SInput s2sInput = S2SInput.builder()
                .firstName("James")
                .lastName("Dean")
                .email("james@gmail.com")
                .address("64 Hertingfordbury Rd")
                .country("GB")
                .city("Newport")
                .state("GB")
                .zip("TF10 8DF")
                .ipAddress("127.0.0.1")
                .phoneNumber("7654233212")
                .amount(100) // Integer amount: 100 = $100.00
                .currency("GBP")
                .cardNumber("4111111111111111")
                .cardExpiryMonth("12")
                .cardExpiryYear("2027")
                .cardCvv("029")
                .redirectUrl("https://pay.agency")
                .webhookUrl("https://pay.agency/webhook")
                .orderId("ORDER_123")
                .terminalId("T12345")
                .build();

            S2SOutput response = payAgency.getPayment().s2s(s2sInput);
            System.out.println("Payment Status: " + response.getStatus());
            System.out.println("Transaction ID: " + response.getData().getTransactionId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## API Reference

### Payment Methods

#### Server-to-Server (S2S) Payment

```java
S2SInput s2sInput = S2SInput.builder()
    .firstName("James")
    .lastName("Dean")
    .email("james@gmail.com")
    .address("64 Hertingfordbury Rd")
    .country("GB")
    .city("Newport")
    .state("GB")
    .zip("TF10 8DF")
    .ipAddress("127.0.0.1")
    .phoneNumber("7654233212")
    .amount(100) // Integer: 100 = $100.00
    .currency("GBP")
    .cardNumber("4111111111111111")
    .cardExpiryMonth("12")
    .cardExpiryYear("2027")
    .cardCvv("029")
    .redirectUrl("https://pay.agency")
    .webhookUrl("https://pay.agency/webhook") // Optional
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .build();

S2SOutput result = payAgency.getPayment().s2s(s2sInput);

// Response format:
// {
//   "status": "SUCCESS" | "REDIRECT" | "FAILED",
//   "message": "string",
//   "data": {
//     "amount": 100,
//     "currency": "GBP",
//     "order_id": "ORDER_123",
//     "transaction_id": "PA5108743179386871",
//     "customer": {
//       "first_name": "James",
//       "last_name": "Dean",
//       "email": "james@gmail.com"
//     },
//     "refund": {
//       "status": false,
//       "refund_date": null
//     },
//     "chargeback": {
//       "status": false,
//       "chargeback_date": null
//     }
//   },
//   "redirect_url": "..." // Present for REDIRECT status
// }
```

#### Hosted Payment

```java
HostedInput hostedInput = HostedInput.builder()
    .firstName("James")
    .lastName("Dean")
    .email("james@gmail.com")
    .address("64 Hertingfordbury Rd")
    .country("GB")
    .city("Newport")
    .state("GB")
    .zip("TF10 8DF")
    .ipAddress("127.0.0.1")
    .phoneNumber("7654233212")
    .amount(100) // Integer: 100 = $100.00
    .currency("GBP")
    .redirectUrl("https://pay.agency")
    .webhookUrl("https://pay.agency/webhook") // Optional
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .build();

HostedOutput result = payAgency.getPayment().hosted(hostedInput);

// Returns the same response format as S2S Payment
```

#### Alternative Payment Methods (APM)

```java
APMInput apmInput = APMInput.builder()
    .firstName("James")
    .lastName("Dean")
    .email("james@gmail.com")
    .address("64 Hertingfordbury Rd")
    .country("GB")
    .city("Newport")
    .state("GB")
    .zip("TF10 8DF")
    .ipAddress("127.0.0.1")
    .phoneNumber("7654233212")
    .amount(100) // Integer: 100 = $100.00
    .currency("GBP")
    .paymentMethod("paypal") // or "googlepay", "applepay", etc.
    .redirectUrl("https://pay.agency")
    .webhookUrl("https://pay.agency/webhook") // Optional
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .build();

APMOutput result = payAgency.getPayment().apm(apmInput);

// Returns the same response format as S2S Payment
```

### Payment Links

```java
// Create payment link
PaymentLinkCreateInput linkInput = PaymentLinkCreateInput.builder()
    .paymentTemplateId("PLI07435325281394735") // Required
    .amount(1000) // Optional - Integer: 1000 = $1000.00
    .currency("USD") // Optional
    .expiryDate("2024-12-31") // Optional
    .terminalId("T12345") // Optional
    .orderId("ORDER_123") // Optional
    .build();

PaymentLinkCreateOutput link = payAgency.getPaymentLink().create(linkInput);

// Response format:
// {
//   "message": "Payment link created successfully",
//   "data": "https://front.pay.agency/pay/PAY_LINK_77641134401078810"
// }

// Get payment templates
PaymentTemplatesOutput templates = payAgency.getPaymentLink().getTemplates();

// Response format:
// {
//   "data": [
//     {
//       "template_id": "string",
//       "template_name": "string",
//       "payment_template_id": "string",
//       "template_screenshot": "string",
//       "redirect_url": "string",
//       "webhook_url": "string"
//     }
//   ]
// }
```

### Payouts

```java
// Create payout
PayoutInput payoutInput = PayoutInput.builder()
    .walletId("WAL1234567890")
    .firstName("James")
    .lastName("Dean")
    .email("james@gmail.com")
    .address("64 Hertingfordbury Rd")
    .country("US")
    .city("Newport")
    .state("US")
    .zip("TF10 8DF")
    .ipAddress("127.0.0.1")
    .phoneNumber("7654233212")
    .amount(100) // Integer: 100 = $100.00
    .currency("USD")
    .cardNumber("4222222222222222")
    .cardExpiryMonth("10")
    .cardExpiryYear("2030")
    .webhookUrl("https://pay.agency/webhook") // Optional
    .orderId("ORDER_123") // Optional
    .build();

PayoutOutput payout = payAgency.getPayout().create(payoutInput);

// Response format:
// {
//   "status": "SUCCESS" | "BLOCKED" | "PENDING",
//   "message": "string",
//   "data": {
//     "amount": 100,
//     "currency": "USD",
//     "order_id": "ORDER_123",
//     "transaction_id": "PA5108743179386871",
//     "customer": {
//       "first_name": "James",
//       "last_name": "Dean",
//       "email": "james@gmail.com"
//     }
//   }
// }

// Get available wallets
WalletsOutput wallets = payAgency.getPayout().getWallets();

// Response format:
// {
//   "data": [
//     {
//       "wallet_id": "WAL1234567890",
//       "currency": "USD",
//       "amount": 10000,
//       "payment_method": "string",
//       "status": "Active"
//     }
//   ]
// }

// Estimate payout fees
EstimateFeeInput estimateInput = EstimateFeeInput.builder()
    .walletId("WAL7825818519632620")
    .amount(200) // Integer: 200 = $200.00
    .cardNumber("4111111111111111")
    .build();

EstimateFeeOutput estimate = payAgency.getPayout().estimateFee(estimateInput);

// Response format:
// {
//   "data": {
//     "amount_required": 211.5,
//     "wallet_balance": 1000.0,
//     "total_fee": 11.5
//   }
// }

// Check payout status
PayoutStatusOutput status = payAgency.getPayout().getPayoutStatus("PAYOUT_REFERENCE_123");

// Response format:
// {
//   "status": "SUCCESS" | "PENDING" | "FAILED",
//   "message": "string",
//   "data": {
//     "amount": 100,
//     "currency": "USD",
//     "order_id": "ORDER_123",
//     "transaction_id": "PA5108743179386871",
//     "customer": {
//       "first_name": "James",
//       "last_name": "Dean",
//       "email": "james@gmail.com"
//     }
//   }
// }
```

### Crypto Operations

```java
// Full crypto payment method - handles OnRamp and OffRamp
CryptoPaymentInput cryptoInput = CryptoPaymentInput.builder()
    .transactionType("ONRAMP") // or "OFFRAMP"
    .firstName("Diana")
    .lastName("Prince")
    .email("diana@pay.agency")
    .phoneNumber("0123456789")
    .fiatAmount(200) // Required for ONRAMP, omit for OFFRAMP
    // .cryptoAmount("0.05") // Required for OFFRAMP, omit for ONRAMP
    .fiatCurrency("EUR")
    .cryptoCurrency("BTC")
    .walletAddress("1BoatSLRHtKNngkdXEeobR76b53LETtpyT")
    .ipAddress("127.0.0.1")
    .country("GB")
    .cryptoNetwork("BITCOIN")
    .redirectUrl("https://pay.agency")
    .webhookUrl("https://pay.agency/webhook") // Optional
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .build();

CryptoPaymentOutput cryptoResult = payAgency.getCrypto().payment(cryptoInput);

// Response format:
// {
//   "status": "REDIRECT" | "PENDING" | "FAILED",
//   "message": "string",
//   "redirect_url": "string",
//   "data": {
//     "transaction_id": "string",
//     "fiat": "EUR",
//     "fiat_amount": 200,
//     "crypto": "BTC",
//     "crypto_amount": 0.05,
//     "customer": {
//       "first_name": "Diana",
//       "last_name": "Prince",
//       "email": "diana@pay.agency"
//     }
//   }
// }

// OnRamp (Fiat to Crypto) - dedicated input/output types
CryptoOnRampInput onRampInput = CryptoOnRampInput.builder()
    .firstName("Diana")
    .lastName("Prince")
    .email("diana@pay.agency")
    .phoneNumber("0123456789")
    .fiatAmount(200) // Integer: 200 = $200.00
    .fiatCurrency("EUR")
    .cryptoCurrency("BTC")
    .walletAddress("1BoatSLRHtKNngkdXEeobR76b53LETtpyT")
    .ipAddress("127.0.0.1")
    .country("GB")
    .cryptoNetwork("BITCOIN")
    .redirectUrl("https://pay.agency")
    .webhookUrl("https://pay.agency/webhook") // Optional
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .build();

CryptoOnRampOutput onRampResult = payAgency.getCrypto().onRamp(onRampInput);

// OffRamp (Crypto to Fiat) - dedicated input/output types
CryptoOffRampInput offRampInput = CryptoOffRampInput.builder()
    .firstName("Ethan")
    .lastName("Hunt")
    .email("ethan@pay.agency")
    .phoneNumber("0123456789")
    .fiatCurrency("GBP")
    .cryptoCurrency("BTC")
    .cryptoAmount("0.05") // String format for precise decimals
    .walletAddress("1BoatSLRHtKNngkdXEeobR76b53LETtpyT")
    .ipAddress("127.0.0.1")
    .country("GB")
    .cryptoNetwork("BITCOIN")
    .redirectUrl("https://pay.agency")
    .webhookUrl("https://pay.agency/webhook") // Optional
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .build();

CryptoOffRampOutput offRampResult = payAgency.getCrypto().offRamp(offRampInput);

// Get supported currencies
CryptoCurrenciesInput currenciesInput = CryptoCurrenciesInput.builder()
    .country("GB") // ISO 3166-1 alpha-2 country code
    .amount(100)
    .build();

CryptoCurrenciesOutput currencies = payAgency.getCrypto().getCurrencies(currenciesInput);

// Response format:
// {
//   "message": "string",
//   "data": [
//     {
//       "name": "Bitcoin",
//       "code": "BTC",
//       "symbol": "₿"
//     }
//   ]
// }

// Crypto Payment Links

// Full crypto payment link method - handles OnRamp, OffRamp, and PayIn based on transaction_type
CryptoPaymentLinkInput cryptoPaymentLinkInput = CryptoPaymentLinkInput.builder()
    .transactionType("ONRAMP") // or "OFFRAMP" or "PAYIN"
    .paymentTemplateId("PLI07435325281394735")
    .fiatAmount(100) // Required for ONRAMP and PAYIN
    // .cryptoAmount("0.01") // Required for OFFRAMP
    .fiatCurrency("GBP")
    .cryptoCurrency("BTC")
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .expiryDate("2024-12-31") // Optional
    .build();

CryptoPaymentLinkOutput cryptoPaymentLink = payAgency.getCrypto().paymentLink(cryptoPaymentLinkInput);

// OnRamp payment link (Fiat to Crypto)
CryptoOnRampLinkInput onRampLinkInput = CryptoOnRampLinkInput.builder()
    .paymentTemplateId("PLI07435325281394735")
    .fiatAmount(100)
    .fiatCurrency("GBP")
    .cryptoCurrency("BTC")
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .expiryDate("2024-12-31") // Optional
    .build();

CryptoPaymentLinkOutput onRampLink = payAgency.getCrypto().onRampLink(onRampLinkInput);

// OffRamp payment link (Crypto to Fiat)
CryptoOffRampLinkInput offRampLinkInput = CryptoOffRampLinkInput.builder()
    .paymentTemplateId("PLI07435325281394735")
    .cryptoAmount("0.01")
    .cryptoCurrency("BTC")
    .fiatCurrency("GBP")
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .expiryDate("2024-12-31") // Optional
    .build();

CryptoPaymentLinkOutput offRampLink = payAgency.getCrypto().offRampLink(offRampLinkInput);

// PayIn payment link
CryptoPayinLinkInput payinLinkInput = CryptoPayinLinkInput.builder()
    .paymentTemplateId("PLI07435325281394735")
    .fiatAmount(150)
    .fiatCurrency("USD")
    .cryptoCurrency("BTC")
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .expiryDate("2024-12-31") // Optional
    .build();

CryptoPaymentLinkOutput payinLink = payAgency.getCrypto().payinLink(payinLinkInput);

// Response format for all payment links:
// {
//   "message": "Payment link created successfully",
//   "data": "https://front.pay.agency/pay/CRYPTO_LINK_123456"
// }

// Direct crypto PayIn transaction
CryptoPayinInput payinInput = CryptoPayinInput.builder()
    .firstName("Fiona")
    .lastName("Gallagher")
    .email("hello@gmail.com")
    .address("64 Hertingfordbury Rd")
    .phoneNumber("0123456789")
    .ipAddress("127.0.0.1")
    .cryptoCurrency("BTC")
    .amount(300)
    .currency("USD")
    .cryptoNetwork("BITCOIN")
    .country("US")
    .redirectUrl("https://pay.agency")
    .webhookUrl("https://pay.agency/webhook") // Optional
    .orderId("ORDER_123") // Optional
    .terminalId("T12345") // Optional
    .build();

CryptoPayinOutput payinResult = payAgency.getCrypto().payin(payinInput);

// Response format for PayIn:
// {
//   "status": "SUCCESS" | "PENDING" | "FAILED",
//   "message": "string",
//   "redirect_url": "string",
//   "data": {
//     "amount": 300,
//     "currency": "USD",
//     "order_id": "ORDER_123",
//     "transaction_id": "PA5108743179386871",
//     "customer": {
//       "first_name": "Fiona",
//       "last_name": "Gallagher",
//       "email": "hello@gmail.com"
//     },
//     "crypto_currency": "BTC"
//   }
// }
```

### Refunds

```java
RefundInput refundInput = RefundInput.builder()
    .reason("Customer request")
    .transactionId("PA5108743179386871")
    .build();

RefundOutput refund = payAgency.getRefund().create(refundInput);

// Response format:
// {
//   "status": "SUCCESS",
//   "message": "string",
//   "data": {
//     "amount": 100,
//     "currency": "USD",
//     "order_id": "ORDER_123",
//     "transaction_id": "PA5108743179386871",
//     "customer": {
//       "first_name": "James",
//       "last_name": "Dean",
//       "email": "james@gmail.com"
//     },
//     "refund": {
//       "status": true,
//       "refund_date": "2024-01-15T10:30:00Z"
//     },
//     "chargeback": {
//       "status": false,
//       "chargeback_date": null
//     }
//   }
// }
```

### Transaction Management

```java
// Get transactions
TransactionsInput txnInput = TransactionsInput.builder()
    .transactionStartDate("2023-01-01") // Optional
    .transactionEndDate("2023-12-31") // Optional
    .nextCursor("cursor_value") // Optional - for pagination
    .prevCursor("cursor_value") // Optional - for pagination
    .build();

TransactionsOutput transactions = payAgency.getTxn().getTransactions(txnInput);

// Response format:
// {
//   "message": "Transactions fetched successfully",
//   "data": [
//     {
//       "first_name": "James",
//       "last_name": "Dean",
//       "transaction_id": "PA5108743179386871",
//       "amount": "100",
//       "currency": "GBP",
//       "status": "SUCCESS",
//       "card_type": "VISA",
//       "card_number": "411111XXXXXX1111",
//       "transaction_type": "CARD",
//       "order_id": "ORDER_123",
//       "country": "GB",
//       "email": "james@gmail.com",
//       "phone_number": "7654233212",
//       "address": "64 Hertingfordbury Rd",
//       "created_at": "2024-01-15T10:30:00Z",
//       "refund_date": null,
//       "chargeback_date": null,
//       "suspicious_date": null,
//       "message": "Transaction processed successfully!.",
//       "merchant_connector": {
//         "name": "string"
//       },
//       "user": {
//         "name": "string",
//         "user_kyc": {
//           "name": "string"
//         }
//       }
//     }
//   ],
//   "meta": {
//     "hasNextPage": true,
//     "hasPreviousPage": false,
//     "nextCursor": "OTQy",
//     "prevCursor": "OTcw",
//     "totalCount": 815
//   }
// }

// Pagination metadata contains actual API response data:
// - hasNextPage/hasPreviousPage: Boolean flags for navigation
// - nextCursor/prevCursor: Base64-encoded cursor values for pagination
// - totalCount: Total number of transactions available

// Get wallet transactions
TransactionsInput walletTxnInput = TransactionsInput.builder()
    .transactionStartDate("2023-01-01") // Optional
    .transactionEndDate("2023-12-31") // Optional
    .nextCursor("cursor_value") // Optional
    .prevCursor("cursor_value") // Optional
    .build();

TransactionsOutput walletTransactions = payAgency.getTxn().getWalletTransactions(walletTxnInput);

// Returns the same response format as getTransactions

// Get payment status for a specific transaction
PaymentStatusOutput paymentStatus = payAgency.getTxn().getPaymentStatus("PA5108743179386871");

// Response format:
// {
//   "status": "SUCCESS",
//   "message": "Payment found",
//   "data": {
//     "transaction_id": "PA5108743179386871",
//     "amount": "100",
//     "currency": "GBP",
//     "status": "SUCCESS",
//     "customer": {
//       "first_name": "James",
//       "last_name": "Dean",
//       "email": "james@gmail.com"
//     },
//     "refund": {
//       "status": false,
//       "refund_date": null
//     },
//     "chargeback": {
//       "status": false,
//       "chargeback_date": null
//     }
//   }
// }
```

## Environment Configuration

The SDK automatically detects the environment based on your secret key:

- Test environment: Secret keys starting with `PA_TEST_`
- Live environment: Secret keys starting with `PA_LIVE_`

## Error Handling

The SDK throws specific exceptions for different error scenarios:

```java
try {
    S2SOutput result = payAgency.getPayment().s2s(s2sInput);
    System.out.println("Payment successful: " + result.getData().getTransactionId());
} catch (PayAgencyApiException e) {
    // Server responded with error status
    System.err.println("API Error: " + e.getMessage());
    System.err.println("Error Code: " + e.getErrorCode());
} catch (PayAgencyException e) {
    // Network error or other SDK error
    System.err.println("SDK Error: " + e.getMessage());
} catch (Exception e) {
    // Other unexpected errors
    System.err.println("Unexpected error: " + e.getMessage());
}
```

## Important Notes

- **Payment amounts**: Use Integer values representing actual currency amounts (e.g., 100 for $100.00 or £100.00)
- **Crypto amounts**: For crypto, use String format for precise decimal values (e.g., "0.01" for Bitcoin)
- **Country codes**: Use ISO 3166-1 alpha-2 country codes (e.g., "GB", "US")
- **Currency codes**: Use ISO 4217 currency codes (e.g., "USD", "GBP", "EUR")
- **Crypto networks**: Use uppercase format (e.g., "BITCOIN", "ETHEREUM")
- **Card expiry years**: Use full 4-digit format (e.g., "2027", not "27")
- **Optional fields**: Fields marked as optional can be omitted from the payload

## Security

### Encryption

The SDK automatically encrypts request payloads using AES-256-CBC encryption with your provided encryption key. Some endpoints (like payment links and refunds) skip encryption as indicated by the `Skip-Encryption` parameter.

### API Key Security

- Never expose your API keys in client-side code
- Use test keys (`PA_TEST_`) for development
- Use live keys (`PA_LIVE_`) only in production
- Rotate your keys regularly

### Best Practices

1. Store API keys in environment variables
2. Use HTTPS for all webhook URLs
3. Validate webhook signatures on your server
4. Implement proper error handling
5. Log transactions for auditing

## GitHub Packages Installation

For enterprise users who prefer GitHub Packages:

### Prerequisites

1. Create a GitHub Personal Access Token:

   - Go to: https://github.com/settings/tokens
   - Click "Generate new token (classic)"
   - Select scope: `read:packages`
   - Copy the token

2. Configure Maven settings (`~/.m2/settings.xml`):

```xml
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>YOUR_GITHUB_USERNAME</username>
            <password>YOUR_GITHUB_TOKEN</password>
        </server>
    </servers>
</settings>
```

### Maven Configuration

Add to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/vp-payomatix/payagency-java-sdk</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.payagency</groupId>
        <artifactId>payagency-java-sdk</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### Gradle Configuration

Add to your `build.gradle`:

```gradle
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/vp-payomatix/payagency-java-sdk")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    implementation 'com.payagency:payagency-java-sdk:1.0.0'
}
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For support, email support@pay.agency or visit our [documentation](https://docs.pay.agency).

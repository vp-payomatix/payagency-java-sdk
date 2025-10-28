# Security Policy

## Reporting Security Vulnerabilities

The PayAgency team takes security issues seriously. We appreciate your efforts to responsibly disclose your findings, and will make every effort to acknowledge your contributions.

### Reporting Process

**Please do not report security vulnerabilities through public GitHub issues.**

Instead, please report security vulnerabilities by emailing security@payagency.com.

Include the following information in your report:
- Description of the vulnerability
- Steps to reproduce the issue
- Versions affected
- Any potential impact
- Suggested remediation if you have one

### What to Expect

- We will acknowledge receipt of your report within 48 hours
- We will provide an estimated timeline for resolution
- We will keep you informed of our progress
- We will credit you for the discovery (if desired) once the issue is resolved

### Security Best Practices

When using the PayAgency Java SDK:

#### API Key Security
- **Never commit API keys to version control**
- Store API keys in environment variables or secure configuration files
- Use test keys (`PA_TEST_`) for development and testing only
- Use live keys (`PA_LIVE_`) only in production environments
- Rotate keys regularly
- Restrict API key permissions to minimum required scope

#### Network Security
- Always use HTTPS endpoints (the SDK defaults to HTTPS)
- Validate SSL certificates in production
- Use webhook signature validation for incoming webhook requests
- Implement rate limiting to prevent abuse

#### Data Security
- Never log sensitive payment information (card numbers, CVV, etc.)
- Use the SDK's built-in encryption for sensitive data
- Implement proper data retention policies
- Follow PCI DSS compliance requirements if handling card data

#### Application Security
- Keep the SDK updated to the latest version
- Validate all user inputs
- Implement proper error handling to avoid information disclosure
- Use secure coding practices
- Regularly audit dependencies for vulnerabilities

#### Environment Security
- Use separate API keys for different environments
- Implement proper access controls
- Monitor for suspicious activity
- Set up alerting for failed API requests

### Supported Versions

We provide security updates for the following versions:

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | :white_check_mark: |

### Security Scanning

We recommend using dependency scanning tools such as:
- OWASP Dependency Check
- Snyk
- GitHub Security Advisories

### Compliance

This SDK is designed to help maintain compliance with:
- PCI DSS (Payment Card Industry Data Security Standard)
- GDPR (General Data Protection Regulation)
- SOC 2 Type II

However, compliance is a shared responsibility between PayAgency and our customers. Please ensure your implementation follows security best practices and regulatory requirements.

### Contact

For related questions or concerns:
- General support: support@payagency.com

Thank you for helping keep PayAgency and our users safe!

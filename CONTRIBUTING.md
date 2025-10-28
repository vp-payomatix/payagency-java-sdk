# Contribution Guidelines

Thank you for considering contributing to the PayAgency Java SDK! Here are some guidelines to help you get started.

## Code of Conduct

Please note that this project is released with a [Contributor Code of Conduct](CODE_OF_CONDUCT.md). By participating in this project you agree to abide by its terms.

## How to Contribute

### Reporting Bugs

Before creating bug reports, please check the issue tracker to avoid duplicates. When creating a bug report, include:

- Use a clear and descriptive title
- Describe the exact steps to reproduce the problem
- Provide specific examples to demonstrate the steps
- Describe the behavior you observed after following the steps
- Explain which behavior you expected to see instead and why
- Include Java version, SDK version, and operating system details

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, include:

- Use a clear and descriptive title
- Provide a step-by-step description of the suggested enhancement
- Provide specific examples to demonstrate the steps
- Describe the current behavior and explain which behavior you expected to see instead
- Explain why this enhancement would be useful

### Pull Requests

1. Fork the repository
2. Create a new branch from `main` for your feature or bug fix
3. Make your changes with clear commit messages
4. Add tests for any new functionality
5. Ensure all tests pass
6. Update documentation as needed
7. Submit a pull request

### Development Setup

1. **Prerequisites:**
   - Java 11 or higher
   - Maven 3.6 or higher

2. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/payagency-java-sdk.git
   cd payagency-java-sdk
   ```

3. **Build the project:**
   ```bash
   mvn clean compile
   ```

4. **Run tests:**
   ```bash
   mvn test
   ```

### Coding Standards

- Follow Java naming conventions
- Use meaningful variable and method names
- Add Javadoc comments for public APIs
- Keep methods focused and small
- Add unit tests for new functionality
- Ensure code is properly formatted

### Testing

- Write unit tests for all new functionality
- Ensure all existing tests continue to pass
- Add integration tests where appropriate
- Test with both test and live environments (using test credentials only)

### Security

- Never commit real API keys or credentials
- Use test credentials for examples and tests
- Report security vulnerabilities privately to security@payagency.com

## Questions?

If you have questions about contributing, please create an issue or contact us at support@payagency.com.

Thank you for contributing!

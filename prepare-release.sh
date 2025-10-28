#!/bin/bash

# PayAgency Java SDK Release Script
# This script helps prepare and validate the SDK for open source release

set -e

echo "🚀 PayAgency Java SDK Release Preparation"
echo "========================================"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${GREEN}✓${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}⚠${NC} $1"
}

print_error() {
    echo -e "${RED}✗${NC} $1"
}

# Check if we're in the right directory
if [ ! -f "pom.xml" ]; then
    print_error "pom.xml not found. Please run this script from the project root directory."
    exit 1
fi

# 1. Security Check - Look for actual secrets (not documentation)
echo
echo "1. 🔍 Checking for actual secrets..."
SECRETS_FOUND=false

# Look for actual secret patterns (real API keys with specific length/format)
if grep -r --exclude-dir=target --exclude-dir=.git -E "PA_LIVE_[a-zA-Z0-9]{20,}" . && ! grep -q "PA_LIVE_live-secret-key" .; then
    print_error "Real PA_LIVE_ API key found!"
    SECRETS_FOUND=true
fi

# Look for real encryption keys (exclude known test keys)
EXCLUDED_TEST_KEYS=("89ca59fb3b49ada55851021df12cfbc5" "PA_TEST_94bf3520bcbe435f2ed558c31ac664f3e72dfa3114a3232e436e25f9")
REAL_KEYS_FOUND=$(grep -r --exclude-dir=target --exclude-dir=.git -E "[a-f0-9]{32,}" . || true)
for test_key in "${EXCLUDED_TEST_KEYS[@]}"; do
    REAL_KEYS_FOUND=$(echo "$REAL_KEYS_FOUND" | grep -v "$test_key" || true)
done
if [ -n "$REAL_KEYS_FOUND" ]; then
    print_error "Real encryption key found!"
    echo "$REAL_KEYS_FOUND"
    SECRETS_FOUND=true
fi

# Look for hardcoded credentials (but exclude common test/method names)
CREDENTIAL_PATTERNS=$(grep -r --exclude-dir=target --exclude-dir=.git -E "(password|secret).*[:=].*['\"][a-zA-Z0-9]{10,}" . | grep -vE "(test|example|demo|TODO|FIXME|getSecret|secretKey)" || true)
if [ -n "$CREDENTIAL_PATTERNS" ]; then
    print_error "Potential hardcoded credentials found!"
    echo "$CREDENTIAL_PATTERNS"
    SECRETS_FOUND=true
fi

if [ "$SECRETS_FOUND" = true ]; then
    print_error "Actual secrets detected! Please review and remove before releasing."
    exit 1
else
    print_status "No actual secrets detected in source code"
fi

# 2. Clean build
echo
echo "2. 🧹 Cleaning and building project..."
mvn clean compile
if [ $? -eq 0 ]; then
    print_status "Clean build successful"
else
    print_error "Build failed"
    exit 1
fi

# 3. Run tests (excluding integration tests that require real API keys)
echo
echo "3. 🧪 Running unit tests..."
mvn test -P skip-integration
if [ $? -eq 0 ]; then
    print_status "Unit tests passed"
else
    print_error "Tests failed"
    exit 1
fi

# 4. Generate test coverage report
echo
echo "4. 📊 Generating test coverage report..."
mvn test -P coverage,skip-integration
if [ $? -eq 0 ]; then
    print_status "Coverage report generated in target/site/jacoco/"
else
    print_warning "Coverage report generation failed"
fi

# 5. Check for TODO/FIXME comments
echo
echo "5. 📝 Checking for TODO/FIXME comments..."
TODO_COUNT=$(find src -name "*.java" -exec grep -l "TODO\|FIXME\|XXX" {} \; | wc -l)
if [ $TODO_COUNT -gt 0 ]; then
    print_warning "$TODO_COUNT files contain TODO/FIXME comments"
    find src -name "*.java" -exec grep -Hn "TODO\|FIXME\|XXX" {} \;
else
    print_status "No TODO/FIXME comments found"
fi

# 6. Validate package structure
echo
echo "6. 📦 Validating package structure..."
REQUIRED_FILES=("README.md" "LICENSE" "CODE_OF_CONDUCT.md" "CONTRIBUTING.md" "SECURITY.md")
for file in "${REQUIRED_FILES[@]}"; do
    if [ -f "$file" ]; then
        print_status "$file exists"
    else
        print_error "$file is missing"
        exit 1
    fi
done

# 7. Generate Javadoc
echo
echo "7. 📚 Generating Javadoc..."
mvn javadoc:javadoc
if [ $? -eq 0 ]; then
    print_status "Javadoc generated successfully"
else
    print_error "Javadoc generation failed"
    exit 1
fi

# 8. Create source JAR
echo
echo "8. 📦 Creating source JAR..."
mvn source:jar
if [ $? -eq 0 ]; then
    print_status "Source JAR created successfully"
else
    print_error "Source JAR creation failed"
    exit 1
fi

# 9. Check .gitignore effectiveness
echo
echo "9. 🙈 Checking .gitignore effectiveness..."
UNTRACKED_IMPORTANT=$(git status --porcelain | grep -E '\.(class|jar|log)$' || true)
if [ -n "$UNTRACKED_IMPORTANT" ]; then
    print_warning "Some build artifacts are not ignored by .gitignore:"
    echo "$UNTRACKED_IMPORTANT"
else
    print_status "Build artifacts properly ignored"
fi

# 10. Final validation
echo
echo "10. ✅ Final validation..."
mvn package -P skip-integration
if [ $? -eq 0 ]; then
    print_status "Package creation successful"
else
    print_error "Package creation failed"
    exit 1
fi

echo
echo "🎉 Release preparation completed successfully!"
echo
print_status "The SDK is ready for open source release"
print_status "Generated artifacts:"
echo "   • JAR: target/payagency-java-sdk-*.jar"
echo "   • Sources: target/payagency-java-sdk-*-sources.jar"
echo "   • Javadoc: target/payagency-java-sdk-*-javadoc.jar"
echo "   • Coverage: target/site/jacoco/index.html"
echo

echo "📋 Next steps for release:"
echo "1. Review all generated documentation"
echo "2. Test the JAR in a separate project"
echo "3. Create a git tag for the release"
echo "4. Push to GitHub and create a release"
echo "5. Deploy to Maven Central (if configured)"

echo
print_warning "Remember to:"
echo "• Update version numbers if needed"
echo "• Review and update CHANGELOG.md"
echo "• Test integration with a sample project"
echo "• Verify all examples in README.md work correctly"

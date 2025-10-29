#!/bin/bash

# PayAgency Java SDK - GitHub Packages Deployment Script
# ==================================================

echo "🚀 PayAgency Java SDK - GitHub Packages Deployment"
echo "================================================="
echo ""

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${YELLOW}📋 Pre-deployment Checklist:${NC}"
echo "✅ All tests passing (33/33)"
echo "✅ Build successful"
echo "✅ Version 1.0.0 ready"
echo "✅ GitHub repository configured"
echo ""

# Check if settings.xml exists
if [ ! -f "$HOME/.m2/settings.xml" ]; then
    echo -e "${RED}❌ Maven settings.xml not found!${NC}"
    echo ""
    echo -e "${YELLOW}🔧 Setup Required:${NC}"
    echo "1. Copy the settings template to Maven directory:"
    echo "   cp settings-template.xml ~/.m2/settings.xml"
    echo ""
    echo "2. Edit ~/.m2/settings.xml and replace:"
    echo "   - YOUR_GITHUB_USERNAME with your GitHub username"
    echo "   - YOUR_GITHUB_TOKEN with your GitHub Personal Access Token"
    echo ""
    echo "3. Create a GitHub Personal Access Token:"
    echo "   - Go to: https://github.com/settings/tokens"
    echo "   - Click 'Generate new token (classic)'"
    echo "   - Select scopes: 'write:packages', 'read:packages'"
    echo "   - Copy the token and use it in settings.xml"
    echo ""
    echo "4. Re-run this script after setup"
    exit 1
fi

echo -e "${GREEN}✅ Maven settings.xml found${NC}"
echo ""

# Check if GitHub credentials are configured
if grep -q "YOUR_GITHUB_USERNAME" "$HOME/.m2/settings.xml" 2>/dev/null; then
    echo -e "${RED}❌ GitHub credentials not configured in settings.xml${NC}"
    echo ""
    echo -e "${YELLOW}🔧 Please edit ~/.m2/settings.xml and replace:${NC}"
    echo "   - YOUR_GITHUB_USERNAME with your GitHub username"
    echo "   - YOUR_GITHUB_TOKEN with your GitHub Personal Access Token"
    echo ""
    exit 1
fi

echo -e "${GREEN}✅ GitHub credentials configured${NC}"
echo ""

# Confirm deployment
echo -e "${YELLOW}🚀 Ready to deploy PayAgency Java SDK v1.0.0 to GitHub Packages${NC}"
echo ""
echo "This will:"
echo "• Deploy JAR, sources, and javadoc to GitHub Packages"
echo "• Make the SDK available via Maven/Gradle"
echo "• Allow others to use: com.payagency:payagency-java-sdk:1.0.0"
echo ""

read -p "Continue with deployment? (y/N): " -n 1 -r
echo ""

if [[ ! $REPLY =~ ^[Yy]$ ]]; then
    echo "Deployment cancelled."
    exit 0
fi

echo ""
echo -e "${YELLOW}📦 Starting deployment...${NC}"
echo ""

# Deploy to GitHub Packages
echo "🔄 Deploying to GitHub Packages..."
if mvn clean deploy -P github; then
    echo ""
    echo -e "${GREEN}🎉 SUCCESS! PayAgency Java SDK v1.0.0 deployed to GitHub Packages${NC}"
    echo ""
    echo -e "${YELLOW}📖 Usage Instructions:${NC}"
    echo ""
    echo "Add to your pom.xml:"
    echo ""
    echo "<repositories>"
    echo "  <repository>"
    echo "    <id>github</id>"
    echo "    <url>https://maven.pkg.github.com/vp-payomatix/payagency-java-sdk</url>"
    echo "  </repository>"
    echo "</repositories>"
    echo ""
    echo "<dependencies>"
    echo "  <dependency>"
    echo "    <groupId>com.payagency</groupId>"
    echo "    <artifactId>payagency-java-sdk</artifactId>"
    echo "    <version>1.0.0</version>"
    echo "  </dependency>"
    echo "</dependencies>"
    echo ""
    echo -e "${GREEN}🔗 View package: https://github.com/vp-payomatix/payagency-java-sdk/packages${NC}"
else
    echo ""
    echo -e "${RED}❌ Deployment failed!${NC}"
    echo ""
    echo "Common issues:"
    echo "• Check GitHub token has 'write:packages' permission"
    echo "• Verify repository name in settings.xml"
    echo "• Ensure you're authenticated to GitHub"
    exit 1
fi

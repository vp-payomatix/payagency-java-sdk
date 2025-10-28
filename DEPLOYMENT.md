# PayAgency Java SDK Deployment Guide

## 🚀 Deployment Options

### Option 1: GitHub Package Repository (Recommended for Open Source)

1. **Push to GitHub:**
   ```bash
   git remote add origin https://github.com/vp-payomatix/payagency-java-sdk.git
   git push -u origin main
   git push origin v1.0.15
   ```

2. **Configure GitHub Package Registry:**
   Add to your `~/.m2/settings.xml`:
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

3. **Deploy to GitHub Packages:**
   ```bash
   mvn deploy -DaltDeploymentRepository=github::default::https://maven.pkg.github.com/vp-payomatix/payagency-java-sdk
   ```

### Option 2: Maven Central (Production Distribution)

1. **Prerequisites:**
   - GPG key configured for signing
   - Sonatype OSSRH account
   - Configure `~/.m2/settings.xml` with Sonatype credentials

2. **Deploy to Maven Central:**
   ```bash
   mvn clean deploy -P release
   ```

### Option 3: Local Development/Testing

1. **Install to Local Repository:**
   ```bash
   mvn clean install
   ```

2. **Test in Another Project:**
   ```xml
   <dependency>
     <groupId>com.payagency</groupId>
     <artifactId>payagency-java-sdk</artifactId>
     <version>1.0.15</version>
   </dependency>
   ```

## 📦 Generated Artifacts

The following artifacts are ready for distribution:

- **Main JAR:** `target/payagency-java-sdk-1.0.15.jar`
- **Sources JAR:** `target/payagency-java-sdk-1.0.15-sources.jar`
- **Javadoc JAR:** `target/payagency-java-sdk-1.0.15-javadoc.jar`
- **Test Coverage:** `target/site/jacoco/index.html`

## 🔐 Security Checklist

✅ No secrets in source code  
✅ All tests passing  
✅ Code coverage generated  
✅ Javadoc complete  
✅ Open source governance files present  
✅ Maven Central distribution ready  

## 🚀 Quick Deploy Commands

### GitHub Packages:
```bash
# Set up GitHub remote (if not already done)
git remote add origin https://github.com/vp-payomatix/payagency-java-sdk.git

# Push code and tags
git push -u origin main
git push origin v1.0.15

# Deploy to GitHub Packages
mvn deploy -DaltDeploymentRepository=github::default::https://maven.pkg.github.com/vp-payomatix/payagency-java-sdk
```

### Maven Central:
```bash
# Deploy with GPG signing
mvn clean deploy -P release
```

### Local Install:
```bash
# Install to local Maven repository
mvn clean install
```

## 📋 Post-Deployment

1. **Create GitHub Release:**
   - Go to GitHub repository
   - Create new release from tag v1.0.15
   - Attach JAR files
   - Add release notes

2. **Update Documentation:**
   - Update version in README.md examples
   - Create CHANGELOG.md entry
   - Update any integration guides

3. **Verify Integration:**
   - Test in a separate project
   - Verify Maven/Gradle dependency resolution
   - Test all major API endpoints

## 🎯 SDK Features Ready for Release

- ✅ **Complete API Coverage:** All PayAgency endpoints
- ✅ **Crypto Payment Links:** Full ONRAMP/OFFRAMP/PAYIN support
- ✅ **Proper Documentation:** Correct amount format (100 = $100.00)
- ✅ **Type Safety:** Comprehensive type definitions
- ✅ **Error Handling:** Robust exception management
- ✅ **Test Coverage:** Unit and integration tests
- ✅ **Open Source Ready:** Full governance and security validation

Your PayAgency Java SDK is production-ready! 🎉

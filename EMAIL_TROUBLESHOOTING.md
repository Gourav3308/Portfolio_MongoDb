# Email Service Troubleshooting Guide

## Issues Fixed

### 1. **Enhanced Error Handling**
- Added comprehensive logging to track email sending process
- Added validation for required fields
- Better error messages for debugging

### 2. **Improved Email Service**
- Added try-catch blocks around each email operation
- Detailed logging for each step of the process
- Better error propagation

### 3. **Added Test Endpoint**
- Created `/api/contact/test` endpoint to test email functionality
- Helps isolate email issues from form submission issues

## Testing Steps

### Step 1: Start the Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Step 2: Test Email Service Directly
Visit: `http://localhost:8080/api/contact/test`

This will send a test email to verify the email service is working.

### Step 3: Check Logs
Look for these log messages in the console:
- "Testing email service..."
- "Starting to process contact message from: [email]"
- "Contact message saved to database with ID: [id]"
- "Sending admin notification email to: gouravkrsah78@gmail.com"
- "Admin notification email sent successfully"
- "Sending confirmation email to: [email]"
- "Confirmation email sent successfully to: [email]"

### Step 4: Test Contact Form
1. Go to `http://localhost:4200/contact`
2. Fill out the contact form
3. Submit and check for success/error messages

## Common Issues & Solutions

### Issue 1: Gmail Authentication
**Problem**: "Authentication failed" or "Invalid credentials"
**Solution**: 
- Verify the app password is correct: `byqm pgup kojq vzwv`
- Ensure 2FA is enabled on Gmail account
- Check if Gmail account allows "Less secure app access" (if not using app password)

### Issue 2: MongoDB Connection
**Problem**: "Cannot connect to MongoDB"
**Solution**:
- Start MongoDB: `mongod`
- Verify MongoDB is running on port 27017
- Check database name: `portfolio_db`

### Issue 3: CORS Issues
**Problem**: Frontend can't connect to backend
**Solution**:
- Verify backend is running on port 8080
- Check CORS configuration in ContactController
- Ensure frontend is making requests to `http://localhost:8080/api/contact/send`

### Issue 4: Network/Firewall Issues
**Problem**: "Connection timeout" or "Network unreachable"
**Solution**:
- Check firewall settings
- Verify internet connection
- Test with a different network

## Debug Commands

### Check Backend Status
```bash
curl http://localhost:8080/api/contact/test
```

### Check MongoDB
```bash
mongo
use portfolio_db
db.contact_messages.find()
```

### Check Logs
```bash
# In backend directory
tail -f logs/spring.log
```

## Email Configuration Verification

The current configuration in `application.yml`:
```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: gouravkrsah78@gmail.com
    password: byqm pgup kojq vzwv
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
          ssl:
            trust: smtp.gmail.com
```

## Next Steps

1. **Test the email service** using the test endpoint
2. **Check the logs** for any error messages
3. **Verify Gmail settings** if authentication fails
4. **Test the contact form** after confirming email service works

If issues persist, check the console logs for specific error messages and share them for further debugging.

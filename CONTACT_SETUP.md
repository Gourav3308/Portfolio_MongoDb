# Contact Form Setup Guide

## Overview
The contact form has been updated with professional styling and email functionality. When users submit the contact form, it will:

1. Send an email notification to `gouravkrsah78@gmail.com`
2. Send a confirmation email to the user
3. Store the message in the database for future reference

## Features Implemented

### Frontend (Angular)
- **Professional styling** matching other sections
- **Contact information cards** with email, phone, and location
- **Social media links** (GitHub and LinkedIn)
- **Enhanced contact form** with validation and loading states
- **Success/error messages** with proper styling
- **Responsive design** for mobile devices

### Backend (Spring Boot)
- **Email service** using Gmail SMTP
- **Contact message model** for database storage
- **REST API endpoint** for form submission
- **Automatic email notifications** to admin
- **Confirmation emails** to users

## Configuration

### Email Settings
The email configuration is set up in `backend/src/main/resources/application.yml`:

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: gouravkrsah78@gmail.com
    password: byqm pgup kojq vzwv  # App password
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
          ssl:
            trust: smtp.gmail.com

app:
  contact:
    email: gouravkrsah78@gmail.com
```

### Contact Information
- **Email**: gouravkrsah78@gmail.com
- **Phone**: +91 7903840357
- **GitHub**: https://github.com/Gourav3308
- **LinkedIn**: https://www.linkedin.com/in/gourav-java-dev/

## API Endpoints

### POST /api/contact/send
Sends a contact message and triggers email notifications.

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "subject": "Project Inquiry",
  "message": "I'm interested in discussing a project..."
}
```

**Response:**
```json
{
  "status": "success",
  "message": "Message sent successfully!"
}
```

## Running the Application

1. **Start MongoDB** (if not already running)
2. **Start the backend**:
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```
3. **Start the frontend**:
   ```bash
   cd frontend
   npm start
   ```

## Testing the Contact Form

1. Navigate to the contact page
2. Fill out the form with test data
3. Submit the form
4. Check your email for:
   - Admin notification email
   - User confirmation email

## Security Notes

- The Gmail app password is configured for SMTP access
- CORS is enabled for the contact API endpoint
- Form validation is implemented on both frontend and backend
- Email addresses are validated before sending

## Troubleshooting

### Email Issues
- Verify Gmail app password is correct
- Check Gmail SMTP settings
- Ensure 2FA is enabled on Gmail account

### Form Submission Issues
- Check browser console for errors
- Verify backend is running on port 8080
- Check network tab for API call status

### Database Issues
- Ensure MongoDB is running
- Check database connection in application.yml
- Verify contact_messages collection is created

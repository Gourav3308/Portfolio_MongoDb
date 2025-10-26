# 🔧 Environment Variables Setup Guide

## MongoDB Atlas Setup

### Step 1: Create MongoDB Atlas Account
1. Go to https://www.mongodb.com/atlas
2. Sign up for free account
3. Create new project

### Step 2: Create Free Cluster
1. Click "Build a Database"
2. Choose "M0 Sandbox" (FREE)
3. Select region closest to you
4. Click "Create Cluster"

### Step 3: Set Up Database Access
1. Go to "Database Access" in left sidebar
2. Click "Add New Database User"
3. **Authentication Method**: Password
4. **Username**: `portfolio_user`
5. **Password**: Generate secure password (save this!)
6. **Database User Privileges**: "Read and write to any database"
7. Click "Add User"

### Step 4: Configure Network Access
1. Go to "Network Access" in left sidebar
2. Click "Add IP Address"
3. Choose "Allow Access from Anywhere" (0.0.0.0/0)
4. Click "Confirm"

### Step 5: Get Connection String
1. Go to "Clusters" in left sidebar
2. Click "Connect" on your cluster
3. Choose "Connect your application"
4. Copy the connection string
5. Replace `<password>` with your database user password

**Example Connection String:**
```
mongodb+srv://portfolio_user:YOUR_PASSWORD@cluster0.xxxxx.mongodb.net/portfolio_db?retryWrites=true&w=majority
```

## Render Backend Environment Variables

### Required Variables:
```
SPRING_DATA_MONGODB_URI=mongodb+srv://portfolio_user:YOUR_PASSWORD@cluster0.xxxxx.mongodb.net/portfolio_db?retryWrites=true&w=majority
SPRING_PROFILES_ACTIVE=production
ADMIN_USERNAME=admin
ADMIN_PASSWORD=your_secure_password_here
MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_gmail_app_password
CONTACT_EMAIL=your_email@gmail.com
```

### How to Set in Render:
1. Go to your Render service dashboard
2. Click "Environment" tab
3. Add each variable one by one
4. Click "Save Changes"

## Vercel Frontend Environment Variables

### Required Variables:
```
API_URL=https://your-backend-service.onrender.com/api
```

### How to Set in Vercel:
1. Go to your Vercel project dashboard
2. Click "Settings" tab
3. Click "Environment Variables"
4. Add the variable
5. Click "Save"

## Gmail App Password Setup

### Step 1: Enable 2-Factor Authentication
1. Go to https://myaccount.google.com/security
2. Enable "2-Step Verification"

### Step 2: Generate App Password
1. Go to https://myaccount.google.com/apppasswords
2. Select "Mail" as app
3. Generate password
4. Use this password in `MAIL_PASSWORD` variable

## Security Best Practices

### Database Security:
- ✅ Use strong passwords
- ✅ Enable network access restrictions
- ✅ Regular password rotation
- ✅ Monitor access logs

### Application Security:
- ✅ Use environment variables for secrets
- ✅ Enable HTTPS
- ✅ Set strong admin passwords
- ✅ Regular security updates

## Troubleshooting

### Common Issues:

1. **Database Connection Failed**:
   - Check MongoDB Atlas network access
   - Verify connection string format
   - Ensure database user has correct permissions

2. **Email Not Working**:
   - Verify Gmail app password
   - Check 2FA is enabled
   - Test with simple email first

3. **Frontend API Calls Failing**:
   - Check API_URL environment variable
   - Verify backend is running
   - Check CORS settings

### Support Resources:
- MongoDB Atlas: https://docs.atlas.mongodb.com
- Render: https://render.com/docs
- Vercel: https://vercel.com/docs

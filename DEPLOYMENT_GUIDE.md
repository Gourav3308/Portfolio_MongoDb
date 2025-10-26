# 🚀 Portfolio Deployment Guide - Vercel + Render + MongoDB Atlas

## Overview
This guide will help you deploy your Spring Boot + Angular + MongoDB portfolio application using:
- **Frontend**: Vercel (excellent for Angular)
- **Backend**: Render (great for Spring Boot)
- **Database**: MongoDB Atlas (free tier)

## 🎯 Why This Combination?
- ✅ **Vercel**: Best-in-class Angular hosting with automatic deployments
- ✅ **Render**: Reliable Spring Boot hosting with free tier
- ✅ **MongoDB Atlas**: Free 512MB database with excellent performance
- ✅ **Total Cost**: $0/month (all free tiers)

## 📋 Prerequisites
1. **GitHub account** (for code repository)
2. **Vercel account** (sign up at vercel.com)
3. **Render account** (sign up at render.com)
4. **MongoDB Atlas account** (sign up at mongodb.com/atlas)

## 🗂️ Project Structure for Deployment
```
Portfolio/
├── backend/                 # Spring Boot Backend
├── frontend/               # Angular Frontend
├── vercel.json            # Vercel configuration
├── render.yaml            # Render configuration
└── README.md              # This file
```

## 🛠️ Step-by-Step Deployment

### Step 1: Prepare Your Repository

1. **Push your code to GitHub** (if not already done):
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/yourusername/portfolio.git
   git push -u origin main
   ```

### Step 2: Set Up MongoDB Atlas (Free Database)

1. **Create MongoDB Atlas account**: https://www.mongodb.com/atlas
2. **Create a free cluster**:
   - Choose "M0 Sandbox" (free tier)
   - Select region closest to you
   - Create cluster
3. **Set up database access**:
   - Go to "Database Access"
   - Add new database user
   - Username: `portfolio_user`
   - Password: Generate secure password
   - Database User Privileges: "Read and write to any database"
4. **Configure network access**:
   - Go to "Network Access"
   - Add IP Address: "0.0.0.0/0" (allow from anywhere)
5. **Get connection string**:
   - Go to "Clusters"
   - Click "Connect"
   - Choose "Connect your application"
   - Copy the connection string (replace `<password>` with your password)

### Step 3: Deploy Backend to Render

1. **Sign up/Login to Render**: https://render.com
2. **Create new Web Service**:
   - Click "New +" → "Web Service"
   - Connect your GitHub repository
   - Choose your portfolio repository
3. **Configure backend service**:
   - **Name**: `portfolio-backend`
   - **Root Directory**: `backend`
   - **Environment**: `Java`
   - **Build Command**: `./mvnw clean package -DskipTests`
   - **Start Command**: `java -jar target/portfolio-backend-0.0.1-SNAPSHOT.jar`
4. **Add environment variables**:
   ```
   SPRING_DATA_MONGODB_URI=mongodb+srv://portfolio_user:YOUR_PASSWORD@cluster0.xxxxx.mongodb.net/portfolio_db?retryWrites=true&w=majority
   SPRING_PROFILES_ACTIVE=production
   ADMIN_USERNAME=admin
   ADMIN_PASSWORD=your_secure_password
   MAIL_USERNAME=your_email@gmail.com
   MAIL_PASSWORD=your_gmail_app_password
   CONTACT_EMAIL=your_email@gmail.com
   ```
5. **Deploy**: Click "Create Web Service"

### Step 4: Deploy Frontend to Vercel

1. **Sign up/Login to Vercel**: https://vercel.com
2. **Import project**:
   - Click "New Project"
   - Import from GitHub
   - Choose your portfolio repository
3. **Configure frontend**:
   - **Framework Preset**: Angular
   - **Root Directory**: `frontend`
   - **Build Command**: `npm run build`
   - **Output Directory**: `dist/portfolio-frontend/browser`
4. **Add environment variables**:
   ```
   API_URL=https://your-backend-service.onrender.com/api
   ```
5. **Deploy**: Click "Deploy"

### Step 5: Configure Production Settings

#### Backend Configuration (`backend/src/main/resources/application-prod.yml`):
```yaml
server:
  port: ${PORT:8080}

spring:
  data:
    mongodb:
      uri: ${SPRING_DATA_MONGODB_URI}
      database: portfolio_db
  
  security:
    user:
      name: ${ADMIN_USERNAME:admin}
      password: ${ADMIN_PASSWORD:admin123}
    enabled: true
  
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USERNAME}
    password: ${MAIL_PASSWORD}
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
    email: ${CONTACT_EMAIL}

logging:
  level:
    com.portfolio: INFO
    org.springframework.data.mongodb: WARN
    org.springframework.mail: INFO

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

#### Frontend Configuration (`frontend/src/environments/environment.prod.ts`):
```typescript
export const environment = {
  production: true,
  apiUrl: 'https://your-backend-service.onrender.com/api'
};
```

#### Frontend Service Update (`frontend/src/app/services/portfolio.ts`):
```typescript
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
// ... other imports

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {
  private apiUrl = environment.apiUrl;
  // ... rest of the service
}
```

### Step 6: Custom Domain (Optional)

#### Vercel Custom Domain:
1. Go to your project settings
2. Add custom domain
3. Update DNS records as instructed

#### Render Custom Domain:
1. Go to your service settings
2. Add custom domain
3. Update DNS records as instructed

## 🔧 Required Files for Deployment

### 1. Vercel Configuration (`vercel.json`)
```json
{
  "buildCommand": "cd frontend && npm run build",
  "outputDirectory": "frontend/dist/portfolio-frontend/browser",
  "framework": "angular",
  "rewrites": [
    {
      "source": "/(.*)",
      "destination": "/index.html"
    }
  ]
}
```

### 2. Render Configuration (`render.yaml`)
```yaml
services:
  - type: web
    name: portfolio-backend
    env: java
    plan: free
    buildCommand: ./mvnw clean package -DskipTests
    startCommand: java -jar target/portfolio-backend-0.0.1-SNAPSHOT.jar
    envVars:
      - key: SPRING_PROFILES_ACTIVE
        value: production
      - key: SPRING_DATA_MONGODB_URI
        fromDatabase:
          name: portfolio-db
          property: connectionString
```

### 3. Angular Build Configuration Update
Update `frontend/angular.json` to include environment file replacement:
```json
"configurations": {
  "production": {
    "fileReplacements": [
      {
        "replace": "src/environments/environment.ts",
        "with": "src/environments/environment.prod.ts"
      }
    ],
    "outputHashing": "all"
  }
}
```

## 🚀 Deployment Commands

### Backend (Render):
```bash
# Render will automatically build and deploy
# Just push to GitHub and Render handles the rest
```

### Frontend (Vercel):
```bash
# Vercel will automatically build and deploy
# Just push to GitHub and Vercel handles the rest
```

## 🔍 Monitoring and Maintenance

### Vercel Dashboard:
- Monitor deployments
- View analytics
- Manage domains
- Check function logs

### Render Dashboard:
- Monitor service health
- View logs
- Manage environment variables
- Check resource usage

### MongoDB Atlas Dashboard:
- Monitor database performance
- View connection metrics
- Manage backups

## 💰 Cost Breakdown (FREE)

- **Vercel**: Free tier (100GB bandwidth/month)
- **Render**: Free tier (750 hours/month)
- **MongoDB Atlas**: 512MB storage (FREE tier)
- **Custom Domain**: Optional (FREE subdomains provided)
- **Total Monthly Cost**: $0

## 🆘 Troubleshooting

### Common Issues:

1. **Vercel Build Failures**:
   - Check build logs in Vercel dashboard
   - Verify Angular build command
   - Ensure all dependencies are in package.json

2. **Render Build Failures**:
   - Check Render logs
   - Verify Maven build command
   - Ensure Java version compatibility

3. **Database Connection Issues**:
   - Verify MongoDB Atlas connection string
   - Check network access settings
   - Ensure database user has correct permissions

4. **Frontend API Calls Failing**:
   - Verify API_URL environment variable
   - Check CORS settings in backend
   - Ensure backend is deployed and running

### Support Resources:
- Vercel Documentation: https://vercel.com/docs
- Render Documentation: https://render.com/docs
- MongoDB Atlas Documentation: https://docs.atlas.mongodb.com

## 🎉 Success Checklist

- [ ] Code pushed to GitHub
- [ ] MongoDB Atlas cluster created
- [ ] Backend deployed to Render
- [ ] Frontend deployed to Vercel
- [ ] Environment variables configured
- [ ] Custom domains set up (optional)
- [ ] Application accessible via URLs
- [ ] All features working correctly

## 🔄 Continuous Deployment

Once set up:
- **Vercel** will automatically deploy frontend changes
- **Render** will automatically deploy backend changes
- **MongoDB Atlas** provides reliable database hosting

## 📊 Performance Benefits

- **Vercel**: Global CDN, instant deployments, excellent Angular support
- **Render**: Reliable Java hosting, automatic scaling, health checks
- **MongoDB Atlas**: Managed database, automatic backups, monitoring

---

**Next Steps**: Follow this guide step by step, and your portfolio will be live on the internet for FREE! 🚀

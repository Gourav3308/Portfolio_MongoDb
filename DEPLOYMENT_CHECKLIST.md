# 🚀 Quick Deployment Checklist - Vercel + Render + MongoDB Atlas

## Pre-Deployment Checklist
- [ ] Code pushed to GitHub repository
- [ ] MongoDB Atlas account created
- [ ] Vercel account created
- [ ] Render account created
- [ ] All configuration files created

## MongoDB Atlas Setup
- [ ] Free cluster created (M0 Sandbox)
- [ ] Database user created with read/write permissions
- [ ] Network access configured (0.0.0.0/0)
- [ ] Connection string obtained and tested

## Render Backend Deployment
- [ ] Web service created
- [ ] Root directory set to `backend`
- [ ] Build command: `./mvnw clean package -DskipTests`
- [ ] Start command: `java -jar target/portfolio-backend-0.0.1-SNAPSHOT.jar`
- [ ] Environment variables configured
- [ ] Service deployed and running

## Vercel Frontend Deployment
- [ ] Project imported from GitHub
- [ ] Root directory set to `frontend`
- [ ] Build command: `npm run build`
- [ ] Output directory: `dist/portfolio-frontend/browser`
- [ ] Environment variables configured
- [ ] Project deployed and running

## Environment Variables Checklist

### Render Backend Service
- [ ] `SPRING_DATA_MONGODB_URI` - MongoDB connection string
- [ ] `SPRING_PROFILES_ACTIVE=production`
- [ ] `ADMIN_USERNAME` - Admin username
- [ ] `ADMIN_PASSWORD` - Secure admin password
- [ ] `MAIL_USERNAME` - Gmail address
- [ ] `MAIL_PASSWORD` - Gmail app password
- [ ] `CONTACT_EMAIL` - Contact email address

### Vercel Frontend Service
- [ ] `API_URL` - Backend service URL (https://your-backend.onrender.com/api)

## Testing Checklist
- [ ] Backend API accessible at Render URL
- [ ] Frontend loads correctly at Vercel URL
- [ ] API calls working from frontend
- [ ] Contact form functional
- [ ] All pages accessible
- [ ] Responsive design working
- [ ] Database operations working

## Post-Deployment
- [ ] Custom domain configured (optional)
- [ ] SSL certificate active
- [ ] Performance monitoring set up
- [ ] Backup strategy in place

## Troubleshooting
- [ ] Check Render logs for backend errors
- [ ] Check Vercel logs for frontend errors
- [ ] Verify environment variables
- [ ] Test MongoDB connection
- [ ] Check CORS settings
- [ ] Verify email configuration

## Cost Monitoring
- [ ] Render usage within free tier limits (750 hours/month)
- [ ] Vercel usage within free tier limits (100GB bandwidth/month)
- [ ] MongoDB Atlas within free tier limits (512MB storage)

## URLs After Deployment
- **Frontend**: `https://your-project.vercel.app`
- **Backend**: `https://your-backend.onrender.com`
- **Database**: MongoDB Atlas (managed)

---

**Total Estimated Setup Time**: 45-60 minutes
**Monthly Cost**: $0 (FREE tier)
**Difficulty Level**: Beginner to Intermediate

## Quick Commands

### Test Backend Locally:
```bash
cd backend
./mvnw spring-boot:run
```

### Test Frontend Locally:
```bash
cd frontend
npm start
```

### Build for Production:
```bash
cd frontend
npm run build
```

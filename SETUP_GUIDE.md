# Professional Portfolio Setup Guide

## 🚀 Complete Setup Instructions

This guide will help you set up and run your professional portfolio application with Spring Boot, Angular, and MongoDB.

## 📋 Prerequisites

Before starting, ensure you have the following installed:

- **Java 17+** - [Download here](https://adoptium.net/)
- **Node.js 18+** - [Download here](https://nodejs.org/)
- **MongoDB 6+** - [Download here](https://www.mongodb.com/try/download/community)
- **Maven 3.6+** - [Download here](https://maven.apache.org/download.cgi)
- **Angular CLI 17+** - Install with `npm install -g @angular/cli`

## 🗂️ Project Structure

```
Portfolio/
├── backend/                 # Spring Boot Backend
│   ├── src/main/java/com/portfolio/
│   │   ├── model/          # MongoDB data models
│   │   ├── repository/     # MongoDB repositories
│   │   ├── controller/     # REST API controllers
│   │   └── PortfolioApplication.java
│   ├── src/main/resources/
│   │   └── application.yml # MongoDB configuration
│   └── pom.xml
├── frontend/               # Angular Frontend
│   ├── src/app/
│   │   ├── components/     # Page components
│   │   ├── models/         # TypeScript models
│   │   ├── services/       # API services
│   │   └── app.routes.ts    # Routing configuration
│   └── package.json
├── MONGODB_SETUP.md        # MongoDB setup instructions
└── README.md
```

## 🛠️ Step-by-Step Setup

### 1. MongoDB Setup

**What you need to do manually for MongoDB:**

#### Install MongoDB Community Server
1. Download from: https://www.mongodb.com/try/download/community
2. Run installer and choose "Complete" installation
3. Install as Windows Service (recommended)
4. Install MongoDB Compass (optional GUI tool)

#### Start MongoDB Service
```bash
# Check if MongoDB is running
netstat -an | findstr 27017

# Start MongoDB service (if not running)
net start MongoDB
```

#### Create Database and Sample Data
```bash
# Open MongoDB shell
mongosh

# Create database and collections
use portfolio_db

# Insert sample personal info
db.personal_info.insertOne({
  name: "Gourav Kumar",
  title: "Full Stack Developer",
  email: "gourav@example.com",
  phone: "+91-1234567890",
  location: "India",
  linkedin: "https://linkedin.com/in/gourav",
  github: "https://github.com/gourav",
  portfolio: "https://gourav-portfolio.com",
  summary: "Passionate full-stack developer with expertise in Java, Spring Boot, Angular, and MongoDB."
})

# Insert sample projects
db.projects.insertOne({
  name: "HealthBridge - Telehealth Platform",
  description: "Full-stack telehealth platform with appointment scheduling, video consultations, and patient management.",
  technologies: "Spring Boot, Angular, MySQL, JWT, OAuth2",
  githubUrl: "https://github.com/gourav/healthbridge",
  liveUrl: "https://healthbridge-demo.com",
  imageUrl: "https://via.placeholder.com/400x250",
  startDate: "2024-01-01",
  endDate: "2024-06-30",
  isCurrent: false,
  features: ["Appointment Scheduling", "Video Consultations", "Patient Reviews", "Dashboard"],
  category: "Full Stack"
})

# Insert sample skills
db.skills.insertMany([
  {
    name: "Java",
    proficiency: 90,
    category: "Backend",
    icon: "fab fa-java",
    description: "Core Java, Spring Framework, Spring Boot"
  },
  {
    name: "Angular",
    proficiency: 85,
    category: "Frontend",
    icon: "fab fa-angular",
    description: "Angular 17+, TypeScript, RxJS"
  },
  {
    name: "MongoDB",
    proficiency: 80,
    category: "Database",
    icon: "fas fa-database",
    description: "MongoDB, Mongoose, NoSQL"
  },
  {
    name: "Spring Boot",
    proficiency: 88,
    category: "Backend",
    icon: "fas fa-code",
    description: "REST APIs, Microservices, Spring Security"
  }
])
```

### 2. Backend Setup (Spring Boot)

```bash
# Navigate to backend directory
cd backend

# Install dependencies
mvn clean install

# Run the Spring Boot application
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

**API Endpoints:**
- `GET /api/personal-info` - Get personal information
- `GET /api/projects` - Get all projects
- `GET /api/skills` - Get all skills
- `POST /api/personal-info` - Create personal info
- `POST /api/projects` - Create project
- `POST /api/skills` - Create skill

### 3. Frontend Setup (Angular)

```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start the Angular development server
ng serve
```

The frontend will start on `http://localhost:4200`

### 4. Verify Setup

1. **MongoDB**: Check if running on port 27017
2. **Backend**: Visit `http://localhost:8080/api/personal-info`
3. **Frontend**: Visit `http://localhost:4200`

## 🎨 Features

### Multiple Pages
- **Home**: Hero section with introduction
- **About**: Personal information and background
- **Projects**: Portfolio projects with details
- **Skills**: Technical skills with proficiency levels
- **Contact**: Contact form and information

### Technologies Used
- **Backend**: Spring Boot 3.2, Spring Data MongoDB, Spring Security
- **Frontend**: Angular 17, TypeScript, Bootstrap 5, Font Awesome
- **Database**: MongoDB with document-based storage
- **Architecture**: RESTful APIs, Multi-page application

## 🔧 Configuration

### Backend Configuration
The Spring Boot application is configured to connect to MongoDB at:
- **Host**: localhost
- **Port**: 27017
- **Database**: portfolio_db

You can modify `backend/src/main/resources/application.yml` if needed.

### Frontend Configuration
The Angular application connects to the backend API at:
- **API URL**: http://localhost:8080/api

## 🚀 Running the Application

### Development Mode

1. **Start MongoDB** (if not running as service):
   ```bash
   mongod --dbpath "C:\data\db"
   ```

2. **Start Backend**:
   ```bash
   cd backend
   mvn spring-boot:run
   ```

3. **Start Frontend**:
   ```bash
   cd frontend
   ng serve
   ```

4. **Open Browser**: Visit `http://localhost:4200`

### Production Build

1. **Build Backend**:
   ```bash
   cd backend
   mvn clean package
   java -jar target/portfolio-backend-0.0.1-SNAPSHOT.jar
   ```

2. **Build Frontend**:
   ```bash
   cd frontend
   ng build --prod
   ```

## 🐛 Troubleshooting

### Common Issues

1. **MongoDB Connection Error**:
   - Ensure MongoDB is running on port 27017
   - Check if data directory exists: `C:\data\db`
   - Verify MongoDB service is started

2. **Backend Not Starting**:
   - Check Java version (should be 17+)
   - Verify Maven installation
   - Check if port 8080 is available

3. **Frontend Not Loading**:
   - Check Node.js version (should be 18+)
   - Verify Angular CLI installation
   - Check if port 4200 is available

4. **CORS Issues**:
   - Backend is configured to allow CORS from `http://localhost:4200`
   - If using different ports, update CORS configuration

### Useful Commands

```bash
# Check MongoDB status
sc query MongoDB

# Start MongoDB service
net start MongoDB

# Check if ports are in use
netstat -an | findstr :8080
netstat -an | findstr :4200
netstat -an | findstr :27017
```

## 📝 Adding Content

### Add Personal Information
Use the API or MongoDB shell to add your personal information:

```javascript
db.personal_info.insertOne({
  name: "Your Name",
  title: "Your Title",
  email: "your@email.com",
  phone: "your-phone",
  location: "Your Location",
  linkedin: "https://linkedin.com/in/yourprofile",
  github: "https://github.com/yourusername",
  portfolio: "https://your-portfolio.com",
  summary: "Your professional summary"
})
```

### Add Projects
```javascript
db.projects.insertOne({
  name: "Project Name",
  description: "Project description",
  technologies: "Technology1, Technology2, Technology3",
  githubUrl: "https://github.com/yourusername/project",
  liveUrl: "https://your-project.com",
  imageUrl: "https://your-image-url.com",
  startDate: "2024-01-01",
  endDate: "2024-06-30",
  isCurrent: false,
  features: ["Feature 1", "Feature 2"],
  category: "Full Stack"
})
```

### Add Skills
```javascript
db.skills.insertOne({
  name: "Skill Name",
  proficiency: 85,
  category: "Backend",
  icon: "fas fa-code",
  description: "Skill description"
})
```

## 🎯 Next Steps

1. **Customize Content**: Update personal information, projects, and skills
2. **Add Images**: Replace placeholder images with actual project screenshots
3. **Deploy**: Consider deploying to cloud platforms like Heroku, AWS, or Vercel
4. **Domain**: Purchase a custom domain for your portfolio
5. **Analytics**: Add Google Analytics or similar tracking

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Angular Documentation](https://angular.io/docs)
- [MongoDB Documentation](https://docs.mongodb.com/)
- [Bootstrap Documentation](https://getbootstrap.com/docs/)

## 🤝 Support

If you encounter any issues:
1. Check the troubleshooting section above
2. Verify all prerequisites are installed
3. Ensure all services are running
4. Check the console for error messages

Your professional portfolio is now ready! 🎉



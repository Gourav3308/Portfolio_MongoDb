# MongoDB Manual Setup Instructions

## What You Need to Do Manually for MongoDB

### 1. Install MongoDB Community Server

#### Windows:
1. Download MongoDB Community Server from: https://www.mongodb.com/try/download/community
2. Run the installer and follow the setup wizard
3. Choose "Complete" installation
4. Install MongoDB as a Windows Service (recommended)
5. Install MongoDB Compass (GUI tool) - optional but helpful

#### Alternative - Using Chocolatey (if you have it):
```bash
choco install mongodb
```

### 2. Start MongoDB Service

#### Windows:
- MongoDB should start automatically as a Windows Service
- If not, open Services (services.msc) and start "MongoDB" service
- Or use Command Prompt as Administrator:
```bash
net start MongoDB
```

#### Manual Start (if needed):
```bash
# Navigate to MongoDB bin directory
cd "C:\Program Files\MongoDB\Server\7.0\bin"

# Start MongoDB
mongod --dbpath "C:\data\db"
```

### 3. Create Database and Collections

#### Option 1: Using MongoDB Compass (GUI)
1. Open MongoDB Compass
2. Connect to `mongodb://localhost:27017`
3. Create database: `portfolio_db`
4. Create collections:
   - `personal_info`
   - `projects`
   - `skills`

#### Option 2: Using MongoDB Shell
1. Open Command Prompt
2. Navigate to MongoDB bin directory
3. Run MongoDB shell:
```bash
mongosh
```

4. Create database and collections:
```javascript
// Switch to portfolio database
use portfolio_db

// Create collections (they will be created when you insert data)
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

### 4. Verify Installation

#### Check if MongoDB is running:
```bash
# In Command Prompt
netstat -an | findstr 27017
```

#### Test connection:
```bash
# Using MongoDB shell
mongosh --eval "db.adminCommand('ismaster')"
```

### 5. Configure Spring Boot Application

The Spring Boot application is already configured to connect to MongoDB at:
- **Host**: localhost
- **Port**: 27017
- **Database**: portfolio_db

You can modify the connection string in `backend/src/main/resources/application.yml` if needed:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/portfolio_db
```

### 6. Troubleshooting

#### Common Issues:

1. **MongoDB not starting**:
   - Check if port 27017 is available
   - Ensure data directory exists: `C:\data\db`
   - Check Windows Firewall settings

2. **Connection refused**:
   - Verify MongoDB service is running
   - Check if MongoDB is listening on port 27017
   - Ensure no other application is using port 27017

3. **Authentication issues**:
   - For development, MongoDB runs without authentication by default
   - If you enabled authentication, update the connection string

#### Useful Commands:
```bash
# Check MongoDB status
sc query MongoDB

# Start MongoDB service
net start MongoDB

# Stop MongoDB service
net stop MongoDB

# View MongoDB logs
# Check Windows Event Viewer or MongoDB log files
```

### 7. Data Management

#### Backup Database:
```bash
mongodump --db portfolio_db --out backup/
```

#### Restore Database:
```bash
mongorestore --db portfolio_db backup/portfolio_db/
```

### 8. Production Considerations

For production deployment, you should:
1. Enable authentication
2. Configure SSL/TLS
3. Set up replica sets for high availability
4. Configure proper backup strategies
5. Monitor performance and logs

### 9. Alternative: MongoDB Atlas (Cloud)

If you prefer a cloud solution:
1. Sign up at https://cloud.mongodb.com
2. Create a free cluster
3. Get connection string
4. Update `application.yml` with Atlas connection string

```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://username:password@cluster.mongodb.net/portfolio_db
```

## Summary

**What you need to do manually:**
1. ✅ Install MongoDB Community Server
2. ✅ Start MongoDB service
3. ✅ Create database and collections (or let the app create them)
4. ✅ Verify connection
5. ✅ Optionally add sample data

The Spring Boot application will automatically create the database and collections when you start it, but you can pre-populate them with sample data using the MongoDB shell commands above.



# Database Setup Script for Portfolio Application
# This script will create sample data for the portfolio application

Write-Host "Setting up Portfolio Database..." -ForegroundColor Green

# Sample Personal Information
$personalInfo = @{
    name = "Gourav Kumar"
    title = "Full Stack Developer"
    email = "gourav@example.com"
    phone = "+91-1234567890"
    location = "India"
    linkedin = "https://linkedin.com/in/gourav"
    github = "https://github.com/gourav"
    portfolio = "https://gourav-portfolio.com"
    summary = "Passionate full-stack developer with expertise in Java, Spring Boot, Angular, and MongoDB."
} | ConvertTo-Json

# Sample Project
$project = @{
    name = "HealthBridge - Telehealth Platform"
    description = "Full-stack telehealth platform with appointment scheduling, video consultations, and patient management."
    technologies = "Spring Boot, Angular, MySQL, JWT, OAuth2"
    githubUrl = "https://github.com/gourav/healthbridge"
    liveUrl = "https://healthbridge-demo.com"
    imageUrl = "https://via.placeholder.com/400x250"
    startDate = "2024-01-01"
    endDate = "2024-06-30"
    isCurrent = $false
    features = @("Appointment Scheduling", "Video Consultations", "Patient Reviews", "Dashboard")
    category = "Full Stack"
} | ConvertTo-Json

# Sample Skills
$skills = @(
    @{
        name = "Java"
        proficiency = 90
        category = "Backend"
        icon = "fab fa-java"
        description = "Core Java, Spring Framework, Spring Boot"
    },
    @{
        name = "Angular"
        proficiency = 85
        category = "Frontend"
        icon = "fab fa-angular"
        description = "Angular 17+, TypeScript, RxJS"
    },
    @{
        name = "MongoDB"
        proficiency = 80
        category = "Database"
        icon = "fas fa-database"
        description = "MongoDB, Mongoose, NoSQL"
    },
    @{
        name = "Spring Boot"
        proficiency = 88
        category = "Backend"
        icon = "fas fa-code"
        description = "REST APIs, Microservices, Spring Security"
    }
) | ConvertTo-Json

Write-Host "Waiting for backend to be ready..." -ForegroundColor Yellow
Start-Sleep -Seconds 10

# Test if backend is accessible
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/personal-info" -Method GET -ErrorAction Stop
    Write-Host "Backend is accessible!" -ForegroundColor Green
} catch {
    Write-Host "Backend is not accessible. Please ensure the Spring Boot application is running on port 8080." -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# Insert Personal Information
Write-Host "Inserting Personal Information..." -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/personal-info" -Method POST -Body $personalInfo -ContentType "application/json" -ErrorAction Stop
    Write-Host "Personal Information inserted successfully!" -ForegroundColor Green
} catch {
    Write-Host "Error inserting Personal Information: $($_.Exception.Message)" -ForegroundColor Red
}

# Insert Project
Write-Host "Inserting Project..." -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/projects" -Method POST -Body $project -ContentType "application/json" -ErrorAction Stop
    Write-Host "Project inserted successfully!" -ForegroundColor Green
} catch {
    Write-Host "Error inserting Project: $($_.Exception.Message)" -ForegroundColor Red
}

# Insert Skills
Write-Host "Inserting Skills..." -ForegroundColor Yellow
$skillsArray = $skills | ConvertFrom-Json
foreach ($skill in $skillsArray) {
    try {
        $skillJson = $skill | ConvertTo-Json
        $response = Invoke-WebRequest -Uri "http://localhost:8080/api/skills" -Method POST -Body $skillJson -ContentType "application/json" -ErrorAction Stop
        Write-Host "Skill '$($skill.name)' inserted successfully!" -ForegroundColor Green
    } catch {
        Write-Host "Error inserting Skill '$($skill.name)': $($_.Exception.Message)" -ForegroundColor Red
    }
}

Write-Host "Database setup completed!" -ForegroundColor Green
Write-Host "You can now access your portfolio at: http://localhost:4200" -ForegroundColor Cyan




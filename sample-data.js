// Sample data for MongoDB Compass
// Copy and paste these into MongoDB Compass

// 1. Personal Info Collection
db.personal_info.insertOne({
  "name": "Your Name",
  "title": "Full Stack Developer",
  "email": "your.email@example.com",
  "phone": "+1 (555) 123-4567",
  "location": "Your City, Country",
  "summary": "Passionate full-stack developer with expertise in Spring Boot, Angular, and MongoDB. I love creating innovative solutions and learning new technologies.",
  "linkedinUrl": "https://linkedin.com/in/yourprofile",
  "githubUrl": "https://github.com/yourusername",
  "resumeUrl": "https://your-resume-url.com",
  "profileImageUrl": "assets/images/profile.jpg"
});

// 2. Projects Collection
db.projects.insertMany([
  {
    "name": "E-Commerce Platform",
    "description": "A full-stack e-commerce application built with Spring Boot and Angular. Features include user authentication, product management, shopping cart, and payment integration.",
    "technologies": "Spring Boot, Angular, MongoDB, Stripe API, Bootstrap",
    "githubUrl": "https://github.com/yourusername/ecommerce-platform",
    "liveUrl": "https://your-ecommerce-demo.com",
    "imageUrl": "assets/images/ecommerce-project.svg",
    "startDate": "2024-01-01",
    "endDate": "2024-03-15",
    "isCurrent": false,
    "features": [
      "User Authentication & Authorization",
      "Product Catalog Management",
      "Shopping Cart & Checkout",
      "Payment Integration",
      "Order Management",
      "Admin Dashboard"
    ],
    "category": "Full Stack"
  },
  {
    "name": "Task Management App",
    "description": "A collaborative task management application with real-time updates, team collaboration features, and project tracking capabilities.",
    "technologies": "Spring Boot, Angular, WebSocket, MongoDB, JWT",
    "githubUrl": "https://github.com/yourusername/task-manager",
    "liveUrl": "https://your-task-manager-demo.com",
    "imageUrl": "assets/images/task-manager-project.svg",
    "startDate": "2024-02-01",
    "endDate": "2024-04-30",
    "isCurrent": true,
    "features": [
      "Real-time Collaboration",
      "Project Management",
      "Team Workspaces",
      "Task Assignment",
      "Progress Tracking",
      "Notifications"
    ],
    "category": "Web Application"
  },
  {
    "name": "Portfolio Website",
    "description": "This responsive portfolio website showcasing my projects, skills, and experience. Built with modern web technologies and best practices.",
    "technologies": "Angular, Spring Boot, MongoDB, Bootstrap, SCSS",
    "githubUrl": "https://github.com/yourusername/portfolio",
    "liveUrl": "https://your-portfolio.com",
    "imageUrl": "assets/images/portfolio-project.svg",
    "startDate": "2024-03-01",
    "endDate": "2024-05-15",
    "isCurrent": true,
    "features": [
      "Responsive Design",
      "Dynamic Content Management",
      "Contact Form",
      "Project Showcase",
      "Skills Display",
      "SEO Optimized"
    ],
    "category": "Portfolio"
  }
]);

// 3. Skills Collection
db.skills.insertMany([
  {
    "name": "Java",
    "category": "Backend",
    "proficiency": 90,
    "description": "Expert in Java development with Spring Boot framework"
  },
  {
    "name": "Spring Boot",
    "category": "Backend",
    "proficiency": 85,
    "description": "Proficient in building RESTful APIs and microservices"
  },
  {
    "name": "Angular",
    "category": "Frontend",
    "proficiency": 80,
    "description": "Skilled in building dynamic single-page applications"
  },
  {
    "name": "TypeScript",
    "category": "Frontend",
    "proficiency": 85,
    "description": "Strong typing and modern JavaScript features"
  },
  {
    "name": "MongoDB",
    "category": "Database",
    "proficiency": 75,
    "description": "Document-based database design and optimization"
  },
  {
    "name": "HTML/CSS",
    "category": "Frontend",
    "proficiency": 90,
    "description": "Semantic HTML and modern CSS techniques"
  },
  {
    "name": "JavaScript",
    "category": "Frontend",
    "proficiency": 85,
    "description": "ES6+ features and modern JavaScript patterns"
  },
  {
    "name": "Git",
    "category": "Tools",
    "proficiency": 80,
    "description": "Version control and collaborative development"
  },
  {
    "name": "Docker",
    "category": "DevOps",
    "proficiency": 70,
    "description": "Containerization and deployment automation"
  },
  {
    "name": "AWS",
    "category": "Cloud",
    "proficiency": 65,
    "description": "Cloud services and infrastructure management"
  }
]);

// 4. Education Collection
db.education.insertMany([
  {
    "institution": "Your University",
    "degree": "Bachelor of Science in Computer Science",
    "fieldOfStudy": "Computer Science",
    "startDate": "2020-09-01",
    "endDate": "2024-06-01",
    "gpa": 3.8,
    "description": "Focused on software engineering, algorithms, and data structures",
    "activities": [
      "Computer Science Club",
      "Programming Competitions",
      "Open Source Contributions"
    ],
    "isCurrent": false
  }
]);

// 5. Experience Collection
db.experience.insertMany([
  {
    "company": "Tech Company Inc.",
    "position": "Full Stack Developer Intern",
    "location": "City, State",
    "startDate": "2023-06-01",
    "endDate": "2023-08-31",
    "isCurrent": false,
    "description": "Developed and maintained web applications using Spring Boot and Angular. Collaborated with cross-functional teams to deliver high-quality software solutions.",
    "responsibilities": [
      "Developed RESTful APIs using Spring Boot",
      "Built responsive frontend components with Angular",
      "Collaborated with team members using Git",
      "Participated in code reviews and testing"
    ],
    "technologies": ["Spring Boot", "Angular", "MongoDB", "Git"],
    "type": "Internship"
  },
  {
    "company": "Freelance Projects",
    "position": "Web Developer",
    "location": "Remote",
    "startDate": "2023-09-01",
    "endDate": null,
    "isCurrent": true,
    "description": "Working on various web development projects for clients, focusing on modern web technologies and best practices.",
    "responsibilities": [
      "Client consultation and requirement analysis",
      "Full-stack web application development",
      "Database design and optimization",
      "Deployment and maintenance"
    ],
    "technologies": ["Spring Boot", "Angular", "MongoDB", "AWS", "Docker"],
    "type": "Freelance"
  }
]);

// 6. Certifications Collection
db.certifications.insertMany([
  {
    "name": "AWS Certified Developer - Associate",
    "organization": "Amazon Web Services",
    "issueDate": "2024-01-15",
    "expiryDate": "2027-01-15",
    "credentialId": "AWS-DEV-123456",
    "credentialUrl": "https://aws.amazon.com/verification",
    "status": "Active",
    "description": "Validates ability to develop and maintain applications on AWS platform"
  },
  {
    "name": "Spring Professional Certification",
    "organization": "VMware",
    "issueDate": "2023-11-20",
    "expiryDate": null,
    "credentialId": "SPRING-PRO-789012",
    "credentialUrl": "https://spring.io/certification",
    "status": "Active",
    "description": "Demonstrates expertise in Spring Framework and Spring Boot"
  }
]);

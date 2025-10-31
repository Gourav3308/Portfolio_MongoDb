package com.portfolio.config;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.portfolio.model.Certification;
import com.portfolio.model.Education;
import com.portfolio.model.Experience;
import com.portfolio.model.PersonalInfo;
import com.portfolio.model.Project;
import com.portfolio.model.Skill;
import com.portfolio.repository.CertificationRepository;
import com.portfolio.repository.EducationRepository;
import com.portfolio.repository.ExperienceRepository;
import com.portfolio.repository.PersonalInfoRepository;
import com.portfolio.repository.ProjectRepository;
import com.portfolio.repository.SkillRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PersonalInfoRepository personalInfoRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private EducationRepository educationRepository;
    
    @Autowired
    private ExperienceRepository experienceRepository;
    
    @Autowired
    private CertificationRepository certificationRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (personalInfoRepository.count() == 0) {
            initializePersonalInfo();
        }
        
        // Always re-initialize projects to ensure all entries are present
        projectRepository.deleteAll();
        initializeProjects();
        
        // Always re-initialize skills to ensure all categories are present
        skillRepository.deleteAll();
        initializeSkills();
        
        // Always re-initialize education to ensure all entries are present
        educationRepository.deleteAll();
        initializeEducation();
        
        if (experienceRepository.count() == 0) {
            initializeExperience();
        }
        
        // Always re-initialize certifications to ensure all certificates are present
        certificationRepository.deleteAll();
        initializeCertifications();
    }

    private void initializePersonalInfo() {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("Gourav Kumar");
        personalInfo.setTitle("Java Developer & Full Stack Developer");
        personalInfo.setEmail("gouravkrsah78@gmail.com");
        personalInfo.setPhone("7903840357");
        personalInfo.setLocation("Forbesganj, Bihar");
        personalInfo.setLinkedin("https://linkedin.com/in/gourav");
        personalInfo.setGithub("https://github.com/gourav");
        personalInfo.setPortfolio("https://gourav-portfolio.com");
        personalInfo.setSummary("B.Tech CSE student with 9.0 CGPA, experienced Java Developer Intern at TechnoHacks EduTech. Passionate about building scalable applications using Spring Boot, Angular, and modern web technologies. Strong foundation in Data Structures & Algorithms with expertise in full-stack development.");
        
        personalInfoRepository.save(personalInfo);
        System.out.println("Personal Information initialized!");
    }

    private void initializeProjects() {
        List<Project> projects = Arrays.asList(
            // HealthBridge Project
            createProject(
                "HealthBridge - Telehealth Platform",
                "A comprehensive full-stack telehealth platform designed to bridge the gap between healthcare providers and patients. Built with modern web technologies to provide seamless virtual healthcare experiences.",
                "Spring Boot, Angular, TypeScript, MySQL, OAuth2, JWT, RBAC, Spring Security, REST APIs",
                "https://github.com/Gourav3308/Healthbridge_Prj",
                null, // No live URL as requested
                "data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNTAwIiBoZWlnaHQ9IjQwMCIgdmlld0JveD0iMCAwIDUwMCA0MDAiIGZpbGw9Im5vbmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjxyZWN0IHdpZHRoPSI1MDAiIGhlaWdodD0iNDAwIiBmaWxsPSIjNGI2M2YxIi8+Cjx0ZXh0IHg9IjI1MCIgeT0iMjAwIiBmb250LWZhbWlseT0iQXJpYWwiIGZvbnQtc2l6ZT0iMzIiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiPkhlYWx0aEJyaWRnZTwvdGV4dD4KPC9zdmc+", // HealthBridge project image
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 10, 1),
                false,
                Arrays.asList(
                    "Appointment Scheduling & Cancellation",
                    "Video Consultations with WebRTC",
                    "Patient Reviews & Rating System",
                    "Comprehensive Reporting Dashboards",
                    "Real-time Notifications",
                    "Secure Image & File Uploads",
                    "OAuth2 Authentication (Google)",
                    "Role-based Access Control (Admin/Manager/User)",
                    "Centralized Logging & Error Handling",
                    "Unit & Integration Testing"
                ),
                "Full Stack"
            ),
            // SmartBank Project
            createProject(
                "SmartBank - Banking API System",
                "A robust backend banking system providing secure APIs for account management, transaction processing, and administrative workflows. Implemented with enterprise-grade security and data integrity measures.",
                "Spring Boot, Spring Data JPA, Spring Security, BCrypt, REST APIs, MySQL, JWT",
                "https://github.com/Gourav3308/SmartBank-Spring-Project",
                null, // No live URL as requested
                "data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNTAwIiBoZWlnaHQ9IjQwMCIgdmlld0JveD0iMCAwIDUwMCA0MDAiIGZpbGw9Im5vbmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjxyZWN0IHdpZHRoPSI1MDAiIGhlaWdodD0iNDAwIiBmaWxsPSIjMTY1MzczIi8+Cjx0ZXh0IHg9IjI1MCIgeT0iMjAwIiBmb250LWZhbWlseT0iQXJpYWwiIGZvbnQtc2l6ZT0iMzIiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiPlNtYXJ0QmFuazwvdGV4dD4KPC9zdmc+", // SmartBank project image
                LocalDate.of(2024, 8, 1),
                LocalDate.of(2024, 10, 1),
                false,
                Arrays.asList(
                    "Account Management System",
                    "Transaction Processing (Deposits, Withdrawals, Transfers)",
                    "Administrative Workflow Management",
                    "Role-based Access Control (Admin/Manager/User)",
                    "Password Hashing with BCrypt",
                    "Transactional Integrity with @Transactional",
                    "Bean Validation & Error Handling",
                    "RESTful API Design",
                    "Database Schema Optimization",
                    "Comprehensive API Documentation"
                ),
                "Backend"
            ),
            // Payment Gateway Project
            createProject(
                "Spring Boot Payment Gateway Integration",
                "A comprehensive payment processing system integrating Razorpay payment gateway with Spring Boot 3. Features secure authentication, transaction management, and administrative controls for seamless e-commerce operations.",
                "Spring Boot 3, Razorpay API, MySQL, Google OAuth 2.0, Spring Security, REST APIs, JWT",
                "https://github.com/Gourav3308/PaymentAppOath2",
                null, // No live URL as requested
                "data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNTAwIiBoZWlnaHQ9IjQwMCIgdmlld0JveD0iMCAwIDUwMCA0MDAiIGZpbGw9Im5vbmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjxyZWN0IHdpZHRoPSI1MDAiIGhlaWdodD0iNDAwIiBmaWxsPSIjZTkxZTYzIi8+Cjx0ZXh0IHg9IjI1MCIgeT0iMjAwIiBmb250LWZhbWlseT0iQXJpYWwiIGZvbnQtc2l6ZT0iMjgiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiPlBheW1lbnQgR2F0ZXdheTwvdGV4dD4KPC9zdmc+", // Payment Gateway project image
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 4, 1),
                false,
                Arrays.asList(
                    "Razorpay Payment Gateway Integration",
                    "Order Creation & Payment Processing",
                    "Payment Capture & Refund Management",
                    "Signature Verification & Security",
                    "Google OAuth 2.0 Authentication",
                    "Role-based Authorization (Admin/User)",
                    "Admin Dashboard for Transaction Monitoring",
                    "User Management System",
                    "Server-side Filtering & Pagination",
                    "Real-time Payment Status Updates",
                    "Secure API Endpoints",
                    "Comprehensive Error Handling"
                ),
                "Backend"
            ),
            // SchoolWeb Project
            createProject(
                "SchoolWeb",
                "Modern school website with an Angular frontend and a Spring Boot backend. It provides public pages (home, facilities, gallery, notices, fees, contact) and an admin area for managing content such as student registrations and notices.",
                "Angular, TypeScript, SCSS, Spring Boot, Spring Security, Spring Data JPA, Maven",
                "https://github.com/Gourav3308/SchoolWeb",
                "https://schoolweb-1-homv.onrender.com",
                null,
                LocalDate.of(2025, 9, 1),
                null,
                true,
                Arrays.asList(
                    "Responsive Angular UI for school information",
                    "Secure Spring Boot API for notices, students, and fees",
                    "Email notification support via pluggable service",
                    "Admin area to manage students, notices, and content",
                    "Production-ready split of frontend and backend apps",
                    "Configurable CORS and security settings"
                ),
                "Full Stack"
            )
        );
        
        projectRepository.saveAll(projects);
        System.out.println("Projects initialized!");
    }

    private Project createProject(String name, String description, String technologies, String githubUrl, 
                                String liveUrl, String imageUrl, LocalDate startDate, LocalDate endDate, 
                                boolean isCurrent, List<String> features, String category) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setTechnologies(technologies);
        project.setGithubUrl(githubUrl);
        project.setLiveUrl(liveUrl);
        project.setImageUrl(imageUrl);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setCurrent(isCurrent);
        project.setFeatures(features);
        project.setCategory(category);
        return project;
    }

    private void initializeSkills() {
        List<Skill> skills = Arrays.asList(
            // Languages
            createSkill("Java", 90, "Languages", "fas fa-code", "Core Java, OOP, Data Structures & Algorithms"),
            createSkill("JavaScript", 85, "Languages", "fas fa-code", "ES6+, DOM Manipulation, Async Programming"),
            createSkill("TypeScript", 90, "Languages", "fas fa-code", "TypeScript, Angular, Type Safety"),
            createSkill("SQL", 85, "Languages", "fas fa-code", "MySQL, Database Design, Query Optimization"),
            createSkill("HTML", 90, "Languages", "fas fa-code", "HTML5, Semantic Markup, Accessibility"),
            createSkill("CSS", 85, "Languages", "fas fa-code", "CSS3, Responsive Design, Flexbox, Grid"),
            
            // Backend
            createSkill("Spring Boot", 88, "Backend", "fas fa-code", "REST APIs, Microservices, Spring Security"),
            createSkill("Spring MVC", 90, "Backend", "fas fa-code", "MVC Pattern, Controllers, View Resolution"),
            createSkill("Spring Data JPA", 90, "Backend", "fas fa-code", "JPA, Hibernate, Repository Pattern"),
            createSkill("Hibernate", 85, "Backend", "fas fa-code", "ORM, Entity Mapping, HQL"),
            createSkill("RESTful APIs", 95, "Backend", "fas fa-code", "REST Architecture, HTTP Methods, API Design"),
            createSkill("Spring Security", 85, "Backend", "fas fa-code", "Authentication, Authorization, JWT, OAuth2"),
            createSkill("JWT", 80, "Backend", "fas fa-code", "JSON Web Tokens, Token-based Authentication"),
            createSkill("OAuth2", 75, "Backend", "fas fa-code", "OAuth2 Flow, Google OAuth, Authorization"),
            
            // Frontend
            createSkill("Angular", 85, "Frontend", "fas fa-code", "Angular 17+, Components, Services, Routing"),
            createSkill("Angular CLI", 85, "Frontend", "fas fa-code", "CLI Commands, Project Generation, Build Tools"),
            createSkill("Bootstrap", 80, "Frontend", "fas fa-code", "Responsive Design, Components, Utilities"),
            createSkill("Thymeleaf", 75, "Frontend", "fas fa-code", "Server-side Templating, Spring Integration"),
            
            // Databases
            createSkill("MySQL", 90, "Databases", "fas fa-code", "Relational Database, ACID Properties, Indexing"),
            createSkill("MongoDB", 80, "Databases", "fas fa-code", "NoSQL, Document Database, Aggregation"),
            
            // Tools
            createSkill("Git", 90, "Tools", "fas fa-code", "Version Control, Branching, Merging, GitHub"),
            createSkill("GitHub", 90, "Tools", "fas fa-code", "Repository Management, Collaboration, CI/CD"),
            createSkill("IntelliJ IDEA", 85, "Tools", "fas fa-code", "IDE, Debugging, Code Analysis"),
            createSkill("Eclipse", 80, "Tools", "fas fa-code", "IDE, Java Development, Plugins"),
            createSkill("VS Code", 85, "Tools", "fas fa-code", "Code Editor, Extensions, Debugging"),
            createSkill("Postman", 90, "Tools", "fas fa-code", "API Testing, Collection Management, Automation"),
            createSkill("Swagger/OpenAPI", 85, "Tools", "fas fa-code", "API Documentation, Interactive Docs"),
            
            // Build & Testing
            createSkill("Maven", 90, "Build & Testing", "fas fa-code", "Dependency Management, Build Lifecycle, Plugins"),
            createSkill("JUnit", 85, "Build & Testing", "fas fa-code", "Unit Testing, Test Cases, Assertions"),
            createSkill("Mockito", 80, "Build & Testing", "fas fa-code", "Mocking, Test Doubles, Behavior Verification"),
            
            // Concepts
            createSkill("OOP", 95, "Concepts", "fas fa-code", "Object-Oriented Programming, Encapsulation, Inheritance"),
            createSkill("Data Structures & Algorithms", 90, "Concepts", "fas fa-code", "Arrays, Linked Lists, Trees, Sorting, Searching"),
            createSkill("Microservices", 70, "Concepts", "fas fa-code", "Distributed Systems, Service Communication, Scalability")
        );
        
        skillRepository.saveAll(skills);
        System.out.println("Skills initialized!");
    }

    private Skill createSkill(String name, int proficiency, String category, String icon, String description) {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setProficiency(proficiency);
        skill.setCategory(category);
        skill.setIcon(icon);
        skill.setDescription(description);
        return skill;
    }

    private void initializeEducation() {
        List<Education> educationList = Arrays.asList(
            // B.Tech CSE
            createEducation(
                "B.Tech in Computer Science and Engineering",
                "Centurion University of Technology and Management",
                "Paralakhemundi, Odisha",
                "08/2022",
                "05/2026",
                "9.0/10",
                "Pursuing Bachelor of Technology in Computer Science and Engineering with focus on software development, data structures, algorithms, and modern web technologies."
            ),
            // Intermediate
            createEducation(
                "Intermediate (12th Grade)",
                "BDBKS College",
                "Forbesganj, Araria, Bihar",
                "04/2017",
                "03/2019",
                "77%",
                "Completed Intermediate education with focus on Science stream, laying the foundation for higher studies in engineering."
            ),
            // Matriculation
            createEducation(
                "Matriculation (10th Grade)",
                "Ram Lal High School",
                "Haripur, Forbesganj, Araria, Bihar",
                "04/2016",
                "03/2017",
                "80%",
                "Completed matriculation education with excellent academic performance, establishing a strong foundation for future studies."
            )
        );
        
        educationRepository.saveAll(educationList);
        System.out.println("Education initialized!");
    }

    private Education createEducation(String degree, String university, String location, 
                                    String startDate, String endDate, String gpa, String description) {
        Education education = new Education();
        education.setDegree(degree);
        education.setUniversity(university);
        education.setLocation(location);
        education.setStartDate(startDate);
        education.setEndDate(endDate);
        education.setGpa(gpa);
        education.setDescription(description);
        return education;
    }

    private void initializeExperience() {
        Experience experience = new Experience();
        experience.setJobTitle("Java Developer Intern");
        experience.setCompany("TechnoHacks EduTech");
        experience.setLocation("Remote (Nashik, Maharashtra)");
        experience.setStartDate("08/2025");
        experience.setEndDate("09/2025");
        experience.setCurrent(false);
        experience.setType("Intern");
        experience.setDescription("Gained hands-on experience in Java development, Spring Boot, and modern web technologies through practical projects and mentorship.");
        experience.setResponsibilities(Arrays.asList(
            "Implemented Spring Boot REST APIs with validation, exception handling, and data access using Spring Data JPA/Hibernate",
            "Created comprehensive API documentation with Swagger/OpenAPI for enhanced developer experience",
            "Built Java web applications showcasing both annotation-based servlet configuration (@WebServlet) and traditional web.xml-based servlet mapping",
            "Developed and tested RESTful APIs using Postman for API testing and validation",
            "Collaborated with team using Git/GitHub for version control and code management",
            "Utilized IntelliJ IDEA for development, debugging, and code analysis"
        ));
        experience.setTechnologies(Arrays.asList(
            "Java", "Spring Boot", "Spring MVC", "REST APIs", "JPA/Hibernate", "Maven", 
            "JUnit/Mockito", "Postman", "Git/GitHub", "IntelliJ IDEA"
        ));
        
        experienceRepository.save(experience);
        System.out.println("Experience initialized!");
    }

    private void initializeCertifications() {
        List<Certification> certifications = Arrays.asList(
            createCertification(
                "Java Full-Stack Development",
                "Smart Programming",
                "2025",
                null,
                null,
                "https://via.placeholder.com/800x600/e91e63/ffffff?text=Java+Full-Stack+Certificate",
                "Comprehensive course covering Java fundamentals to advanced concepts, Spring Framework ecosystem (Spring Boot, Spring MVC, Spring Data JPA, Spring Security), frontend technologies (HTML5, CSS3, JavaScript ES6+), MySQL database management, and modern development practices including RESTful APIs and microservices architecture.",
                "Active"
            ),
            createCertification(
                "Quantum Computing Basics & Applications",
                "Centurion University of Technology & Management",
                "07/2024",
                null,
                "CT1735-tN9jX0m-cd2",
                "https://drive.google.com/file/d/1Ad1HxPkr66XDuuR7R48VGmSb1iTvi7yo/view?usp=drive_link",
                "Certificate of Participation for successfully completing a three-day workshop on Quantum Computing Basics & Applications, organized by the Centre for Computational Mathematics in association with School of Engineering and Technology, School of Applied Sciences and Centre of Excellence for Quantum Computing.",
                "Active"
            ),
            createCertification(
                "Data Structures and Algorithms using C for Placements",
                "CodeTantra",
                "03/2024",
                null,
                "CT1735-tN9jX0m-cd2",
                "https://drive.google.com/file/d/1kgDiuaHM45vWdSCJyljONqkMeAhcyJP3/view?usp=sharing",
                "Certificate of Completion for successfully completing the comprehensive course on Data Structures and Algorithms using C programming language, specifically designed for technical placements and competitive programming.",
                "Active"
            ),
            createCertification(
                "Cloud Practitioner Essentials",
                "Aspire For Her in collaboration with AWS",
                "10/2023",
                null,
                null,
                "https://drive.google.com/file/d/1QvxBkVqF1cnvLwRr7o9qO7lDcvCE5yzS/view?usp=sharing",
                "Certificate of Participation awarded upon successfully completing the Cloud Practitioner Essentials course, providing foundational knowledge of cloud computing concepts and AWS services.",
                "Active"
            ),
            createCertification(
                "Java Development Internship",
                "TechnoHacks Solutions Pvt. Ltd.",
                "08/2025",
                null,
                "TH10950",
                "https://drive.google.com/file/d/1JlCg6wlKuGBJ8elNEXQ6ANadTUJcEndk/view?usp=drive_link",
                "Certificate of successful completion of Java Development internship from August 1, 2025 to August 31, 2025. Recognized for consistent performance and hard work during the internship period.",
                "Active"
            ),
            createCertification(
                "GeeksforGeeks CUTM Training Program",
                "GeeksforGeeks",
                "2024",
                null,
                "12e4ca54b2c98458233c06a867d053e4",
                "https://media.geeksforgeeks.org/courses/certificates/12e4ca54b2c98458233c06a867d053e4.pdf",
                "Certificate of completion for successfully completing a 16-week comprehensive course on GeeksforGeeks CUTM Training Program at Paralakhemundi Campus, covering advanced programming concepts and practical applications.",
                "Active"
            )
        );
        
        certificationRepository.saveAll(certifications);
        System.out.println("Certifications initialized!");
    }

    private Certification createCertification(String name, String organization, String issueDate, 
                                           String expiryDate, String credentialId, String credentialUrl, 
                                           String description, String status) {
        Certification certification = new Certification();
        certification.setName(name);
        certification.setIssuingOrganization(organization);
        certification.setIssueDate(issueDate);
        certification.setExpiryDate(expiryDate);
        certification.setCredentialId(credentialId);
        certification.setCredentialUrl(credentialUrl);
        certification.setDescription(description);
        certification.setStatus(status);
        return certification;
    }
}

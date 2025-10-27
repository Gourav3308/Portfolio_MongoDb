import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Skill } from '../../../models/skill.model';

@Component({
  selector: 'app-category-skills',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './category-skills.html',
  styleUrls: ['./category-skills.scss']
})
export class CategorySkillsComponent implements OnInit {
  @Input() category: string = '';
  @Input() skills: Skill[] = [];
  @Output() backToSkills = new EventEmitter<void>();

  ngOnInit(): void {
    // Component initialization
  }

  getCategoryIcon(category: string): string {
    const iconMap: { [key: string]: string } = {
      'Languages': 'fas fa-code',
      'Backend': 'fas fa-server',
      'Frontend': 'fas fa-desktop',
      'Databases': 'fas fa-database',
      'Tools': 'fas fa-tools',
      'Build & Testing': 'fas fa-cogs',
      'Concepts': 'fas fa-lightbulb'
    };
    return iconMap[category] || 'fas fa-code';
  }

  getCategoryDescription(category: string): string {
    const descriptions: { [key: string]: string } = {
      'Languages': 'Programming languages and markup languages I work with',
      'Backend': 'Server-side technologies and frameworks I use',
      'Frontend': 'Client-side technologies and UI frameworks',
      'Databases': 'Database systems and data management tools',
      'Tools': 'Development tools and platforms I utilize',
      'Build & Testing': 'Build tools and testing frameworks',
      'Concepts': 'Core programming concepts and methodologies'
    };
    return descriptions[category] || 'Technical skills and expertise';
  }

  getSkillIcon(skillName: string): string {
    const iconMap: { [key: string]: string } = {
      // Languages
      'Java': 'fab fa-java',
      'JavaScript': 'fab fa-js-square',
      'TypeScript': 'fab fa-js-square',
      'SQL': 'fas fa-database',
      'HTML': 'fab fa-html5',
      'CSS': 'fab fa-css3-alt',
      
      // Backend
      'Spring Boot': 'fas fa-leaf',
      'Spring MVC': 'fas fa-leaf',
      'Spring Data JPA': 'fas fa-leaf',
      'Hibernate': 'fas fa-leaf',
      'RESTful APIs': 'fas fa-cloud',
      'Spring Security': 'fas fa-shield-alt',
      'JWT': 'fas fa-key',
      'OAuth2': 'fas fa-lock',
      
      // Frontend
      'Angular': 'fab fa-angular',
      'Angular CLI': 'fab fa-angular',
      'Bootstrap': 'fab fa-bootstrap',
      'Thymeleaf': 'fas fa-file-code',
      
      // Databases
      'MySQL': 'fas fa-database',
      'MongoDB': 'fas fa-database',
      
      // Tools
      'Git': 'fab fa-git-alt',
      'GitHub': 'fab fa-github',
      'IntelliJ IDEA': 'fas fa-code',
      'Eclipse': 'fas fa-code',
      'VS Code': 'fas fa-code',
      'Postman': 'fas fa-rocket',
      'Swagger/OpenAPI': 'fas fa-book',
      
      // Build & Testing
      'Maven': 'fas fa-cogs',
      'JUnit': 'fas fa-vial',
      'Mockito': 'fas fa-vial',
      
      // Concepts
      'OOP': 'fas fa-cube',
      'Data Structures & Algorithms': 'fas fa-sort',
      'Microservices': 'fas fa-network-wired'
    };
    return iconMap[skillName] || 'fas fa-code';
  }

  getSkillIconColor(skillName: string): string {
    const colorMap: { [key: string]: string } = {
      // Languages
      'Java': '#f89820',
      'JavaScript': '#f7df1e',
      'TypeScript': '#3178c6',
      'SQL': '#336791',
      'HTML': '#e34f26',
      'CSS': '#1572b6',
      
      // Backend
      'Spring Boot': '#6db33f',
      'Spring MVC': '#6db33f',
      'Spring Data JPA': '#6db33f',
      'Hibernate': '#59666c',
      'RESTful APIs': '#61dafb',
      'Spring Security': '#6db33f',
      'JWT': '#000000',
      'OAuth2': '#4285f4',
      
      // Frontend
      'Angular': '#dd0031',
      'Angular CLI': '#dd0031',
      'Bootstrap': '#7952b3',
      'Thymeleaf': '#005f0f',
      
      // Databases
      'MySQL': '#4479a1',
      'MongoDB': '#47a248',
      
      // Tools
      'Git': '#f05032',
      'GitHub': '#181717',
      'IntelliJ IDEA': '#000000',
      'Eclipse': '#2c2255',
      'VS Code': '#007acc',
      'Postman': '#ff6c37',
      'Swagger/OpenAPI': '#85ea2d',
      
      // Build & Testing
      'Maven': '#c71a36',
      'JUnit': '#25a162',
      'Mockito': '#25a162',
      
      // Concepts
      'OOP': '#ff6b6b',
      'Data Structures & Algorithms': '#4ecdc4',
      'Microservices': '#45b7d1'
    };
    return colorMap[skillName] || '#667eea';
  }

  getProgressBarColor(proficiency: number): string {
    if (proficiency >= 90) return 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)';
    if (proficiency >= 80) return 'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)';
    if (proficiency >= 70) return 'linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)';
    if (proficiency >= 60) return 'linear-gradient(135deg, #43e97b 0%, #38f9d7 100%)';
    return 'linear-gradient(135deg, #fa709a 0%, #fee140 100%)';
  }

  getProficiencyText(proficiency: number): string {
    if (proficiency >= 90) return 'Expert';
    if (proficiency >= 80) return 'Advanced';
    if (proficiency >= 70) return 'Intermediate';
    if (proficiency >= 60) return 'Beginner';
    return 'Learning';
  }

  getProficiencyClass(proficiency: number): string {
    if (proficiency >= 90) return 'expert';
    if (proficiency >= 80) return 'advanced';
    if (proficiency >= 70) return 'intermediate';
    if (proficiency >= 60) return 'beginner';
    return 'learning';
  }

  getAverageProficiency(): number {
    if (this.skills.length === 0) return 0;
    const total = this.skills.reduce((sum, skill) => sum + skill.proficiency, 0);
    return Math.round(total / this.skills.length);
  }

  getExpertSkillsCount(): number {
    return this.skills.filter(skill => skill.proficiency >= 90).length;
  }

  goBack(): void {
    this.backToSkills.emit();
  }
}





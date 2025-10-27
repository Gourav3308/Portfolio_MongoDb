import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-skills-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './skills-navbar.html',
  styleUrls: ['./skills-navbar.scss']
})
export class SkillsNavbarComponent {
  @Input() categories: string[] = [];
  @Input() activeCategory: string = '';
  @Input() skillCounts: { [key: string]: number } = {};
  @Output() categorySelected = new EventEmitter<string>();

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

  getSkillCount(category: string): number {
    return this.skillCounts[category] || 0;
  }

  selectCategory(category: string): void {
    this.activeCategory = category;
    this.categorySelected.emit(category);
  }
}


import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Project } from '../../models/project.model';
import { PortfolioService } from '../../services/portfolio';
import { LoadingSpinnerComponent } from '../loading-spinner/loading-spinner.component';

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [CommonModule, RouterModule, LoadingSpinnerComponent],
  templateUrl: './projects.html',
  styleUrls: ['./projects.scss']
})
export class ProjectsComponent implements OnInit {
  projects: Project[] = [];
  loading = true;
  private imageRetryState: Record<string, number> = {};
  private imageCandidatesMap: Record<string, string[]> = {};
  private customImageMap: Record<string, string> = {
    // Use lowercase keys for robust matching
    'healthbridge - telehealth platform': 'assets/images/healthbridge.jpg',
    'smartbank - banking api system': 'assets/images/smartbank.jpg',
    'spring boot payment gateway integration': 'assets/images/springboot payemnt app.jpg',
    'schoolweb': 'assets/images/schoolweb.jpg'
  };

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this.loadProjects();
  }

  loadProjects(): void {
    this.portfolioService.getProjects().subscribe({
      next: (projects) => {
        this.projects = projects;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading projects:', error);
        this.loading = false;
      }
    });
  }

  getProjectImage(project: Project): string {
    const key = project.id || project.name;
    // Direct mapping if provided (case-insensitive)
    const mapped = this.customImageMap[(project.name || '').toLowerCase().trim()];
    if (mapped) {
      return mapped;
    }
    if (project.imageUrl && project.imageUrl.trim().length > 0) {
      return project.imageUrl;
    }
    if (!this.imageCandidatesMap[key]) {
      this.imageCandidatesMap[key] = this.buildImageCandidates(project.name);
      this.imageRetryState[key] = 0;
    }
    return this.imageCandidatesMap[key][0] || 'assets/images/project-placeholder.png';
  }

  handleImgError(event: Event, project: Project): void {
    const target = event.target as HTMLImageElement;
    const key = project.id || project.name;
    if (!this.imageCandidatesMap[key]) {
      this.imageCandidatesMap[key] = this.buildImageCandidates(project.name);
    }
    const attempt = (this.imageRetryState[key] ?? 0) + 1;
    this.imageRetryState[key] = attempt;
    const candidates = this.imageCandidatesMap[key];
    if (attempt < candidates.length) {
      target.src = candidates[attempt];
    } else {
      target.src = 'assets/images/project-placeholder.png';
    }
  }

  private slugify(text: string): string {
    return text
      .toLowerCase()
      .replace(/[^a-z0-9]+/g, '-')
      .replace(/(^-|-$)+/g, '');
  }

  private buildImageCandidates(name: string): string[] {
    const baseDir = 'assets/images/';
    const original = name.trim();
    const lower = original.toLowerCase().replace(/\s+/g, ' ');
    const hyphen = this.slugify(original);
    const underscore = lower.replace(/[^a-z0-9]+/g, '_').replace(/^_+|_+$/g, '');
    const compact = lower.replace(/[^a-z0-9]+/g, '');
    const cleaned = original.replace(/[()\[\]{}'`"!@#$%^&*,.?<>:;|\\/+~=]+/g, '').replace(/\s+/g, ' ').trim();
    const cleanedLower = cleaned.toLowerCase();
    const exts = ['png', 'jpg', 'jpeg', 'webp', 'PNG', 'JPG', 'JPEG', 'WEBP'];

    const unique = new Set<string>();
    const push = (p: string) => { if (!unique.has(p)) unique.add(p); };

    // Try plain names as provided
    exts.forEach(ext => push(`${baseDir}${original}.${ext}`));
    exts.forEach(ext => push(`${baseDir}${cleaned}.${ext}`));
    exts.forEach(ext => push(`${baseDir}${lower}.${ext}`));
    exts.forEach(ext => push(`${baseDir}${cleanedLower}.${ext}`));
    // Try formatted variants
    exts.forEach(ext => push(`${baseDir}${hyphen}.${ext}`));
    exts.forEach(ext => push(`${baseDir}${underscore}.${ext}`));
    exts.forEach(ext => push(`${baseDir}${compact}.${ext}`));

    return Array.from(unique);
  }
}

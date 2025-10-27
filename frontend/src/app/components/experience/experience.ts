import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Experience } from '../../models/experience.model';
import { PortfolioService } from '../../services/portfolio';
import { LoadingSpinnerComponent } from '../loading-spinner/loading-spinner.component';

@Component({
  selector: 'app-experience',
  standalone: true,
  imports: [CommonModule, LoadingSpinnerComponent],
  templateUrl: './experience.html',
  styleUrls: ['./experience.scss']
})
export class ExperienceComponent implements OnInit {
  experienceList: Experience[] = [];
  loading = true;

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this.loadExperience();
  }

  loadExperience(): void {
    this.portfolioService.getExperience().subscribe({
      next: (experience) => {
        this.experienceList = experience;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading experience:', error);
        this.loading = false;
      }
    });
  }
}
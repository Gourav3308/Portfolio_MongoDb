import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Education } from '../../models/education.model';
import { PortfolioService } from '../../services/portfolio';
import { LoadingSpinnerComponent } from '../loading-spinner/loading-spinner.component';

@Component({
  selector: 'app-education',
  standalone: true,
  imports: [CommonModule, LoadingSpinnerComponent],
  templateUrl: './education.html',
  styleUrls: ['./education.scss']
})
export class EducationComponent implements OnInit {
  educationList: Education[] = [];
  loading = true;
  errorMessage = '';

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this.loadEducation();
  }

  loadEducation(): void {
    this.loading = true;
    this.errorMessage = '';
    
    this.portfolioService.getEducation().subscribe({
      next: (education) => {
        this.educationList = education;
        this.loading = false;
        this.errorMessage = '';
      },
      error: (error) => {
        console.error('Error loading education:', error);
        this.loading = false;
        this.errorMessage = error.message || 'Failed to load education data. Please try again.';
      }
    });
  }
}
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Education } from '../../models/education.model';
import { PortfolioService } from '../../services/portfolio';

@Component({
  selector: 'app-education',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './education.html',
  styleUrls: ['./education.scss']
})
export class EducationComponent implements OnInit {
  educationList: Education[] = [];

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this.loadEducation();
  }

  loadEducation(): void {
    this.portfolioService.getEducation().subscribe({
      next: (education) => {
        this.educationList = education;
      },
      error: (error) => {
        console.error('Error loading education:', error);
      }
    });
  }
}
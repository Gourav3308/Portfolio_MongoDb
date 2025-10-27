import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PersonalInfo } from '../../models/personal-info.model';
import { PortfolioService } from '../../services/portfolio';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './about.html',
  styleUrls: ['./about.scss']
})
export class AboutComponent implements OnInit {
  personalInfo: PersonalInfo | null = null;
  loading = true;

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this.loadPersonalInfo();
  }

  loadPersonalInfo(): void {
    this.portfolioService.getPersonalInfo().subscribe({
      next: (personalInfoList) => {
        if (personalInfoList.length > 0) {
          this.personalInfo = personalInfoList[0];
        }
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading personal info:', error);
        this.loading = false;
      }
    });
  }
}

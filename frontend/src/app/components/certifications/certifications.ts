import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Certification } from '../../models/certification.model';
import { PortfolioService } from '../../services/portfolio';
import { LoadingSpinnerComponent } from '../loading-spinner/loading-spinner.component';

@Component({
  selector: 'app-certifications',
  standalone: true,
  imports: [CommonModule, LoadingSpinnerComponent],
  templateUrl: './certifications.html',
  styleUrls: ['./certifications.scss']
})
export class CertificationsComponent implements OnInit {
  certificationList: Certification[] = [];
  loading = true;

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this.loadCertifications();
  }

  loadCertifications(): void {
    this.portfolioService.getCertifications().subscribe({
      next: (certifications) => {
        this.certificationList = certifications;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading certifications:', error);
        this.loading = false;
      }
    });
  }

  getStatusClass(status: string | undefined): string {
    if (!status) {
      return 'status-default';
    }
    
    switch (status.toLowerCase()) {
      case 'active':
        return 'status-active';
      case 'ongoing':
        return 'status-ongoing';
      case 'expired':
        return 'status-expired';
      default:
        return 'status-default';
    }
  }

  openCertificateInNewTab(certification: Certification): void {
    if (certification.credentialUrl) {
      window.open(certification.credentialUrl, '_blank');
    }
  }
}
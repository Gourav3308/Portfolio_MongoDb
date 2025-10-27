import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { environment } from '../../../environments/environment';
import { PersonalInfo } from '../../models/personal-info.model';
import { PortfolioService } from '../../services/portfolio';

interface ContactForm {
  name: string;
  email: string;
  subject: string;
  message: string;
}

interface MessageStatus {
  type: 'success' | 'error';
  message: string;
}

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './contact.html',
  styleUrls: ['./contact.scss']
})
export class ContactComponent implements OnInit {
  personalInfo: PersonalInfo | null = null;
  contactForm: ContactForm = {
    name: '',
    email: '',
    subject: '',
    message: ''
  };
  
  isSubmitting: boolean = false;
  messageStatus: MessageStatus | null = null;

  constructor(
    private portfolioService: PortfolioService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadPersonalInfo();
  }

  loadPersonalInfo(): void {
    this.portfolioService.getPersonalInfo().subscribe({
      next: (personalInfoList) => {
        if (personalInfoList.length > 0) {
          this.personalInfo = personalInfoList[0];
        }
      },
      error: (error) => {
        console.error('Error loading personal info:', error);
      }
    });
  }

  onSubmit(): void {
    if (this.isSubmitting) return;
    
    this.isSubmitting = true;
    this.messageStatus = null;
    
    // Send email via backend
    this.http.post(`${environment.apiUrl}/contact/send`, this.contactForm)
      .subscribe({
        next: (response) => {
          this.messageStatus = {
            type: 'success',
            message: 'Thank you for your message! I will get back to you soon. See you soon!'
          };
          this.resetForm();
          this.isSubmitting = false;
        },
        error: (error) => {
          console.error('Error sending message:', error);
          this.messageStatus = {
            type: 'error',
            message: 'Sorry, there was an error sending your message. Please try again or contact me directly at gouravkrsah78@gmail.com'
          };
          this.isSubmitting = false;
        }
      });
  }

  private resetForm(): void {
    this.contactForm = {
      name: '',
      email: '',
      subject: '',
      message: ''
    };
  }
}

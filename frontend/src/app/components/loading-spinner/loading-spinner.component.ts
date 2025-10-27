import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-loading-spinner',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="loading-spinner" [class.small]="size === 'small'" [class.large]="size === 'large'">
      <div class="spinner-border" role="status">
        <span class="visually-hidden">Loading...</span>
      </div>
      <div *ngIf="message" class="loading-message">{{ message }}</div>
    </div>
  `,
  styles: [`
    .loading-spinner {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      padding: 2rem;
      
      .spinner-border {
        width: 3rem;
        height: 3rem;
        border: 0.3rem solid rgba(59, 130, 246, 0.25);
        border-right-color: #3b82f6;
        border-radius: 50%;
        animation: spinner-border 0.75s linear infinite;
      }
      
      &.small .spinner-border {
        width: 1.5rem;
        height: 1.5rem;
        border-width: 0.15rem;
      }
      
      &.large .spinner-border {
        width: 4rem;
        height: 4rem;
        border-width: 0.4rem;
      }
      
      .loading-message {
        margin-top: 1rem;
        color: var(--text-secondary);
        font-size: 0.9rem;
        text-align: center;
      }
    }
    
    @keyframes spinner-border {
      to {
        transform: rotate(360deg);
      }
    }
  `]
})
export class LoadingSpinnerComponent {
  @Input() size: 'small' | 'medium' | 'large' = 'medium';
  @Input() message: string = '';
}

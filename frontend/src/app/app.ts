import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar';
import { ThemeToggleComponent } from './components/theme-toggle/theme-toggle.component';
import { ThemeService } from './services/theme.service';
import { PortfolioService } from './services/portfolio';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, ThemeToggleComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {
  protected readonly title = signal('portfolio-frontend');

  constructor(private themeService: ThemeService, private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    // Initialize theme on app start
    this.themeService.getCurrentTheme();

    // Warm up backend/API to avoid first-load timeouts
    // Fire-and-forget; we don't surface errors to the UI here
    this.portfolioService.pingHealth().subscribe({
      next: () => {},
      error: () => {}
    });
  }
}

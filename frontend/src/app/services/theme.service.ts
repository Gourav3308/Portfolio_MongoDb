import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private isDarkMode = new BehaviorSubject<boolean>(true);
  public isDarkMode$ = this.isDarkMode.asObservable();

  constructor() {
    // Check for saved theme preference or default to dark mode
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme) {
      this.isDarkMode.next(savedTheme === 'dark');
    } else {
      this.isDarkMode.next(true); // Default to dark mode
    }
    
    // Apply initial theme
    this.applyTheme();
  }

  toggleTheme(): void {
    const newTheme = !this.isDarkMode.value;
    this.isDarkMode.next(newTheme);
    localStorage.setItem('theme', newTheme ? 'dark' : 'light');
    this.applyTheme();
  }

  private applyTheme(): void {
    const isDark = this.isDarkMode.value;
    document.documentElement.setAttribute('data-theme', isDark ? 'dark' : 'light');
    
    // Update body class for additional styling
    if (isDark) {
      document.body.classList.add('dark-theme');
      document.body.classList.remove('light-theme');
      document.body.style.backgroundColor = '#0f1419';
      document.body.style.color = '#ffffff';
    } else {
      document.body.classList.add('light-theme');
      document.body.classList.remove('dark-theme');
      document.body.style.backgroundColor = '#ffffff';
      document.body.style.color = '#1f2937';
    }
  }

  getCurrentTheme(): boolean {
    return this.isDarkMode.value;
  }
}

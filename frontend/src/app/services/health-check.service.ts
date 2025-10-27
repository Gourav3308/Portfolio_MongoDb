import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, interval, Observable, of, switchMap } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HealthCheckService {
  private apiUrl = environment.apiUrl;
  private healthStatusSubject = new BehaviorSubject<boolean>(true);
  public healthStatus$ = this.healthStatusSubject.asObservable();

  constructor(private http: HttpClient) {
    // Check health every 5 minutes
    interval(5 * 60 * 1000).pipe(
      switchMap(() => this.checkHealth()),
      catchError(() => {
        this.healthStatusSubject.next(false);
        return of(false);
      })
    ).subscribe();
  }

  checkHealth(): Observable<boolean> {
    return this.http.get<{status: string}>(`${this.apiUrl}/health`).pipe(
      switchMap(response => {
        const isHealthy = response.status === 'UP';
        this.healthStatusSubject.next(isHealthy);
        return of(isHealthy);
      }),
      catchError(() => {
        this.healthStatusSubject.next(false);
        return of(false);
      })
    );
  }

  getHealthStatus(): boolean {
    return this.healthStatusSubject.value;
  }
}

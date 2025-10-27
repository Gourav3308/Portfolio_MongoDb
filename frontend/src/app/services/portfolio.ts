import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError, timeout } from 'rxjs';
import { environment } from '../../environments/environment';
import { Certification } from '../models/certification.model';
import { Education } from '../models/education.model';
import { Experience } from '../models/experience.model';
import { PersonalInfo } from '../models/personal-info.model';
import { Project } from '../models/project.model';
import { Skill } from '../models/skill.model';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {
  // Use environment-specific API URL
  private apiUrl = environment.apiUrl;
  private maxRetries = 3;
  private retryDelay = 2000; // 2 seconds

  constructor(private http: HttpClient) { }

  // Generic error handler
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Client Error: ${error.error.message}`;
    } else {
      // Server-side error
      switch (error.status) {
        case 0:
          errorMessage = 'Backend server is not responding. Please try again in a few moments.';
          break;
        case 404:
          errorMessage = 'Resource not found';
          break;
        case 500:
          errorMessage = 'Internal server error';
          break;
        case 503:
          errorMessage = 'Service temporarily unavailable. The server may be starting up.';
          break;
        default:
          errorMessage = `Server Error: ${error.status} - ${error.message}`;
      }
    }
    
    console.error('API Error:', errorMessage);
    return throwError(() => new Error(errorMessage));
  }

  // Generic retry logic
  private retryRequest<T>(request: Observable<T>): Observable<T> {
    return request.pipe(
      timeout(15000), // 15 second timeout
      retry({
        count: this.maxRetries,
        delay: (error, retryCount) => {
          console.log(`Retry attempt ${retryCount} for API call`);
          return new Promise(resolve => setTimeout(resolve, this.retryDelay * retryCount));
        }
      }),
      catchError(this.handleError)
    );
  }

  // Personal Info methods
  getPersonalInfo(): Observable<PersonalInfo[]> {
    console.log('Making request to:', `${this.apiUrl}/personal-info`);
    return this.retryRequest(this.http.get<PersonalInfo[]>(`${this.apiUrl}/personal-info`));
  }

  getPersonalInfoById(id: string): Observable<PersonalInfo> {
    return this.http.get<PersonalInfo>(`${this.apiUrl}/personal-info/${id}`);
  }

  createPersonalInfo(personalInfo: PersonalInfo): Observable<PersonalInfo> {
    return this.http.post<PersonalInfo>(`${this.apiUrl}/personal-info`, personalInfo);
  }

  updatePersonalInfo(id: string, personalInfo: PersonalInfo): Observable<PersonalInfo> {
    return this.http.put<PersonalInfo>(`${this.apiUrl}/personal-info/${id}`, personalInfo);
  }

  deletePersonalInfo(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/personal-info/${id}`);
  }

  // Project methods
  getProjects(): Observable<Project[]> {
    console.log('Making request to:', `${this.apiUrl}/projects`);
    return this.retryRequest(this.http.get<Project[]>(`${this.apiUrl}/projects`));
  }

  getProjectById(id: string): Observable<Project> {
    return this.retryRequest(this.http.get<Project>(`${this.apiUrl}/projects/${id}`));
  }

  getProjectsByCategory(category: string): Observable<Project[]> {
    return this.retryRequest(this.http.get<Project[]>(`${this.apiUrl}/projects/category/${category}`));
  }

  getCurrentProjects(): Observable<Project[]> {
    return this.retryRequest(this.http.get<Project[]>(`${this.apiUrl}/projects/current`));
  }

  createProject(project: Project): Observable<Project> {
    return this.http.post<Project>(`${this.apiUrl}/projects`, project);
  }

  updateProject(id: string, project: Project): Observable<Project> {
    return this.http.put<Project>(`${this.apiUrl}/projects/${id}`, project);
  }

  deleteProject(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/projects/${id}`);
  }

  // Skill methods
  getSkills(): Observable<Skill[]> {
    console.log('Making request to:', `${this.apiUrl}/skills`);
    return this.retryRequest(this.http.get<Skill[]>(`${this.apiUrl}/skills`));
  }

  getSkillById(id: string): Observable<Skill> {
    return this.retryRequest(this.http.get<Skill>(`${this.apiUrl}/skills/${id}`));
  }

  getSkillsByCategory(category: string): Observable<Skill[]> {
    return this.retryRequest(this.http.get<Skill[]>(`${this.apiUrl}/skills/category/${category}`));
  }

  getSkillsByMinProficiency(minProficiency: number): Observable<Skill[]> {
    return this.retryRequest(this.http.get<Skill[]>(`${this.apiUrl}/skills/proficiency/${minProficiency}`));
  }

  createSkill(skill: Skill): Observable<Skill> {
    return this.http.post<Skill>(`${this.apiUrl}/skills`, skill);
  }

  updateSkill(id: string, skill: Skill): Observable<Skill> {
    return this.http.put<Skill>(`${this.apiUrl}/skills/${id}`, skill);
  }

  deleteSkill(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/skills/${id}`);
  }

  // Education methods
  getEducation(): Observable<Education[]> {
    console.log('Making request to:', `${this.apiUrl}/education`);
    return this.retryRequest(this.http.get<Education[]>(`${this.apiUrl}/education`));
  }

  getEducationById(id: string): Observable<Education> {
    return this.retryRequest(this.http.get<Education>(`${this.apiUrl}/education/${id}`));
  }

  createEducation(education: Education): Observable<Education> {
    return this.http.post<Education>(`${this.apiUrl}/education`, education);
  }

  updateEducation(id: string, education: Education): Observable<Education> {
    return this.http.put<Education>(`${this.apiUrl}/education/${id}`, education);
  }

  deleteEducation(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/education/${id}`);
  }

  // Experience methods
  getExperience(): Observable<Experience[]> {
    console.log('Making request to:', `${this.apiUrl}/experience`);
    return this.retryRequest(this.http.get<Experience[]>(`${this.apiUrl}/experience`));
  }

  getExperienceById(id: string): Observable<Experience> {
    return this.retryRequest(this.http.get<Experience>(`${this.apiUrl}/experience/${id}`));
  }

  getCurrentExperience(): Observable<Experience[]> {
    return this.retryRequest(this.http.get<Experience[]>(`${this.apiUrl}/experience/current`));
  }

  getExperienceByType(type: string): Observable<Experience[]> {
    return this.retryRequest(this.http.get<Experience[]>(`${this.apiUrl}/experience/type/${type}`));
  }

  createExperience(experience: Experience): Observable<Experience> {
    return this.http.post<Experience>(`${this.apiUrl}/experience`, experience);
  }

  updateExperience(id: string, experience: Experience): Observable<Experience> {
    return this.http.put<Experience>(`${this.apiUrl}/experience/${id}`, experience);
  }

  deleteExperience(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/experience/${id}`);
  }

  // Certification methods
  getCertifications(): Observable<Certification[]> {
    console.log('Making request to:', `${this.apiUrl}/certifications`);
    return this.retryRequest(this.http.get<Certification[]>(`${this.apiUrl}/certifications`));
  }

  getCertificationById(id: string): Observable<Certification> {
    return this.retryRequest(this.http.get<Certification>(`${this.apiUrl}/certifications/${id}`));
  }

  getCertificationsByStatus(status: string): Observable<Certification[]> {
    return this.retryRequest(this.http.get<Certification[]>(`${this.apiUrl}/certifications/status/${status}`));
  }

  getCertificationsByOrganization(organization: string): Observable<Certification[]> {
    return this.retryRequest(this.http.get<Certification[]>(`${this.apiUrl}/certifications/organization/${organization}`));
  }

  createCertification(certification: Certification): Observable<Certification> {
    return this.http.post<Certification>(`${this.apiUrl}/certifications`, certification);
  }

  updateCertification(id: string, certification: Certification): Observable<Certification> {
    return this.http.put<Certification>(`${this.apiUrl}/certifications/${id}`, certification);
  }

  deleteCertification(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/certifications/${id}`);
  }
}

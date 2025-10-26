import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
  private apiUrl = environment.apiUrl || 'https://portfolio-back-v6uj.onrender.com/api';

  constructor(private http: HttpClient) { }

  // Personal Info methods
  getPersonalInfo(): Observable<PersonalInfo[]> {
    return this.http.get<PersonalInfo[]>(`${this.apiUrl}/personal-info`);
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
    return this.http.get<Project[]>(`${this.apiUrl}/projects`);
  }

  getProjectById(id: string): Observable<Project> {
    return this.http.get<Project>(`${this.apiUrl}/projects/${id}`);
  }

  getProjectsByCategory(category: string): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/projects/category/${category}`);
  }

  getCurrentProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/projects/current`);
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
    return this.http.get<Skill[]>(`${this.apiUrl}/skills`);
  }

  getSkillById(id: string): Observable<Skill> {
    return this.http.get<Skill>(`${this.apiUrl}/skills/${id}`);
  }

  getSkillsByCategory(category: string): Observable<Skill[]> {
    return this.http.get<Skill[]>(`${this.apiUrl}/skills/category/${category}`);
  }

  getSkillsByMinProficiency(minProficiency: number): Observable<Skill[]> {
    return this.http.get<Skill[]>(`${this.apiUrl}/skills/proficiency/${minProficiency}`);
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
    return this.http.get<Education[]>(`${this.apiUrl}/education`);
  }

  getEducationById(id: string): Observable<Education> {
    return this.http.get<Education>(`${this.apiUrl}/education/${id}`);
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
    return this.http.get<Experience[]>(`${this.apiUrl}/experience`);
  }

  getExperienceById(id: string): Observable<Experience> {
    return this.http.get<Experience>(`${this.apiUrl}/experience/${id}`);
  }

  getCurrentExperience(): Observable<Experience[]> {
    return this.http.get<Experience[]>(`${this.apiUrl}/experience/current`);
  }

  getExperienceByType(type: string): Observable<Experience[]> {
    return this.http.get<Experience[]>(`${this.apiUrl}/experience/type/${type}`);
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
    return this.http.get<Certification[]>(`${this.apiUrl}/certifications`);
  }

  getCertificationById(id: string): Observable<Certification> {
    return this.http.get<Certification>(`${this.apiUrl}/certifications/${id}`);
  }

  getCertificationsByStatus(status: string): Observable<Certification[]> {
    return this.http.get<Certification[]>(`${this.apiUrl}/certifications/status/${status}`);
  }

  getCertificationsByOrganization(organization: string): Observable<Certification[]> {
    return this.http.get<Certification[]>(`${this.apiUrl}/certifications/organization/${organization}`);
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

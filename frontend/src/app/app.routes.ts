import { Routes } from '@angular/router';
import { AboutComponent } from './components/about/about';
import { CertificationsComponent } from './components/certifications/certifications';
import { ContactComponent } from './components/contact/contact';
import { EducationComponent } from './components/education/education';
import { ExperienceComponent } from './components/experience/experience';
import { HomeComponent } from './components/home/home';
import { ProjectsComponent } from './components/projects/projects';
import { SkillsComponent } from './components/skills/skills';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'education', component: EducationComponent },
  { path: 'experience', component: ExperienceComponent },
  { path: 'projects', component: ProjectsComponent },
  { path: 'skills', component: SkillsComponent },
  { path: 'certifications', component: CertificationsComponent },
  { path: 'contact', component: ContactComponent },
  { path: '**', redirectTo: '/home' }
];

export interface Project {
  id?: string;
  name: string;
  description: string;
  technologies: string;
  githubUrl?: string;
  liveUrl?: string;
  imageUrl?: string;
  startDate?: string;
  endDate?: string;
  isCurrent: boolean;
  features?: string[];
  category: string;
}



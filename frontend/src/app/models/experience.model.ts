export interface Experience {
  id?: string;
  jobTitle: string;
  company: string;
  location: string;
  startDate: string;
  endDate: string;
  isCurrent: boolean;
  description?: string;
  responsibilities?: string[];
  technologies?: string[];
  type?: string;
}




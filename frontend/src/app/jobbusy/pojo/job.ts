import { JobCompany } from './job-company';
import { JobType } from './job-type';

export class Job {
    id: number;
    jobCompany: JobCompany;
    jobType: JobType;
    jobTitle: string;
    jobDescription: string;
    requirement: string;
    location: string;
    salary: number;
    postDate: Date;
    editMode = false;
}

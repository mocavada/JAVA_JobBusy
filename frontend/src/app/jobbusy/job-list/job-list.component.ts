import { JobbusyService } from './../service/jobbusy.service';
import { Job } from './../pojo/job';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css']
})
export class JobListComponent implements OnInit {

  jobList: Job[];

  constructor(private dService: JobbusyService) { }

  ngOnInit() {
    this.dService.jobResult$.subscribe(data => {
      if (data != null) {
        this.jobList = data;
        console.log('Loading Data');
      }
    });

    this.dService.getData();
    console.log('Get Data');

  }

}

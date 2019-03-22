import { JobListComponent } from './jobbusy/job-list/job-list.component';
import { UserLoginComponent } from './jobbusy/user-login/user-login.component';
import { JobPostComponent } from './jobbusy/job-post/job-post.component';
import { LoginGuardService } from './jobbusy/service/login-guard.service';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {path: 'PostJob' , component: JobPostComponent},
  {path: 'UserLoginComponent' , component: UserLoginComponent},
  {path: 'ListJob' , component: JobListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

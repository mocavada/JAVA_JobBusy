import { UserLoginComponent } from './jobbusy/user-login/user-login.component';
import { JobPostComponent } from './jobbusy/job-post/job-post.component';
import { JobListComponent } from './jobbusy/job-list/job-list.component';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ReactiveFormsModule} from '@angular/forms';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AutoSizeInputModule} from 'ngx-autosize-input';

@NgModule({
  declarations: [
    AppComponent,
    JobListComponent,
    JobPostComponent,
    UserLoginComponent
  ],
  schemas: [
    NO_ERRORS_SCHEMA
  ],
  // imported from other modules
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AutoSizeInputModule
  ],
  // define service for injector
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

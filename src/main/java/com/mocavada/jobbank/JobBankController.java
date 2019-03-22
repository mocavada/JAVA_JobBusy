package com.mocavada.jobbank;

import com.mocavada.jobbank.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
public class JobBankController {


    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JobBankService jobBankService;

    //======== GET JOB
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/jobs")
    @ResponseStatus(HttpStatus.OK)
    public List<Job> listAllAndSort() {
        return this.jobBankService.getJobList();
    }

    //======== POST/CREATE JOB - JAMES
//    @RequestMapping(
//            method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            value = "/job")
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody List<Job> createJob(@RequestBody Job job) {
//        Job newJob = this.jobbankService.createJob(job);
//        System.out.print(newJob);
//        return this.jobbankService.getJobList();
//    }
//

    //======== POST/CREATE JOB - MARC
    @RequestMapping(method = RequestMethod.POST, value = "/job")
    public ResponseEntity<Void> createJob(@RequestBody Job job, UriComponentsBuilder builder) {

        jobBankService.createJob(job);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/job").buildAndExpand(job.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    //======== DELETE JOB BY ID
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/job/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody
    void delJob(@PathVariable("id") Long id) {
        this.jobBankService.delete(id);
    }

    //======== PUT/UPDATE JOB
    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/job")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void editJob(@RequestBody Job job) {
        this.jobBankService.updateJobByID(job);
    }

    //======== GET JOB TYPE
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/jobtypes")
    @ResponseStatus(HttpStatus.OK)
    public List<JobType> getJobTypeList() {
        return this.jobBankService.getJobTypeList();
    }

    //======== CREATE JOB
    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/company")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody List<JobCompany> createCompany(@RequestBody JobCompany comp) {
        JobCompany jobCompanySaved = this.jobBankService.createCompany(comp);
        //Coomcomp = this.jobbankService.createCompany(comp);
        System.out.print(jobCompanySaved);
        return this.jobBankService.getCompanyList();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/companies")
    @ResponseStatus(HttpStatus.OK)
    public List<JobCompany> getCompanyList() {
        return this.jobBankService.getCompanyList();
    }


    //======== login
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/login")
    public JobLoginStatus login(@RequestBody JobPostUser jobPostUser, HttpSession session) {

        System.out.println(session.getId() + ":" + jobPostUser.getUserName() + " :" + jobPostUser.getPassword());

        return jobBankService.login(jobPostUser, session);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/checklogin")
    public JobLoginStatus checkLogin(HttpSession session) {

        return this.jobBankService.checkLoginSessionStatus(session);
    }

    //======== session test
    @GetMapping("/checkCount")
    // it will handle all request for /welcome
    public java.util.Map<String, Integer> start(HttpSession session) {
        String sessionId = session.getId();
        System.out.println("\n " + sessionId + " \n");

        Integer integer = (Integer) session.getAttribute("hitCounter"); // it will read data from database tables
        if (integer == null) {
            integer = new Integer(0);
            integer++;
            session.setAttribute("hitCounter", integer);  // it will write data to tables
        } else {
            integer++;
            session.setAttribute("hitCounter", integer);  // it will write data to tables
        }
        java.util.Map<String, Integer> hitCounter = new HashMap<>();
        hitCounter.put("Hit Counter", integer);
        return hitCounter;
    }

}




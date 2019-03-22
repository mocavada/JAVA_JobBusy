package com.mocavada.jobbank;

import com.mocavada.jobbank.pojo.*;
import com.mocavada.jobbank.repo.JobCompanyRepository;
import com.mocavada.jobbank.repo.JobPostUserRepository;
import com.mocavada.jobbank.repo.JobRepository;
import com.mocavada.jobbank.repo.JobTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class JobBankService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobCompanyRepository jobCompanyRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Autowired
    private JobPostUserRepository jobPostUserRepository;

    public JobBankService(JobCompanyRepository jbCompanyRepository,
                          JobRepository jobRepository,
                          JobTypeRepository jobTypeRepository,
                          JobPostUserRepository jobPostUserRepository
    ) {
        this.jobCompanyRepository = jbCompanyRepository;
        this.jobRepository = jobRepository;
        this.jobTypeRepository = jobTypeRepository;
        this.jobPostUserRepository = jobPostUserRepository;


        //initialize some dummy data
        if (this.jobTypeRepository != null && !this.jobTypeRepository.findAll().iterator().hasNext()) {
            this.jobTypeRepository.saveAll(new ArrayList<JobType>() {
                {
                    add(new JobType(1, "Full-time"));
                    add(new JobType(2, "Contractor"));
                    add(new JobType(3, "Part-time"));
                    add(new JobType(4, "Seasonal"));
                    add(new JobType(5, "Temporary"));
                }
            });
        }

        if (this.jobPostUserRepository != null && !this.jobPostUserRepository.findAll().iterator().hasNext()) {
            this.jobPostUserRepository.saveAll(new ArrayList<JobPostUser>() {
                {
                    add(new JobPostUser("james", "busyqa"));
                    add(new JobPostUser("bill", "busyqa2"));
                }
            });
        }
    }

    //create
    public Job createJob(Job job) {
        JobCompany jobCompany = job.getJobCompany();
        JobType jobType = job.getJobType();

        if (jobCompany != null && jobCompany.getId() == 0) {
            JobCompany findFromDB = jobCompanyRepository.findFirst1ByCompanyName(jobCompany.getCompanyName());
            if (findFromDB == null) {
                findFromDB = jobCompanyRepository.save(jobCompany);
            }

            job.setJobCompany(jobCompanyRepository.save(findFromDB));
        }
        if (jobType != null && jobType.getId() == 0) {
            JobType findFromDB = jobTypeRepository.findFirst1ByJobTypeName(jobType.getJobTypeName());
            if (findFromDB == null) {
                findFromDB = jobTypeRepository.save(jobType);
            }
            job.setJobType(findFromDB);
        }
//        System.out.println(job.getJobCompany().getId() + ":" + job.getJobType().getId());

        return this.jobRepository.save(job);
//        return new Job(null, null,
//                "Software Engineerwekjflwjef", "Coding", "College degree", "Toronto", 55000l);
    }

    //Read
    public List<Job> getJobList() {
        List<Job> list = new ArrayList<>();
        jobRepository.findAll(new Sort(Sort.Direction.DESC, "postDate")).forEach(list::add);

//        Iterable<Job> jobs = jobRepository.findAll(new Sort(Sort.Direction.DESC, "postDate"));
//        for (Job job : jobs) {
//            list.add(job);
//        }
        return list;
    }

    //delete
    public void delete(Long cityId) {
        this.jobRepository.deleteById(cityId);
    }


    public List<JobCompany> getCompanyList() {
        List<JobCompany> list = new ArrayList<>();
        jobCompanyRepository.findAll().forEach(list::add);

        return list;
    }

    public List<JobType> getJobTypeList() {
        List<JobType> list = new ArrayList<>();
        jobTypeRepository.findAll().forEach(list::add);

        return list;
    }


    public Job updateJobByTittle(Job job) {
        Job jobFromDB = this.jobRepository.findFirst1ByJobTitle(job.getJobTitle());
        job.setId(jobFromDB.getId());
        return createJob(job);
    }

    public Job updateJobByID(Job job) {

        Job jobFromDB = this.jobRepository.findById(job.getId()).get();
        job.setId(jobFromDB.getId());
        return createJob(job);
    }

//    public Job updateJob(Job job) {
//        JobCompany jobCompany = job.getJobCompany();
//        JobType jobType = job.getJobType();
//
//        if (jobCompany != null && jobCompany.getId() == 0) {
//            JobCompany findFromDB = jbCompanyRepository.findFirst1ByCompanyName(jobCompany.getCompanyName());
//            if (findFromDB == null) {
//                findFromDB = jbCompanyRepository.save(jobCompany);
//            }
//
//            job.setJobCompany(jbCompanyRepository.save(findFromDB));
//        }
//        if (jobType != null && jobType.getId() == 0) {
//            JobType findFromDB = jobTypeRepository.findFirst1ByJobTypeName(jobType.getJobTypeName());
//            if (findFromDB == null) {
//                findFromDB = jobTypeRepository.save(jobType);
//            }
//            job.setJobType(findFromDB);
//        }
////        System.out.println(job.getJobCompany().getId() + ":" + job.getJobType().getId());
//
//
//
//        return this.jobRepository.save(job);
//    }

    // ========

    //create
    public JobCompany createCompany(JobCompany jobCompany) {

//        this.jobCompanyRepository.findFirst1ByCompanyName(jobCompany.getCompanyName());
        JobCompany jobCompanySaved = this.jobCompanyRepository.save(jobCompany);
        return jobCompanySaved;
    }

    // ========
    public JobLoginStatus login(JobPostUser jobPostUser, HttpSession session) {
        JobLoginStatus jobLoginStatus = new JobLoginStatus();

        if (session.getAttribute("id") != null &&
                session.getAttribute("login") != null &&
                (boolean) session.getAttribute("login")) {
            jobLoginStatus.setLogin(true);
            System.out.println("LOGIN already");
        } else {
            System.out.println("not Login yet");

            if (jobPostUserRepository.findFirst1ByUserNameAndPassword(jobPostUser.getUserName(), jobPostUser.getPassword()) != null) {
                session.setAttribute("id", session.getId());
                session.setAttribute("login", true);
                jobLoginStatus.setLogin(true);
                System.out.println(" LOGIN");
            } else {
                session.setAttribute("login", false);
                jobLoginStatus.setLogin(false);
                System.out.println(" LOGIN FAIL: username & password not match");
            }

        }

        return jobLoginStatus;
    }

    public JobLoginStatus checkLoginSessionStatus(HttpSession session) {
        JobLoginStatus jobLoginStatus = new JobLoginStatus();

        if (session.getAttribute("id") != null && session.getAttribute("login") != null && (boolean) session.getAttribute("login")) {
            jobLoginStatus.setLogin(true);
        } else {
            jobLoginStatus.setLogin(false);
        }
        return jobLoginStatus;

    }
}

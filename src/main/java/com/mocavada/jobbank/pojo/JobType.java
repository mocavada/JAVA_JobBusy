package com.mocavada.jobbank.pojo;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "JobType")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class JobType {
    public JobType() {
    }

    public JobType(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }
    public JobType(long id, String jobTypeName) {
        this.id = id;
        this.jobTypeName = jobTypeName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "jobTypeName")
//    @Column(name = "jobTypeName", unique = true)
    private String jobTypeName;

    @OneToMany//(mappedBy = "jobType", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Job> jobs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}

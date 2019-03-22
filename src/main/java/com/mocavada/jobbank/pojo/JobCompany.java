package com.mocavada.jobbank.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "JobCompany")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class JobCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
// @Column(name = "id", unique = true)
    private long id;

    //    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "companyName")
//    @Column(name = "companyName", unique = true)
    private String companyName;

    private String contactor;

    private String email;

    private String phonenumber;

    private int size;

    @OneToMany(cascade = CascadeType.ALL)//(mappedBy = "jobCompany", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Job> jobs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public JobCompany(String companyName) {
        this.companyName = companyName;
    }

    public JobCompany() {
    }
}

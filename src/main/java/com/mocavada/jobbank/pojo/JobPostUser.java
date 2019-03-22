package com.mocavada.jobbank.pojo;

import javax.persistence.*;

@Entity
@Table(name = "JobPostUser")
public class JobPostUser {
//    public static final String ROLE_ADMIN = "ADM";
//    public static final String ROLE_USER = "USR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String password;
    private String role;

    public JobPostUser() {
    }

    public JobPostUser(String userName, String userPwd) {
        this.userName = userName;
        this.password = userPwd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

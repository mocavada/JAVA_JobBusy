package com.mocavada.jobbank.repo;

import com.mocavada.jobbank.pojo.JobCompany;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCompanyRepository extends PagingAndSortingRepository<JobCompany, Long> {
    JobCompany findFirst1ByCompanyName(String companyName);

    JobCompany findFirst1ById(String companyName);
}


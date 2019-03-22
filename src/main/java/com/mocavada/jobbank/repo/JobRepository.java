package com.mocavada.jobbank.repo;

import com.mocavada.jobbank.pojo.Job;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepository extends PagingAndSortingRepository<Job, Long> {
    Job findFirst1ByJobTitle(String jobTitle);

}


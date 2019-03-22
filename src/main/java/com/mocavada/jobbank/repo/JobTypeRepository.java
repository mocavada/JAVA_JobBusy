package com.mocavada.jobbank.repo;

import com.mocavada.jobbank.pojo.JobType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepository extends PagingAndSortingRepository<JobType, Long> {

    JobType findFirst1ByJobTypeName(String jobTypeName);


}


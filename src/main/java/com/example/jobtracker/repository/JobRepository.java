package com.example.jobtracker.repository;

import com.example.jobtracker.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}


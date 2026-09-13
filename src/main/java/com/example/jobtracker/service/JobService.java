package com.example.jobtracker.service;

import com.example.jobtracker.model.Job;
import com.example.jobtracker.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }
    public Job updateJob(Long id, Job job) {
        Job existingJob = jobRepository.findById(id).orElseThrow();

        existingJob.setCompany(job.getCompany());
        existingJob.setRole(job.getRole());
        existingJob.setCity(job.getCity());

        return jobRepository.save(existingJob);
    }
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}

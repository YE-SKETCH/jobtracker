package com.example.jobtracker.service;

import com.example.jobtracker.dto.JobRequest;
import com.example.jobtracker.dto.JobResponse;
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
    public JobResponse createJob(JobRequest request) {
        Job job = new Job();
        job.setCompany(request.getCompany());
        job.setRole(request.getRole());
        job.setCity(request.getCity());
        Job savedJob = jobRepository.save(job);

        JobResponse response = new JobResponse();

        response.setId(savedJob.getId());
        response.setCompany(savedJob.getCompany());
        response.setRole(savedJob.getRole());
        response.setCity(savedJob.getCity());

        return response;
    }
    public List<JobResponse> getAllJobs() {

        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(job -> {
                    JobResponse response = new JobResponse();

                    response.setId(job.getId());
                    response.setCompany(job.getCompany());
                    response.setRole(job.getRole());
                    response.setCity(job.getCity());

                    return response;
                })
                .toList();
    }
    public JobResponse getJobById(Long id) {

        Job job = jobRepository.findById(id).orElseThrow();

        JobResponse response = new JobResponse();

        response.setId(job.getId());
        response.setCompany(job.getCompany());
        response.setRole(job.getRole());
        response.setCity(job.getCity());

        return response;
    }
    public JobResponse updateJob(Long id, JobRequest request) {

        Job existingJob = jobRepository.findById(id).orElseThrow();

        existingJob.setCompany(request.getCompany());
        existingJob.setRole(request.getRole());
        existingJob.setCity(request.getCity());

        Job savedJob = jobRepository.save(existingJob);

        JobResponse response = new JobResponse();

        response.setId(savedJob.getId());
        response.setCompany(savedJob.getCompany());
        response.setRole(savedJob.getRole());
        response.setCity(savedJob.getCity());

        return response;
    }
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}

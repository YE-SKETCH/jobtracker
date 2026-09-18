package com.example.jobtracker.controller;

import com.example.jobtracker.dto.JobRequest;
import com.example.jobtracker.dto.JobResponse;
import com.example.jobtracker.model.Job;
import com.example.jobtracker.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class JobController {
    private final JobService jobService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @PostMapping("/jobs")
public JobResponse createJob(@RequestBody JobRequest request) {
    return jobService.createJob(request);
}
    @GetMapping("/jobs")
    public List<JobResponse> getAllJobs() {
        return jobService.getAllJobs();
    }
    @GetMapping("/jobs/{id}")
    public JobResponse getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }
    @PutMapping("/jobs/{id}")
    public JobResponse updateJob(
            @PathVariable Long id,
            @RequestBody JobRequest request) {
        return jobService.updateJob(id, request);
    }
    @DeleteMapping("/jobs/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}

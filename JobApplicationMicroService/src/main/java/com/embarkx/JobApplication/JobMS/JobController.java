package com.embarkx.JobApplication.JobMS;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.embarkx.JobApplication.DTO.JobDTO;

@RestController
public class JobController {

	private JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping("/jobs")
	public ResponseEntity<?> findAll() {
		try {
			List<JobDTO> jobWithCompanyDTOs = jobService.findAll();
			return new ResponseEntity<>(jobWithCompanyDTOs, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());

		}
	}

	@PostMapping("/jobs")
	public String createJob(@RequestBody Job job) {
		jobService.createJob(job);
//		Company c = job.getCompany();
		return "Job added successfully";
	}

	@GetMapping("/jobs/{id}")
	public ResponseEntity<?> getJob(@PathVariable Long id) {
		try {
			ResponseEntity<JobDTO> job = jobService.getJob(id);
			return job;
		} catch (ResponseStatusException e) {
			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
		}
	}

	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id) {

		try {
			ResponseEntity<String> job = jobService.deleteJob(id);
			return job;
		} catch (ResponseStatusException e) {
			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
		}
	}

	@PutMapping("/jobs/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {

		try {
			ResponseEntity<String> result = jobService.updateJob(id, job);
			return result;
		} catch (ResponseStatusException e) {
			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
		}
	}

}

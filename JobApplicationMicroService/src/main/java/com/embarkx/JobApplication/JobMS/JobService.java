package com.embarkx.JobApplication.JobMS;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.embarkx.JobApplication.DTO.JobDTO;

public interface JobService {

	List<JobDTO> findAll();

	void createJob(Job job);

	ResponseEntity<JobDTO> getJob(Long Id);

	ResponseEntity<String> deleteJob(Long Id);

	ResponseEntity<String> updateJob(Long Id, Job job);

}

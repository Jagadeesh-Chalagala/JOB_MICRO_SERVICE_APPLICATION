package com.embarkx.JobApplication.JobMS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.embarkx.JobApplication.Clients.CompanyClient;
import com.embarkx.JobApplication.Clients.ReviewClient;
import com.embarkx.JobApplication.DTO.JobDTO;
import com.embarkx.JobApplication.Mapper.JobWithCompanyDTOMapper;
import com.embarkx.JobApplication.external.Company;
import com.embarkx.JobApplication.external.Review;

@Service
public class JobSearchImpl implements JobService {

	private JobRepository jobRepository;

	@Autowired
	private RestTemplate restTemplate;

	private CompanyClient companyClient;
	private ReviewClient reviewClient;

	public JobSearchImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
		super();
		this.jobRepository = jobRepository;
		this.companyClient = companyClient;
		this.reviewClient = reviewClient;
	}

	@Override
	public List<JobDTO> findAll() {
		// TODO Auto-generated method stub
		List<Job> jobs = jobRepository.findAll();
		List<JobDTO> jobDTOs = jobs.stream().map(this::convertToDTO).collect(Collectors.toList());

		return jobDTOs;

	}

	private JobDTO convertToDTO(Job job) {

		try {

//			Company company = restTemplate
//					.getForObject("http://COMPANYAPPLICATION:8081/companies/" + job.getCompanyId(), Company.class);
//
//			ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//					"http://REVIEWAPPLICATION:8083/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET, null,
//					new ParameterizedTypeReference<List<Review>>() {
//					});
//			List<Review> reviews = reviewResponse.getBody();

			Company company = companyClient.getCompany(job.getCompanyId());
			List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

			JobDTO jobDTO = JobWithCompanyDTOMapper.jobWithCompanyDTOMapper(job, company, reviews);

			return jobDTO;

		} catch (HttpClientErrorException e) {
			// TODO: handle exception
			throw new ResponseStatusException(e.getStatusCode(),
					e.getMessage() + " and company id is : " + job.getCompanyId());
		}

	}

	@Override
	public void createJob(Job job) {
		// TODO Auto-generated method stub
		jobRepository.save(job);
	}

	@Override
	public ResponseEntity<JobDTO> getJob(Long Id) {
		// TODO Auto-generated method stub

		Optional<Job> optJob = jobRepository.findById(Id);

		if (optJob.isPresent()) {
			return new ResponseEntity<JobDTO>(convertToDTO(optJob.get()), HttpStatus.FOUND);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
	}

	@Override
	public ResponseEntity<String> deleteJob(Long Id) {
		// TODO Auto-generated method stub
		try {
			jobRepository.deleteById(Id);
			return new ResponseEntity<String>("Job deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
		}
	}

	@Override
	public ResponseEntity<String> updateJob(Long Id, Job job) {
		// TODO Auto-generated method stub

		Optional<Job> optJob = jobRepository.findById(Id);

		if (optJob.isPresent()) {
			Job oldJob = optJob.get();

			oldJob.setDescription(job.getDescription());
			oldJob.setLocation(job.getLocation());
			oldJob.setMinSalary(job.getMinSalary());
			oldJob.setMaxSalary(job.getMaxSalary());
			oldJob.setTitle(job.getTitle());

			jobRepository.save(oldJob);

			return new ResponseEntity<String>("Job updated successfully", HttpStatus.OK);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
	}

}

package com.embarkx.JobApplication.Mapper;

import java.util.List;

import com.embarkx.JobApplication.DTO.JobDTO;
import com.embarkx.JobApplication.JobMS.Job;
import com.embarkx.JobApplication.external.Company;
import com.embarkx.JobApplication.external.Review;

public class JobWithCompanyDTOMapper {
	public static JobDTO jobWithCompanyDTOMapper(Job job, Company company, List<Review> reviews) {
		JobDTO JobDTO = new JobDTO();

		JobDTO.setId(job.getId());
		JobDTO.setTitle(job.getTitle());
		JobDTO.setDescription(job.getDescription());
		JobDTO.setMaxSalary(job.getMaxSalary());
		JobDTO.setMinSalary(job.getMinSalary());
		JobDTO.setLocation(job.getLocation());

		JobDTO.setCompany(company);
		JobDTO.setReviews(reviews);

		return JobDTO;
	}
}

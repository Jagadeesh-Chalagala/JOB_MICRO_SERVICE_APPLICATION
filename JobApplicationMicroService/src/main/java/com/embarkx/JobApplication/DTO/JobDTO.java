package com.embarkx.JobApplication.DTO;

import java.util.List;

import com.embarkx.JobApplication.external.Company;
import com.embarkx.JobApplication.external.Review;

import lombok.Data;

@Data
public class JobDTO {
	private Long id;
	private String title;
	private String description;
	private String minSalary;
	private String maxSalary;
	private String location;
	private Company company;
	private List<Review> reviews;
}

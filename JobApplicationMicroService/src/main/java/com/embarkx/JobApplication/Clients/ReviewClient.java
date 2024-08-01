package com.embarkx.JobApplication.Clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.embarkx.JobApplication.external.Review;

@FeignClient(name = "REVIEWAPPLICATION")
public interface ReviewClient {

	@GetMapping("/reviews")
	List<Review> getReviews(@RequestParam("companyId") Long Id);
}

package com.embarkx.CompanyApplication.CompanyMS.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEWAPPLICATION", url= "${reviewapplication.url}")
public interface ReviewClient {
    
    @GetMapping("/reviews/averageRating")
    Double getAverageRatingForCompany(@RequestParam Long companyId);
}

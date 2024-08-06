package com.embarkx.JobApplication.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.embarkx.JobApplication.external.Company;

@FeignClient(name = "COMPANYAPPLICATION" , url="${companyapplication.url}")
public interface CompanyClient {

	@GetMapping("/companies/{Id}")
	Company getCompany(@PathVariable Long Id);
}

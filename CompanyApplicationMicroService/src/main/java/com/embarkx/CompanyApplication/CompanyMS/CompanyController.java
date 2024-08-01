package com.embarkx.CompanyApplication.CompanyMS;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies() {

		return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> createCompany(@RequestBody Company company) {
		try {
			companyService.createCompany(company);
			return new ResponseEntity<String>("company created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
		try {
			companyService.deleteCompany(id);
			return new ResponseEntity<String>("company deleted successfully", HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCompany(@PathVariable Long id) {
		try {
			Company company = companyService.getCompany(id);
			return new ResponseEntity<Company>(company, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody Company company) {
		try {
			Company newCompany = companyService.updateCompany(id, company);
			return new ResponseEntity<Company>(newCompany, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
		}

	}

}

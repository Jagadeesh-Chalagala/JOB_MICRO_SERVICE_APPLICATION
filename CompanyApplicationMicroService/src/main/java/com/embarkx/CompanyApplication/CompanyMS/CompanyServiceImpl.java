package com.embarkx.CompanyApplication.CompanyMS;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.embarkx.CompanyApplication.CompanyMS.clients.ReviewClient;
import com.embarkx.CompanyApplication.CompanyMS.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;

	private ReviewClient reviewClient;

	@Override
	public List<Company> getAllCompanies() {

		return companyRepository.findAll();
	}

	@Override
	public Company getCompany(Long Id) {

		Optional<Company> company = companyRepository.findById(Id);
		if (company.isPresent())
			return company.get();
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
	}
	// @Override
	// public Company getCompany(Long Id) {
	//
	// if(companyRepository.existsById(Id))
	// return companyRepository.findById(Id);
	// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
	// }

	@Override
	public Company updateCompany(Long Id, Company company) {

		Optional<Company> optOldCompany = companyRepository.findById(Id);

		if (optOldCompany.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");

		Company oldCompany = optOldCompany.get();
		oldCompany.setName(company.getName());
		oldCompany.setDescription(company.getDescription());
		companyRepository.save(oldCompany);
		return oldCompany;

	}

	@Override
	public void createCompany(Company company) {

		companyRepository.save(company);
	}

	// @Override
	// public void deleteCompany(Long Id) {
	//
	// Company company = companyRepository.findById(Id)
	// .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "company
	// not found"));
	// companyRepository.delete(company);
	// }
	@Override
	public void deleteCompany(Long Id) {

		if (companyRepository.existsById(Id))
			companyRepository.deleteById(Id);
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
		}
	}

	@Override
	public void updateCompanyRating(ReviewMessage reviewMessage) {
		System.out.println(reviewMessage.getDescription());
		Company company = companyRepository.findById(reviewMessage.getCompanyId())
				.orElseThrow(() -> new NotFoundException("Company not found" + reviewMessage.getCompanyId()));

		double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
		company.setRating(averageRating);
		companyRepository.save(company);
	}

}

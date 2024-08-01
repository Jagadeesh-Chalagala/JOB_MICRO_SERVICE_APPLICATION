package com.embarkx.CompanyApplication.CompanyMS;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();
	}

	@Override
	public Company getCompany(Long Id) {
		// TODO Auto-generated method stub
		Optional<Company> company = companyRepository.findById(Id);
		if (company.isPresent())
			return company.get();
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
	}
//	@Override
//	public Company getCompany(Long Id) {
//		// TODO Auto-generated method stub
//		if(companyRepository.existsById(Id))
//			return companyRepository.findById(Id);
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
//	}

	@Override
	public Company updateCompany(Long Id, Company company) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		companyRepository.save(company);
	}

//	@Override
//	public void deleteCompany(Long Id) {
//		// TODO Auto-generated method stub
//		Company company = companyRepository.findById(Id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "company not found"));
//		companyRepository.delete(company);
//	}
	@Override
	public void deleteCompany(Long Id) {
		// TODO Auto-generated method stub
		if (companyRepository.existsById(Id))
			companyRepository.deleteById(Id);
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
		}
	}

}

package com.embarkx.CompanyApplication.CompanyMS;

import java.util.List;

public interface CompanyService {
	List<Company> getAllCompanies();

	Company getCompany(Long Id);

	void createCompany(Company company);

	void deleteCompany(Long Id);

	Company updateCompany(Long Id, Company company);
}

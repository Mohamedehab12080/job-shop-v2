package com.example.JOBSHOP.JOBSHOP.companyAdministrator.service;

import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;

public interface companyAdministratorServiceInterface {

	 companyAdministrator insert(companyAdministrator company);
	 Optional<companyAdministrator> findByEmail(String email);
	companyAdministrator update(Long id, companyAdministrator req);
	companyAdministrator findById(Long id);
	
}

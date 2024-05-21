package com.example.JOBSHOP.JOBSHOP.companyAdministrator.service;

import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.Registration.controllers.registerUserRequest;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;

public interface companyAdministratorServiceInterface {

	 companyAdministrator insert(companyAdministrator company);
	 Optional<companyAdministrator> findByEmail(String email);
	companyAdministratorDTO update(Long id, registerUserRequest req);
	companyAdministrator findById(Long id);	
	companyProfile findcompanyProfileIdByCompanyName(String companyName)throws UserException;
}

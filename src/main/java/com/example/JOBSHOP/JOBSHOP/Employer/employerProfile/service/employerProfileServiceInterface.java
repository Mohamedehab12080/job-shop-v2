package com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.service;

import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.employerProfile;

public interface employerProfileServiceInterface {

	employerProfile findById(Long id);

	employerProfile findByEmployer(Long id);

	void insert(employerProfile employerProfile);

}

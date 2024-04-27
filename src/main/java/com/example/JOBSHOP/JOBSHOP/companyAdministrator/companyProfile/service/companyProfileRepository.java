package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
@Repository
public interface companyProfileRepository extends /*baseRepo<companyProfile, Long>*/ JpaRepository<companyProfile, Long> {

	Optional<companyProfile> findByCompanyAdministratorId(Long id);
}

package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.models.Employer;

@Repository
public interface employerRepository extends JpaRepository<Employer,Long>{

	List<Employer> findByCompanyAdministratorId(Long CompanyAdministratorId);
}

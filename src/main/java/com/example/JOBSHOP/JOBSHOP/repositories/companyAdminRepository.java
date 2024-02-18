package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;

@Repository
public interface companyAdminRepository extends JpaRepository<companyAdministrator,Long>{

	Optional<companyAdministrator> findById(Long id);
}

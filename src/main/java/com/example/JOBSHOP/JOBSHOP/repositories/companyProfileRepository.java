package com.example.JOBSHOP.JOBSHOP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.models.companyProfile;
@Repository
public interface companyProfileRepository extends /*baseRepo<companyProfile, Long>*/ JpaRepository<companyProfile, Long> {

}

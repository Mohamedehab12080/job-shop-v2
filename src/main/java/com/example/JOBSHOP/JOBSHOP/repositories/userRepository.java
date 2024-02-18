package com.example.JOBSHOP.JOBSHOP.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.models.User;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;

@Repository
public interface userRepository extends JpaRepository<User, Long>{

	
}

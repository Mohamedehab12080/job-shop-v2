package com.example.JOBSHOP.JOBSHOP.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.models.User;
@Repository
public interface userRepository extends /*baseRepo<User, Long>*/ JpaRepository<User,Long>{

	
}

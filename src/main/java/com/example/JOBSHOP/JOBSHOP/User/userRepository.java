package com.example.JOBSHOP.JOBSHOP.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
@Repository
public interface userRepository extends /*baseRepo<User, Long>*/ JpaRepository<User,Long>{

	
}

package com.example.JOBSHOP.JOBSHOP.User.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
@Repository
public interface userRepository extends /*baseRepo<User, Long>*/ JpaRepository<User,Long>{

	Optional<User> findByEmail(String email); 

	@Query("SELECT u FROM User u WHERE lower(u.userName) LIKE lower(concat('%',:query, '%'))")
    List<User> searchUser(@Param("query") String query);
//    @Query("SELECT u FROM User u WHERE lower(u.userName) LIKE lower(concat('%', :query, '%'))")
//    List<User> searchUser(@Param("query") String query);
	
}

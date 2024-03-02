package com.example.JOBSHOP.JOBSHOP.Post;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;

@Repository
public interface postRepository extends /*baseRepo<Post,Long>*/ JpaRepository<Post,Long>,JpaSpecificationExecutor<Post>{

//	@EntityGraph(attributePaths = {"employer"})
	List<Post> findByEmployerId(Long id); 
	
	List<Post> findByCompanyProfileId(Long id);
//	@Query("SELECT DISTINCT p FROM Post p " +
//	           "JOIN Application a ON p.id = a.post.id " +
//	           "JOIN a.jobSeeker j " +
//	           "WHERE j.skills IN :skills " +
//	           "AND a.skills IN :skills")
//	    List<Post> recommendForEmployerTheBestApplications(List<String> skills);
//	                                         // IT Cs AI     IT          
	@Query(value="select p from Post p where p.Title like %:Title%")
	Post findByTitles(@Param("Title") String title);
	
	@Query(value="select distinct p from Post p where p.Title like %:Title%")
	Set<Post> findByTitle(@Param("Title") String title);
}

package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.models.Post;

@Repository
public interface postRepository extends /*baseRepo<Post,Long>*/ JpaRepository<Post,Long>{

//	@EntityGraph(attributePaths = {"employer"})
	List<Post> findByEmployerId(Long id); 
	
	List<Post> findByCompanyProfileId(Long id);
	
	@Query(value="select p from Post p where p.Title like %:Title%")
	Post findByTitles(@Param("Title") String title);
	
	@Query(value="select p from Post p where p.Title like %:Title%")
	List<Post> findByTitle(@Param("Title") String title);
}

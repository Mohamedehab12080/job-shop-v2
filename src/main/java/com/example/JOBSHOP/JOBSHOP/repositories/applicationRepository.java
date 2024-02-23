package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.models.Application;
@Repository
public interface applicationRepository extends /*baseRepo<Application, Long>*/ JpaRepository<Application,Long>{

//	@Query("SELECT a FROM Application a " +
//				"JOIN Post p ON p.id=a.post.id "+
//	           "WHERE a.Post.id = :postId and a.skills IN :postSkills " +
//	           "ORDER BY SIZE(a.Skills) - SIZE((SELECT p.additionalSkills FROM Post p WHERE p.id = :postId)) ASC")
//	    List<Application> findBestMatchingApplicationsForPost(Long postId,@Param("postSkills") List<String> postSkills);
//	
	List<Application> findByJobSeekerIdOrderByCreatedDateDesc(Long id);
	
	@Query("select a from Application a where a.Post.id=:id")
	List<Application> findByPostId(@Param("id") Long id);
	
}

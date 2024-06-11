package com.example.JOBSHOP.JOBSHOP.Post.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.Post.Post;

@Repository
public interface postRepository extends /*baseRepo<Post,Long>*/ JpaRepository<Post,Long>,JpaSpecificationExecutor<Post>{

//	@EntityGraph(attributePaths = {"employer"})
	List<Post> findByEmployerId(Long id); 
	
	 @Query("SELECT p FROM Post p " +
	           "WHERE (:title IS NULL OR p.Title LIKE %:title%) " +
	           "AND (:location IS NULL OR :location = '' OR p.location LIKE %:location%) " +
	           "AND (:employmentType IS NULL OR :employmentType = '' OR p.employmentType LIKE %:employmentType%) " +
	           "AND (:year IS NULL OR FUNCTION('YEAR', p.createdDate) = :year) " +
	           "AND (:month IS NULL OR FUNCTION('MONTH', p.createdDate) = :month) " +
	           "AND (:postIds IS NULL OR p.id IN :postIds)")
	    List<Post> findPosts(@Param("title") String title,
	                         @Param("location") String location,
	                         @Param("employmentType") String employmentType,
	                         @Param("year") Integer year,
	                         @Param("month") Integer month,
	                         @Param("postIds") List<Long> postIds);
	 
//	@Query("SELECT p FROM Post p JOIN FETCH p.companyProfile WHERE p.id = :id")
	@Query("select p from Post p where p.companyProfile.id=:id Order by createdDate Desc")
	List<Post> findByCompanyProfileId(Long id);
//	@Query("SELECT DISTINCT p FROM Post p " +
//	           "JOIN Application a ON p.id = a.post.id " +
//	           "JOIN a.jobSeeker j " +
//	           "WHERE j.skills IN :skills " +s
//	           "AND a.skills IN :skills")
//	    List<Post> recommendForEmployerTheBestApplications(List<String> skills);
//	                                         // IT Cs AI     IT          
	@Query(value="select p from Post p where p.Title like %:Title%")
	Post findByTitles(@Param("Title") String title);
	
	@Query(value="select distinct p from Post p where p.Title like %:Title%")
	Set<Post> findByTitle(@Param("Title") String title);

    List<Post> findByPostFieldsFieldFieldName(String fieldName);

//	@Query("SELECT p from Post p where p.postField.id=:postFieldId")
//	List<Post> findByPostFieldId(@Param("postFieldId") Long postFieldId);
}

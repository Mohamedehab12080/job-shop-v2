package com.example.JOBSHOP.JOBSHOP.Post.Specifications;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class postSpecification implements Specification<Post>{

	
	postSearch postSearch;	
	searchHelper searchHelper;
	public postSpecification(postSearch postSearch)
	{
		this.postSearch=postSearch;
		this.searchHelper=new searchHelper();
	}
	List<Predicate> predicatList=new ArrayList<Predicate>();
	
	@Override
	public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		//Filter by Title
		if(postSearch.getTitle()!=null &&!postSearch.getTitle().isEmpty())
		{
			predicatList.add(cb.like(root.get("Title"),"%"+postSearch.getTitle()+"%"));
		}
		
		//Filter by location
		if(postSearch.getLocation()!=null &&!postSearch.getLocation().isEmpty())
		{
			predicatList.add(cb.like(root.get("location"),"%"+postSearch.getLocation()+"%"));
		}
		
		//Filter by employmentType
		if(postSearch.getEmploymentType()!=null &&!postSearch.getEmploymentType().isEmpty())
		{
			predicatList.add(cb.like(root.get("employmentType"),"%"+postSearch.getEmploymentType()+"%"));
		}
		
		//Filter by companyName
		if(postSearch.getCreatedDate()!=null && !postSearch.getCreatedDate().isEmpty())
		{
			String pattern = "yyyy-MM-dd";
	        // Create a DateTimeFormatter using the defined pattern
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	        
	        LocalDateTime createdDate=LocalDateTime.parse(postSearch.getCreatedDate(),formatter);
	        // Extract year, month, and year-month combinations
	        Integer year = createdDate.getYear();
	        Integer month = createdDate.getMonthValue();
	        if (year != null) {
	        	
	        	predicatList.add(cb.equal(cb.function("YEAR", Integer.class, root.get("createdDate")), year));
	        }
	        
	        if (month != null) {
	        	
	        	predicatList.add(cb.equal(cb.function("MONTH", Integer.class, root.get("createdDate")), month));
	        }
		}
		
		//Filter by companyName
		if(postSearch.getCompanyName()!=null &&!postSearch.getCompanyName().isEmpty())
		{
			try {
				predicatList.add(cb.equal(root.get("companyProfile"),searchHelper.findCompanyProfile(postSearch.getCompanyName())));
			} catch (UserException e) {
				System.out.println("user Exception...");
			}
		}
		
//		//Filter by postFieldName
//		if(postSearch.getFieldName()!=null &&!postSearch.getFieldName().isEmpty())
//		{
//			predicatList.add(cb.equal(root.get("postField"),searchHelper.findPostFieldWithFieldName(postSearch.getFieldName())));
//		}
		
		return cb.and(predicatList.toArray(new Predicate[0]));
	}
	
	
}

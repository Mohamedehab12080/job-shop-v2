package com.example.JOBSHOP.JOBSHOP.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.services.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.specifications.searchModels.postSearch;

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
		
		//Filter by createdDate
		if(postSearch.getCreatedDate()!=null)
		{
			predicatList.add(cb.like(root.get("createdDate"),"%"+postSearch.getCreatedDate()+"%"));
		}
		
		//Filter by createdDate
		if(postSearch.getCompanyName()!=null &&!postSearch.getCompanyName().isEmpty())
		{
			predicatList.add(cb.equal(root.get("companyProfile"),searchHelper.findCompanyProfile(postSearch.getCompanyName())));
		}
		//Filter by createdDate
		if(postSearch.getFieldName()!=null &&!postSearch.getFieldName().isEmpty())
		{
			predicatList.add(cb.equal(root.get("postField"),searchHelper.findPostFieldWithFieldName(postSearch.getFieldName())));
		}
		
		return cb.and(predicatList.toArray(new Predicate[0]));
	}
	
	
}

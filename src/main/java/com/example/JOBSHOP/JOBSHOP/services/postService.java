package com.example.JOBSHOP.JOBSHOP.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.repositories.jobSeekerRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.postRepository;
import com.example.JOBSHOP.JOBSHOP.specifications.postSpecification;
import com.example.JOBSHOP.JOBSHOP.specifications.searchModels.postSearch;

@Service
public class postService{

	@Autowired
	private postRepository postRepository;
	
	@Autowired
	private jobSeekerService jobSeekerService;
	
	
	public List<Post> findPostsWithSearch(postSearch postSearch)
	{
		postSpecification postSpec=new postSpecification(postSearch);
		return postRepository.findAll(postSpec);
	}

    public Post getReferenceById(Long id)
	{
		return postRepository.getReferenceById(id);
	}
   
	public List<Post> findAll()
	{
		return postRepository.findAll();
	}
	
	public Post insert(Post t)
	{
		return postRepository.save(t);
	}
	
	public Post findById(Long id)
	{
		Optional<Post> finded=postRepository.findById(id);
		if(finded.isPresent())
		{
			return finded.get();
		}else 
		{
			return null;
		}
		
	}
	
//	public void updateEntityStatus(Application t)
//	{
//		postRepository.updateEntity(t.getId(),t.getStatusCode()); 
//	}
	
	public Post update(Post t)
	{
		if(getReferenceById(t.getId())!=null)
		{
//			logInfo("Employer Updated Successfully");
			return postRepository.save(t);
		}else 
		{
//			logError("EmployerNotFound");
			return null;
			
		}
	}
	public List<Post> insertAll(List<Post> entity)
	{
		return postRepository.saveAll(entity);
	}
	
	public void deleteById(Long id)
	{
		Post t=getReferenceById(id);
		if(t!=null)
		{
			postRepository.deleteById(id);
		}
	}
	
	public List<Post> findByEmployer(Long id)
	{ 
		return postRepository.findByEmployerId(id);
	}
	public List<Post> findByCompanyProfile(Long id)
	{ 
		return postRepository.findByCompanyProfileId(id);
	}
	

	
	public List<Post> findPostsWithTitle(String title)
	{
		return postRepository.findByTitle(title);
	}
	
	public List<Post> findByEmployerId(Long id)
	{
		return postRepository.findByEmployerId(id);
	}
	public Post findPostsWithTitleOne(String title)
	{
		Post post;
		try {
			post=postRepository.findByTitles(title); 
			return post;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Post> findPostsWithProfileSkills(Long id)
	{
		List<String> skills=jobSeekerService.findSkillsById(id);
		List<Post> ListPosts=new ArrayList<Post>();
		Post post=null;
		for(int i=0;i<skills.size();i++)
		{
			System.out.println("Skills : "+skills.get(i));
			post=findPostsWithTitleOne(skills.get(i));
			if(post!=null)
			{
				ListPosts.add(post);
				System.out.println("Post List : "+ListPosts.size());
			}
			
		} 
		return ListPosts;
	}
}

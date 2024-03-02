package com.example.JOBSHOP.JOBSHOP.Employer;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.postService;

@Service
public class employerService{

	@Autowired
	private employerRepository employerRepository;
	
	@Autowired
	private postService postService;
	

	public Employer insert(Employer employer)
	{
		return employerRepository.save(employer);
	}
	
	public Employer getReferenceById(Long id)
	{
		return employerRepository.getReferenceById(id);
	}
   
	public List<Employer> findAll()
	{
		return employerRepository.findAll();
	}
	
	
	public Employer findById(Long id)
	{
		Optional<Employer> finded=employerRepository.findById(id);
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
//		applicationRepository.updateEntity(t.getId(),t.getStatusCode()); 
//	}
	
	public Employer update(Employer t)
	{
		if(getReferenceById(t.getId())!=null)
		{
//			logInfo("Employer Updated Successfully");
			return employerRepository.save(t);
		}else 
		{
//			logError("EmployerNotFound");
			return null;
			
		}
	}
	
	public void deleteById(Long id)
	{
		Employer t=getReferenceById(id);
		if(t!=null)
		{
			employerRepository.deleteById(id);
		}
	}
	
	public Post createAPost(Post post)
	{
		return postService.insert(post);
	}

	public List<Post> findPostsByEmployerId(Long id)
	{
		return postService.findByEmployerId(id);
	}
	
	public List<Employer> findAllWithCompanyAdministrator(Long id)
	{
		return employerRepository.findByCompanyAdministratorId(id);
	}
	

	 public Employer insertPicture(Long id,byte[] picture)
	 {
	try {
		Optional<Employer> employerUpdate=employerRepository.findById(id);
		 if(employerUpdate.isPresent())
		 {
			 System.out.println("Job Seeker :" +employerUpdate.get().getEmail());
			 Employer employerForUpdate=employerUpdate.get();
			 employerForUpdate.setPicture(picture);
			 return employerRepository.save(employerForUpdate);
		 }else  
		 {
			 return null;
		 }
	} catch (Exception e) {
		return null;
	}
	 }
}

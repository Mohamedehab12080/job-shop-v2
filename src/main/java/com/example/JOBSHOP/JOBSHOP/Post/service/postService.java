package com.example.JOBSHOP.JOBSHOP.Post.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.Specifications.postSearch;
import com.example.JOBSHOP.JOBSHOP.Post.Specifications.postSpecification;
import com.example.JOBSHOP.JOBSHOP.Post.postField.service.postFieldServiceInterface;
import jakarta.transaction.Transactional;

@Service
public class postService implements postServiceInterface{

	@Autowired
	private postRepository postRepository;
	
	@Autowired
	private postFieldServiceInterface postFieldServiceI;
	
	@Override
	public List<Post> findPostsWithSearch(postSearch postSearch)
	{
		postSpecification postSpec=new postSpecification(postSearch);
		return postRepository.findAll(postSpec);
	}
	
	@Override
    public Post getReferenceById(Long id)
	{
		return postRepository.getReferenceById(id);
	}
	
	@Override
	public List<Post> findAll()
	{
		return postRepository.findAll();
	}
	
	@Override
	public Post insert(Post t)
	{
		return postRepository.save(t);
	}
	
	@Override
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
	
	@Override
	@Transactional
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
	
	@Override
	@Transactional
	public Post updateWithId(Long id, Post t)
	{
		try {
			Post lastPost=findById(id);
			
			if(lastPost!=null)
			{
				if(t.getDescription()!=null)
				{
					lastPost.setDescription(t.getDescription());
				}
				
				if(t.getEmploymentType()!=null)
				{
					lastPost.setEmploymentType(t.getEmploymentType());
				}
				
				if(t.getJobRequirments()!=null)
				{
					lastPost.setJobRequirments(t.getJobRequirments());
				}
				
				if(t.getStatusCode()!=null)
				{
					lastPost.setStatusCode(t.getStatusCode());
				}
				
				if(t.getTitle()!=null)
				{
					lastPost.setTitle(t.getTitle());
				}
				
				if(t.getPostFields()!=null||!t.getPostFields().isEmpty())
				{
					postFieldServiceI.insertAll(t.getPostFields());
					lastPost.setPostFields(t.getPostFields());
				}
				
				if(t.getLocation()!=null)
				{
					lastPost.setLocation(t.getLocation());
				}
				return postRepository.save(lastPost);
			}else 
			{
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public List<Post> insertAll(List<Post> entity)
	{
		return postRepository.saveAll(entity);
	}
	
	
	@Override
	public void deleteById(Long id)
	{
		Post t=getReferenceById(id);
		if(t!=null)
		{
			postRepository.deleteById(id);
		}
	}
	
	@Override
	public List<Post> findByEmployer(Long id)
	{ 
		return postRepository.findByEmployerId(id);
	}
	
	@Override
	public List<Post> findByCompanyProfile(Long id)
	{ 
		return postRepository.findByCompanyProfileId(id);
	}
	

	
	@Override
	public Set<Post> findPostsWithTitle(String title)
	{
		return postRepository.findByTitle(title);
	}
	
	@Override
	public List<Post> findByEmployerId(Long id)
	{
		return postRepository.findByEmployerId(id);
	}
	
	@Override
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
//	
//	public List<Post> findPostsWithProfileSkills(Long id)
//	{
//		List<String> skills=jobSeekerService.findSkillsById(id);
//		List<Post> ListPosts=new ArrayList<Post>();
//		Post post=null;
//		for(int i=0;i<skills.size();i++)
//		{
//			System.out.println("Skills : "+skills.get(i));
//			post=findPostsWithTitleOne(skills.get(i));
//			if(post!=null)
//			{
//				ListPosts.add(post);
//				System.out.println("Post List : "+ListPosts.size());
//			}
//			
//		} 
//		return ListPosts;
//	}

}

package com.example.JOBSHOP.JOBSHOP.Post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.Specifications.postSearch;
import com.example.JOBSHOP.JOBSHOP.Post.Specifications.postSpecification;
import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;
import com.example.JOBSHOP.JOBSHOP.Post.postField.service.postFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdministratorServiceInterface;

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
		List<Long> postIds=new ArrayList<Long>();
		List<Post> posts=findPostsWithFieldName(postSearch.getFieldName());
		if(posts!=null && !posts.isEmpty())
		{
			
			for(Post post :posts)
			{
				postIds.add(post.getId());
				System.out.println("POST FROM SEARCH WITH FIELDNAME : "+post.getPostFields().getField().getFieldName());
			}
		}
		
		postSpecification postSpec=new postSpecification(postSearch,postIds);
		
		return postRepository.findAll(postSpec);
	}
//	//Filter by companyName
//	if(postSearch.getCompanyName()!=null &&!postSearch.getCompanyName().isEmpty())
//	{
//		try {
//			predicatList.add(cb.equal(root.get("companyProfile"),companyAdminService.findcompanyProfileIdByCompanyName(postSearch.getCompanyName())));
//		} catch (UserException e) {
//			System.out.println("user Exception...");
//		}
//	}
	
//	if (postSearch.getFieldName() != null && !postSearch.getFieldName().isEmpty()) {
//        // Use the findByPostFieldsFieldFieldName method from PostRepository
//        List<Post> posts = postService.findPostsWithFieldName(postSearch.getFieldName());
//
//        if (posts !=null && !posts.isEmpty()) {
//            // Extract post IDs from the retrieved posts
//            List<Long> postIds = new ArrayList<>();
//            for (Post post : posts) {
//                postIds.add(post.getId());
//            }
//
//            // Add a predicate to filter by post IDs
//            predicatList.add(root.get("id").in(postIds));
//        }
//    }
	
	
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
				
				if(t.getPostFields()!=null)
				{
					postFieldServiceI.insert(t.getPostFields());
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
		Long postFieldId;
		if(t!=null)
		{
			if(t.getPostFields()!=null)
			{
				postFieldId=t.getPostFields().getId();
				postRepository.deleteById(id);
				postFieldServiceI.deleteById(postFieldId);
			}
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

	@Override
	public List<Post> findPostsWithFieldName(String fieldName) {
		return postRepository.findByPostFieldsFieldFieldName(fieldName);
	}

	
	@Override
	public void updatePostForCreate(Post t)
	{
		if(getReferenceById(t.getId())!=null)
		{
			 postRepository.save(t);
		}
	}
	@Override
	public postDTO update(Long postId, postDTO t) {

		Post oldPost=findById(postId);
		if(oldPost!=null)
		{
			if(t.getTitle() !=null && !t.getTitle().isBlank())
			{
				if(t.getSkills()!=null && !t.getSkills().isEmpty())
				{
					oldPost.setTitle(t.getTitle()+"{ "+t.getSkills().stream().collect(Collectors.joining(", "))+" }");
				}else 
				{
					oldPost.setTitle(t.getTitle());
				}
			}
			
			if(t.getField()!=null && t.getField()!=0)
			{
				postField fieldToUpdate=t.getPostField2();
				fieldToUpdate.setId(oldPost.getPostFields().getId());
				if(t.getSkills()!=null && !t.getSkills().isEmpty())
				{
					fieldToUpdate.setSkills(t.getSkills());
				}
				if(t.getQualifications()!=null && !t.getQualifications().isEmpty())
				{
					fieldToUpdate.setQualifications(t.getQualifications());
				}
				oldPost.setPostFields(postFieldServiceI.update(fieldToUpdate));
			}
			
			if(t.getDescription() !=null && !t.getDescription().isBlank())
			{
				oldPost.setDescription(t.getDescription());
			}
			
			if(t.getExperience()!=null && !t.getExperience().isBlank())
			{
				oldPost.setExperience(t.getExperience());
			}
			
			if(t.getJobRequirments() !=null && !t.getJobRequirments().isBlank())
			{
				oldPost.setJobRequirments(t.getJobRequirments());
			}
			
			if(t.getLocation()!=null && !t.getLocation().isEmpty())
			{
				oldPost.setLocation(t.getLocation());
			}
			
			if(t.getEmploymentType() !=null && !t.getEmploymentType().isBlank())
			{
				oldPost.setEmploymentType(t.getEmploymentType());
			}
			if(t.getPostImage()!=null && !t.getPostImage().isBlank())
			{
				oldPost.setImage(t.getPostImage());
			}
			Post updatedPost=postRepository.save(oldPost);
		return postMapper.mapPostTODTO(oldPost);
		}else
		{
			return null;
		}
		
		 
	}

}

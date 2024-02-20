package com.example.JOBSHOP.JOBSHOP.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;
import com.example.JOBSHOP.JOBSHOP.DTOs.postDTO;
import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.services.jobSeekerService;
import com.example.JOBSHOP.JOBSHOP.services.postService;

@RestController
@RequestMapping("/Post")
public class postCotroller {

	
	@Autowired
	private postService postService;
	
	@Autowired 
	private jobSeekerService jobSeekerService;

	@GetMapping("/findByTitle/{title}")
	public List<postDTO> findAllPostsWithTitle(@PathVariable String title)
	{
		List<Post> postList=postService.findPostsWithTitle(title);
		if(!postList.isEmpty())
		{
			
			return postList.stream()
					.map(this::convertPost)
					.collect(Collectors.toList());
		}else  
		{
			return null;
		}
	}  
	
	@GetMapping("/findPostsWithProfileSkills/{id}")
	public List<postDTO> getPosts(@PathVariable Long id)
	{
		
		List<Post>PostList=new ArrayList<Post>();
		 jobSeeker object=jobSeekerService.findById(id);
		if(object!=null) 
		{
			for(int i=0;i<object.getSkills().size();i++)
			{
				Post post=postService.findPostsWithTitleOne(object.getSkills().get(i));	
				PostList.add(post);
			}
			if(PostList.size()!=0) 
			{
				return PostList.stream()
						.map(this::convertPost) 
						.collect(Collectors.toList());
			}else 
			{
				return null;
			}
		}else 
		{
			return null;
		}
	
	}
//	@GetMapping("/findPostsWithProfileSkills/{id}")
//	public List<postDTO> findPostsWithProfileSkills(@PathVariable Long id)
//	{
//		List<Post> postList=postService.findPostsWithProfileSkills(id);
//		return postList.stream()
//				.map(this::convertPost)
//				.collect(Collectors.toList());
//	}   
//	
	private postDTO convertPost(Post post)
	{
		return entityToDTOMapper.mapPostToDTO(post);
	}
}

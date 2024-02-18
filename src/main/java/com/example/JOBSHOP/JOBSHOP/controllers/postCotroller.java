package com.example.JOBSHOP.JOBSHOP.controllers;

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
import com.example.JOBSHOP.JOBSHOP.services.postService;

@RestController
@RequestMapping("/Post")
public class postCotroller {

	@Autowired
	private postService postService;
	
	@GetMapping("/findByTitle/{title}")
	public List<postDTO> findAllPostsWithTitle(@PathVariable String title)
	{
		List<Post> postList=postService.findPostsWithTitle(title);
		return postList.stream()
				.map(this::convertPost)
				.collect(Collectors.toList());
	}  
	@GetMapping("/findPostsWithProfileSkills/{id}")
	public List<postDTO> findPostsWithProfileSkills(@PathVariable Long Id)
	{
		List<Post> postList=postService.findPostsWithProfileSkills(Id);
		return postList.stream()
				.map(this::convertPost)
				.collect(Collectors.toList());
	}   
	
	private postDTO convertPost(Post post)
	{
		return entityToDTOMapper.mapPostToDTO(post);
	}
}

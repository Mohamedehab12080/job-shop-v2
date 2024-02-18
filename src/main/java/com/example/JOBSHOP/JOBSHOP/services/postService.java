package com.example.JOBSHOP.JOBSHOP.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.repositories.jobSeekerRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.postRepository;

@Service
public class postService {

	@Autowired
	private postRepository postRepository;
	
	@Autowired
	private jobSeekerRepository jobSeekerRepository;
	
	public List<Post> findAll()
	{
		return postRepository.findAll();
	}
	public Post insertPost(Post post)
	{
		return postRepository.save(post);
	}
	
	public List<Post> findPostsWithTitle(String title)
	{
		return postRepository.findByTitle(title);
	}
	
	public Post findPostsWithTitleOne(String title)
	{
		return postRepository.findByTitles(title);
	}
	
	public List<Post> findPostsWithProfileSkills(Long id)
	{
		List<String> skills=jobSeekerRepository.findSkillsById(id);
		List<Post> ListPosts=new ArrayList<Post>();
		for(int i=0;i<skills.size();i++)
		{
			ListPosts.add(findPostsWithTitleOne(skills.get(i)));
		} 
		return ListPosts;
	}
}

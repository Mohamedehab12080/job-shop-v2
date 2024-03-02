package com.example.JOBSHOP.JOBSHOP.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.applicationService;
import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;
import com.example.JOBSHOP.JOBSHOP.Post.Specifications.postSearch;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeekerService;

@RestController
@RequestMapping("/Post")
public class postCotroller {

	
	@Autowired
	private postService postService;
	
	@Autowired
	private applicationService applicationService;
	@Autowired 
	private jobSeekerService jobSeekerService;

//	@GetMapping("/findByTitle/{title}")
//	public List<postDTO> findAllPostsWithTitle(@PathVariable String title)
//	{
//		List<Post> postList=postService.findPostsWithTitle(title);
//		if(!postList.isEmpty())
//		{
//			
//			return postList.stream()
//					.map(this::convertPost)
//					.collect(Collectors.toList());
//		}else  
//		{
//			return null;
//		}
//	}  
	
	
	@PostMapping("/postSearch")
	public List<postDTO> findBostWithSpecifications(@RequestBody postSearch postSearch)
	{
		List<Post> postList=postService.findPostsWithSearch(postSearch);
		return postList.stream() 
				.map(this::convertPost)
				.collect(Collectors.toList());
	} 
	
	@GetMapping("/getBestApplications/{id}") //Post id
	public List<applicationDTO> getBestApplicationsForPost(@PathVariable Long id)
	{ 
		List<Application> appList=applicationService.getBestApplicationsForPost(postService.findById(id));
		return appList.stream() 
				.map(this::convertApplicationToDTO)
				.collect(Collectors.toList()); 
	}
	
	private applicationDTO convertApplicationToDTO(Application app)
	{
		return entityToDTOMapper.mapApplicationToDTO(app);
	}
	
	@GetMapping("/findPostsWithProfileSkills/{id}")
	public List <postDTO> getPostss(@PathVariable Long id)
	{
		return jobSeekerService.getPostsWithSkillsOnPublic(id)
				.stream()
				.map(this::convertPost)
				.collect(Collectors.toList()); // this list will be sent to the thymeleaf with model.addAttribute("posts",postDtoList)
	}
//	@GetMapping("/findPostsWithProfileSkills/{id}")
//	public List<postDTO> getPosts(@PathVariable Long id)
//	{
//		Map<String, Integer> postCountMap = new HashMap<>();
//        jobSeeker object = jobSeekerService.findById(id);
//        
//        if (object != null) {
//            for (String skill : object.getSkills()) {
//                List<Post> posts = postService.findPostsWithTitle(skill);
//                if (posts != null) {
//                    for (Post post : posts) {
//                        String title = post.getTitle();
//                        postCountMap.put(title, postCountMap.getOrDefault(title, 0) + 1);
//                    }
//                }
//            }
//            
//            PriorityQueue<Post> postPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(post -> -postCountMap.getOrDefault(post.getTitle(), 0)));
//            Set<String> uniquePosts = new HashSet<>(); // Track unique posts
//            
//            for (String skill : object.getSkills()) {
//                List<Post> posts = postService.findPostsWithTitle(skill);
//                if (posts != null) {
//                    for (Post post : posts) {
//                        if (uniquePosts.add(post.getTitle())) { // Add post's id to the set
//                            postPriorityQueue.offer(post);
//                        }
//                    }
//                }
//            }
//            
//            List<Post> sortedPosts = new ArrayList<>();
//            while (!postPriorityQueue.isEmpty()) {
//                sortedPosts.add(postPriorityQueue.poll());
//            }
//         
//            return sortedPosts.stream()
//            		.map(this::convertPost)
//            		.collect(Collectors.toList());
//        } else {
//            return null;
//        }
////		List<Post>PostList=new ArrayList<Post>();
////		 jobSeeker object=jobSeekerService.findById(id);
////		if(object!=null) 
////		{
////			for(int i=0;i<object.getSkills().size();i++)
////			{
////				Post post=postService.findPostsWithTitleOne(object.getSkills().get(i));	
////				PostList.add(post);
////			}
////			
////			if(PostList.size()!=0) 
////			{
////				return PostList.stream()
////						.map(this::convertPost) 
////						.collect(Collectors.toList());
////			}else 
////			{
////				return null;
////			}
////		}else 
////		{
////			return null;
////		}
////	
//	}
////	@GetMapping("/findPostsWithProfileSkills/{id}")
////	public List<postDTO> findPostsWithProfileSkills(@PathVariable Long id)
////	{
////		List<Post> postList=postService.findPostsWithProfileSkills(id);
////		return postList.stream()
////				.map(this::convertPost)
////				.collect(Collectors.toList());
////	}   
////	
	@GetMapping("/findByCompanyProfile/{id}")
	public List<postDTO> findByCompanyProfileId(@PathVariable("id") Long id)
	{
		List<Post> postList=postService.findByCompanyProfile(id);
		return postList.stream() 
				.map(this::convertPost)
				.collect(Collectors.toList());
	}
	private postDTO convertPost(Post post)
	{
		return entityToDTOMapper.mapPostToDTO(post);
	}
}

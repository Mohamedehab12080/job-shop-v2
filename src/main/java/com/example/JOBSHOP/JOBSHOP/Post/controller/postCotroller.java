package com.example.JOBSHOP.JOBSHOP.Post.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationMapper;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.Specifications.postSearch;
import com.example.JOBSHOP.JOBSHOP.Post.service.postService;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerService;

@RestController
@RequestMapping("/api/Post")
@CrossOrigin(origins = "http://localhost:3000")
public class postCotroller {

	
	@Autowired
	private postService postService;
	
	@Autowired
	private applicationServiceImpl applicationService;
	@Autowired 
	private jobSeekerService jobSeekerService;

	@Autowired
	private userServiceInterface userServiceI;
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
		return applicationService.getBestApplicationsForPost(convertPost(postService.findById(id)));
	}
	
	private applicationDTO convertApplicationToDTO(Application app)
	{
		return applicationMapper.mapApplicationToDTO(app);
	}
	
	@GetMapping("/findPostsWithProfileSkills/{id}")
	public ResponseEntity<List <postDTO>> getPostss(
			@PathVariable("id") Long id,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
		{
			return new ResponseEntity<List<postDTO>>(jobSeekerService.getPostsWithSkillsOnPublic(id),HttpStatus.OK); // this list will be sent to the thymeleaf with model.addAttribute("posts",postDtoList)
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
//		try {
//			
//			
////			List <Post> posts=jobSeekerService.getPostsWithSkillsOnPublic(id);
////			List<String> postsTitle=new ArrayList<String>();
////			for(Post post:posts)
////			{
////				System.out.println("POSTS MATHCHED : "+posts.size());
////				postsTitle.add(post.getTitle());
////			}
////			return postsTitle;
//			
////			
//		} catch (Exception e) {
//			System.out.println("Error at find posts : "+e);
//			return null;
//		}
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
	public List<String> findByCompanyProfileId(@PathVariable("id") Long id)
	{
		List<Post> postList=postService.findByCompanyProfile(id);
		List<String> postTitles=new ArrayList<String>();
		for(Post post:postList)
		{
			postTitles.add(post.getTitle());
		}
		return postTitles;
		
//		return postList.stream() 
//				.map(this::convertPost)
//				.collect(Collectors.toList());
	}
	private postDTO convertPost(Post post)
	{
		return postMapper.mapPostTODTO(post);
	}
}

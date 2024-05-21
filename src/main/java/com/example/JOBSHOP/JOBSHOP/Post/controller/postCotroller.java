package com.example.JOBSHOP.JOBSHOP.Post.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationMapper;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceInerface;
import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.service.employerServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.Specifications.postSearch;
import com.example.JOBSHOP.JOBSHOP.Post.service.postService;
import com.example.JOBSHOP.JOBSHOP.Registration.event.listener.registrationCompleteEventListener;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.userService;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/Post")
@CrossOrigin(origins = "http://localhost:3000")
public class postCotroller {

	
	@Autowired
	private postService postService;
	

	@Autowired 
	private jobSeekerService jobSeekerService;

	@Autowired
	private userServiceInterface userServiceI;
	
	@Autowired
	private companyProfileService companyProfileService;
	
	@Autowired
	private applicationServiceInerface applicationServiceI;
	
	@Autowired 
	private employerServiceInterface employerServiceI;
	
	@Autowired
	private registrationCompleteEventListener registrationCompleteEventListener;
	
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
	public ResponseEntity<List<postDTO>> findBostWithSpecifications(
			@RequestBody postSearch postSearch
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user =userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			List<Post> postList=postService.findPostsWithSearch(postSearch);
			
			List<postDTO> postListDto= postList.stream() 
					.map(this::convertPost)
					.collect(Collectors.toList());
			return new ResponseEntity<List<postDTO>>(
					jobSeekerService.getPostsFromSearchAndJobSeekerSkills(user.getId(), postListDto),HttpStatus.OK);
		}else 
		{
			throw new UserException("User not found for this token");
		}
		
		
	} 
	
	@GetMapping("/findById/{postId}")
	public ResponseEntity<postDTO> findPostById(@PathVariable("postId") Long postId,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			return new ResponseEntity<postDTO>(postMapper.mapPostTODTO(postService.findById(postId)),HttpStatus.OK);
		}else 
		{
			throw new UserException("User not found for this token");
		}
	}
	
	@DeleteMapping("companyDeletePost/{postId}")
	public ResponseEntity<?> deletePost(
			@PathVariable("postId") Long postId
			,@RequestHeader("Authorization") String jwt)throws UserException 
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null && !user.getUserType().equals(Role.jobSeeker))
		{
			postService.deleteById(postId);
			return new ResponseEntity< >("Post Deleted Success",HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
			
	}
	
	@GetMapping("/getBestApplications/{id}") //Post id
	public ResponseEntity<List<applicationDTO>> getBestApplicationsForPost(
			@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws UserException
	{ 
		User user =userServiceI.findUserByJwt(jwt);
		if(user!=null && !user.getUserType().equals(Role.jobSeeker))
		{
			System.out.println("Size of Returned Best Applications : "+applicationServiceI
					.getBestApplicationsForPost(
							convertPost(
									postService
									.findById(id)
									)).size());
			return new 
					ResponseEntity<List<applicationDTO>>(
							applicationServiceI
							.getBestApplicationsForPost(
									convertPost(
											postService
											.findById(id)
											)),HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	
	@DeleteMapping("/deleteApplication/{id}")
	private ResponseEntity<?> 
	deleteApplicationByApplicationId(@PathVariable Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null && !user.getUserType().equals(Role.jobSeeker))
		{
			applicationServiceI.deleteById(id);
			return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	
	@PutMapping("/acceptApplication/{id}")
	private ResponseEntity<applicationDTO> acceptApplication(
			@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws UserException, UnsupportedEncodingException, MessagingException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null && !user.getUserType().equals(Role.jobSeeker))
		{
			applicationDTO appDto=convertApplicationsWithPostSkillsAndQualifications(applicationServiceI.accept(id));
			String title="";
			if(appDto.getPostTitle().contains("{"))
			{
				title=appDto.getPostTitle()
						.substring(0,appDto.getPostTitle().indexOf("{"));
			}else 
			{
				title=appDto.getPostTitle();
			}
			registrationCompleteEventListener
			.sendMailAcceptedApplicationToThejobSeeker(
					user.getEmail(),
					appDto.getJobSeekerr(),
					"http://localhost:3000/postDetails/"+appDto.getPostId(),
					""+appDto.getId(),
					title);
			return new ResponseEntity<>(appDto,HttpStatus.OK);
		}else
		{
			throw new UserException("user not found for this token");
		}
	}
	


	@PutMapping("/rejectApplication/{id}")
	private ResponseEntity<applicationDTO> rejectApplication(
			@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null && !user.getUserType().equals(Role.jobSeeker))
		{
			return new ResponseEntity<>(convertApplicationsWithPostSkillsAndQualifications(applicationServiceI.reject(id)),HttpStatus.OK);
		}else
		{
			throw new UserException("user not found for this token");
		}
	}
	
	private applicationDTO convertApplicationsWithPostSkillsAndQualifications(Application app)
	{
		applicationMapper appMapper=new applicationMapper(postService);
		return appMapper.mapApplicationToDTOIncludingPostSkillsAndQualifications(app);
	}
	private applicationDTO convertApplicationToDTO(Application app)
	{
		return applicationMapper.mapApplicationToDTO(app);
	}
	
	
	@PostMapping("/recommendation")
	public ResponseEntity<?> getRecommendedPostsFromModel(
			@RequestBody List<String> skills,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			return new ResponseEntity<>(jobSeekerService.callFlaskAPI(String.join(",", skills)),HttpStatus.OK);
		}else 
		{
			throw new UserException("user Not found for this token :");
		}
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
	@GetMapping("/findByCompanyProfileAdminId/{id}")
	public ResponseEntity<List<String>> findByCompanyProfileId(
			@PathVariable("id") Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user =userServiceI.findUserByJwt(jwt);
		if(user!=null && !user.getUserType().equals(Role.jobSeeker))
		{
			companyProfile compPR=companyProfileService.findByCompanyAdmin(id);
			List<Post> postList=postService.findByCompanyProfile(compPR.getId());
			List<String> postTitles=new ArrayList<String>();
			for(Post post:postList)
			{
				postTitles.add(post.getTitle());
			}
			return new ResponseEntity<List<String>>(postTitles,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token or your type not allowed");
		}
		
		
//		return postList.stream() 
//				.map(this::convertPost)
//				.collect(Collectors.toList());
	}
	
	@GetMapping("/findPostsForCompany/{id}")
	public ResponseEntity<List<postDTO>> findPostsForCompany(
			@PathVariable("id") Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user =userServiceI.findUserByJwt(jwt);
		Employer emp=null;
		Long companyAdminId;
		if(user!=null && !user.getUserType().equals(Role.jobSeeker))
		{
			if(user.getUserType().equals(Role.Employer))
			{
				System.out.println("Condition of user tYpe Employer ");
				emp=employerServiceI.findById(id);
				companyAdminId=emp.getCompanyAdmin().getId();
			}else 
			{
				companyAdminId=user.getId();
			}
			companyProfile compPR=companyProfileService.findByCompanyAdmin(companyAdminId);
			List<Post> postList=postService.findByCompanyProfile(compPR.getId());
			return new ResponseEntity<List<postDTO>>(
					postList
					.stream()
					.map(postMapper::mapPostTODTO)
					.collect(
							Collectors
							.toList())
					,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token or your type not allowed");
		}
		
			
//		return postList.stream() 
//				.map(this::convertPost)
//				.collect(Collectors.toList());
	}
	
	@GetMapping("/findPostsForEmployer/{id}")
	public ResponseEntity<List<postDTO>> findPostsForEmployer(
			@PathVariable("id") Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user =userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			List<Post> postList=postService.findByEmployer(id);
			return new ResponseEntity<List<postDTO>>(
					postList
					.stream()
					.map(postMapper::mapPostTODTO)
					.collect(
							Collectors
							.toList())
					,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	private postDTO convertPost(Post post)
	{
		return postMapper.mapPostTODTO(post);
	}
	
}

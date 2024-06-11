package com.example.JOBSHOP.JOBSHOP.jobSeeker.service;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationMapper;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.applicationQualification;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.DTO.applicationQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.DTO.applicationQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.service.applicaitonQualificationServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.service.applicationQualificationInterface;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceInerface;
import com.example.JOBSHOP.JOBSHOP.Application.skill.applicationSkill;
import com.example.JOBSHOP.JOBSHOP.Application.skill.DTO.applicationSkillDTO;
import com.example.JOBSHOP.JOBSHOP.Application.skill.DTO.applicationSkillMapper;
import com.example.JOBSHOP.JOBSHOP.Application.skill.service.applicationSkillServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.controller.backEndResponse;
import com.example.JOBSHOP.JOBSHOP.Post.controller.pythonApiResponse;
import com.example.JOBSHOP.JOBSHOP.Post.postField.DTO.postFieldDTO;
import com.example.JOBSHOP.JOBSHOP.Post.service.postRepository;
import com.example.JOBSHOP.JOBSHOP.Registration.controllers.registerUserRequest;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;
import com.example.JOBSHOP.JOBSHOP.degrees.service.qualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.controller.skillsAndQualificationsRequest;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.jobSeekerProfileService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.service.jobSeekerQualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.requests.saveSkillsRequest;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.service.jobSeekerSkillRepository;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.service.jobSeekerSkillServiceInterface;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;
import com.example.JOBSHOP.JOBSHOP.skills.service.skillServiceInterface;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
@Service
public class jobSeekerService implements jobSeekerServiceInterface{

	
	 @Autowired
	 private jobSeekerRepository jobSeekerRepository;
	 
	 @Autowired 
	 private jobSeekerProfileService jobSeekerProfileService;
	 
	 @Autowired
	 private followService followService;
	 
	 @Autowired
	 private applicationServiceImpl applicationService;
	 
	 @Autowired
	 private skillServiceInterface skillServiceI;
	 
	 @Autowired
	 private qualificationServiceInterface qualificationServiceI;
	 
	 @Autowired
	 private applicationSkillServiceInterface applicationSkillServiceI;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private applicationServiceInerface applicationServiceI;
	 @Autowired
	 private jobSeekerQualificationServiceInterface jobSeekerQualificationServiceI;
	 @Autowired
	 private jobSeekerSkillServiceInterface jobSeekerSkillServiceI;
	 @Autowired
	 private postRepository postService;
	 @Autowired
	 private applicationQualificationInterface applicationQualificationI;
	 
//	 public List<User> getJobSeekerFollowers(jobSeeker jobSeeker)
//	 {
//		 return followSerivice.getFollowersById(jobSeeker);
//	 }
//	 public List<User> getJobSeekerFollowings(jobSeeker jobSeeker)
//	 {
//		 return followSerivice.getFollowingById(jobSeeker);
//	 }
	 
	 
	 		
	    public jobSeeker getReferenceById(Long id)
		{
			return jobSeekerRepository.getReferenceById(id);
		}
	   
		public List<jobSeeker> findAll()
		{
			return jobSeekerRepository.findAll();
		}
		
		public jobSeeker findById(Long id)
		{
			Optional<jobSeeker> finded=jobSeekerRepository.findById(id);
			if(finded.isPresent())
			{
				return finded.get();
			}else 
			{
				return null;
			}
			
		}
		
//		public void updateEntityStatus(Application t)
//		{
//			jobSeekerRepository.updateEntity(t.getId(),t.getStatusCode()); 
//		}
		@Transactional
		@Override
		public jobSeekerDTO update(Long jobSeekerId,registerUserRequest user) throws ParseException
		{
			jobSeeker oldJobSeeker=findById(jobSeekerId);
			if(user.getUserName()!=null)
			{
				oldJobSeeker.setUserName(user.getUserName());
			}
			
			if(user.getAddress() !=null)
			{
				oldJobSeeker.setAddress(user.getAddress());
			}
			
			if(user.getGender() !=null)
			{
				oldJobSeeker.setGender(user.getGender());
			}
			
			if(user.getPassword() !=null)
			{
				oldJobSeeker.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			
			if(user.getPicture()!=null)
			{
				oldJobSeeker.setPicture(user.getPicture());
			}
			
			if(user.getEducation()!=null)
			{
				oldJobSeeker.setEducation(user.getEducation());
			}
			
			if(user.getBirthDate()!=null)
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				oldJobSeeker.setBirthDate(dateFormat.parse(user.getBirthDate().substring(0,10)));
			}
			
			if(user.getEmploymentState()!=null)
			{
				oldJobSeeker.setEmploymentState(user.getEmploymentState());
			}
			
			if(user.getExperience()!=null)
			{
				oldJobSeeker.setExperience(user.getExperience());
			}
			
			if(user.getCoverImage() !=null)
			{
				oldJobSeeker.setCoverImage(user.getCoverImage());
			}
				
			jobSeeker insertedJobSeeker= jobSeekerRepository.save(oldJobSeeker);
			jobSeekerDTO dto=jobSeekerMapper.mapJobSeekerToDTO(oldJobSeeker);
			dto.setReq_user(true);
			return dto;
			
		}
		@Transactional
		public List<jobSeeker> insertAll(List<jobSeeker> entity)
		{
			return jobSeekerRepository.saveAll(entity);
		}
		
		public void deleteById(Long id)
		{
			jobSeeker t=getReferenceById(id);
			if(t!=null)
			{
				jobSeekerRepository.deleteById(id);
			}
		}
	 /**
	 * 
	 * @author BOB
	 * @Function Insert jobSeeker With his Profile.
	 */
	 public jobSeeker insert(jobSeeker jobSeeker)
	 {
		if(jobSeeker!=null)
		{
			 jobSeekerProfile jobSeekerProfile=new jobSeekerProfile();
			 jobSeekerProfile.setJobSeeker(jobSeeker); 
			 jobSeeker jobSeekersaved=jobSeekerRepository.save(jobSeeker);
			if(jobSeekersaved!=null)
			{
				 jobSeekerProfileService.insert(jobSeekerProfile);
				 return jobSeekersaved;
			}else 
			{
				return null;
			}
		}else 
		{
			return null;
		}
	 }
	
	 /**
	 * 
	 * @author BOB
	 * @Function find all jobSeeker's submitted applications by jobSeeker_id Order by LIFO.
	 */
	 public List<Application> findAllApplicationsForJobSeeker(Long id)
	 {
		return applicationService.findByJobSeekerIdOrderByCreatedDate(id);
	 }
	 
	 
	 private postDTO convertPostToDto(Post post)
	 {
		 return postMapper.mapPostTODTO(post);
	 }
	 
	 private boolean checkForApply(Long jobSekerId,Post post)
	 {
		 Application app=applicationServiceI.findByJobSeekerIdAndPostId(jobSekerId,post.getId());
		 if(app!=null)
		 {
			 return false;
		 }else 
		 {
			 return true;
		 }
	 }
	 
	 private boolean checkForApplyPostDto(Long jobSekerId,postDTO post)
	 {
		 Application app=applicationServiceI.findByJobSeekerIdAndPostId(jobSekerId,post.getId());
		 if(app!=null)
		 {
			 return false;
		 }else 
		 {
			 return true;
		 }
	 }
	 
	 
	 /**
	 * 
	 * @author BOB
	 * @Function  jobSeeker will select skills he want to apply with them 
	 * and if he don't have the skill it will automatically 
	 * added to his skills in jobSeekerSkill table
	 */
	 @Override
	 @Transactional
	 public applicationReturnedSkillsAndQualifications applyForPost(applicationDTO app)
	 {
		 
		 try {
			 Application apps=applicationMapper.mapDTOToApplicationForInsertApplicaiton(app);
			 Optional<Post> post=postService.findById(apps.getPost().getId()); 
			 List<applicationQualification> appQualListForInsert=new ArrayList<applicationQualification>();
			 List<applicationSkill>appSkillListForInsert=new ArrayList<applicationSkill>();
			 List<String> skillsToSend=new ArrayList<String>();
			 List<String> qualificationsToSend=new ArrayList<String>();
			 
			 for(applicationSkillDTO appSkill:app.getApplicationSkills())
			 {
				 jobSeekerSkill jobSeekerSkill=jobSeekerSkillServiceI.findById(appSkill.getJobSeekerSkillId()).get();
				 skillsToSend.add(jobSeekerSkill.getSkill().getSkillName());
			 }

			 for(applicationQualificationDTO appQual:app.getApplicationQualifications())
			 {
				 jobSeekerQualification qual=jobSeekerQualificationServiceI.findById(appQual.getJobSeekerQualificationId()).get();
				 qualificationsToSend.add(qual.getQualification().getQualificationName());
			 }
			 
			 if(post.isPresent())
			 {
				 postDTO postDto=convertPostToDto(post.get());
				 List<String> postSkills=postDto.getSkills();
				 List<String> postQualifications=postDto.getQualifications();
					
//						for(String skill:)
//						{
//							postSkills.add(skill);
//						}
//						
//						for(String qual:)
//						{
//							postQualifications.add(qual);
//						}
						
				 if(applicationService
						 .returningTheRemainedSkills(postSkills,skillsToSend)
						 .equals("No")
						 && applicationService
						 .returningRemainedQualifications(postQualifications, qualificationsToSend).equals("No"))
				 {
					 applicationReturnedSkillsAndQualifications returnedWithoutApply=applicationService.getApplicationReturnedSkillsAndQualifications();
					 returnedWithoutApply.setMatched(false);
					 returnedWithoutApply.setPostId(app.getPostId());
					 return returnedWithoutApply; // return the remained skills without apply 
				 }else if(applicationService
						 .returningRemainedQualifications
						 (postQualifications, qualificationsToSend)
						 .equals("Yes"))
				 {
					 Application insertedApp=applicationService.insert(apps);// apply
					 for(applicationQualification appQual:
						 	 app.getApplicationQualifications()
							 .stream()
							 .map(this::convertDTOToApplicationQualification)
							 .collect(Collectors.toList()))
					 {
						 appQual.setApplication(insertedApp);
						 appQualListForInsert.add(appQual);
					 }
					 System.out.println("Application Qualification For insert List Size : "+appQualListForInsert.size());
					 applicationQualificationI.insertAll(appQualListForInsert);
					 
					for(applicationSkill appSkill:
						app.getApplicationSkills()
						.stream()
						.map(this::convertDTOToApplicationSkill)
						.collect(Collectors.toList()))
					{
						appSkill.setApplication(apps);
						appSkillListForInsert.add(appSkill);
					}
					applicationSkillServiceI.insertAll(appSkillListForInsert);
					 System.out.println("Application Skills For insert List Size : "+appSkillListForInsert.size());

					 applicationReturnedSkillsAndQualifications returnedWithApply=applicationService.getApplicationReturnedSkillsAndQualifications();
					 returnedWithApply.setApplicationId(insertedApp.getId());
					 returnedWithApply.setPostId(postDto.getId());
					 returnedWithApply.setPostTitle(postDto.getTitle());
					 returnedWithApply.setEmployerEmail(postDto.getEmployerEmail());
					 returnedWithApply.setEmployerUserName(postDto.getEmployerUserName());
					 returnedWithApply.setMatched(true);
					 returnedWithApply.setPostId(app.getPostId());
					 return returnedWithApply;
				 }else 
				 {
					 return null;
				 }
			 }else 
			 {
				 return null;
			 }
			 
		} catch (Exception e) {
			System.out.println("Error From apply : :  : "+e.getMessage());
			return null;
		}
	 }
	 
	 private applicationQualification convertDTOToApplicationQualification(applicationQualificationDTO dto)
	 {
		 return applicationQualificationMapper.mapDTOToApplicationQualification(dto);
	 }
	 private applicationSkill convertDTOToApplicationSkill(applicationSkillDTO dto)
	 {
		 return applicationSkillMapper.mapDTOToApplicationSkill(dto);
	 }
	 private postDTO mapPostToDTO(Post post)
	 {
		 return postMapper.mapPostTODTO(post);
	 }
	 
//	 public List<String> findSkillsById(Long id)
//	 {
//		 return jobSeekerRepository.findSkillsById(id);
//	 }
	 /**
	 * 
	 * @author BOB
	 * @Function jobSeeker update his picture
	 */
	 public jobSeeker insertPicture(Long id,String picture)
	 {
		 try {
			Optional<jobSeeker> jobSeekerUpdate=jobSeekerRepository.findById(id);
			 if(jobSeekerUpdate.isPresent())
			 {
				 System.out.println("Job Seeker :" +jobSeekerUpdate.get().getEmail());
				 jobSeeker jobSeekerForUpdate=jobSeekerUpdate.get();
				 jobSeekerForUpdate.setPicture(picture);
				 return jobSeekerRepository.save(jobSeekerForUpdate);
			 }else  
			 {
				 return null;
			 }
		} catch (Exception e) {
			return null;
		}
	 }
	 
//	 public jobSeekerProfile insertProfile(jobSeekerProfile jobSeekerProfile)
//	 {
//		 return jobSeekerProfileRepository.save(jobSeekerProfile);
//	 }
	 
	 /**
	 * 
	 * @author BOB
	 * @Function find jobSeeker profile by jobSeeker_Id 
	 */
	 public jobSeekerProfile findByJobSeekerId(Long id)
	 {
		return jobSeekerProfileService.findByJobSeekerId(id).get();
	 } 
	 
	 private jobSeekerSkillDTO convertJobSeekerSkillToDto(jobSeekerSkill jobSeekerSkill)
	 {
		 return jobSeekerSkillMapper.mapJobSeekerSkillToDTO(jobSeekerSkill);
	 }
	 private jobSeekerQualificationDTO convertJobSeekerQualificationToDto(jobSeekerQualification jobSeekerQualification)
	 {
		 return jobSeekerQualificationMapper.mapJobSeekerQualificationToDTO(jobSeekerQualification);
	 }
	 public jobSeekerProfile findJobSeekerProfileWithjobSeekerID(Long jobSeekerId)
	 {
		return jobSeekerProfileService.findByJobSeeker_id(jobSeekerId).get();
	 } 
	 
//	 /**
//		 * 
//		 * @Author BOB
//		 * @return List <Post> that contains job seeker skills order by descending
//		 */
//	 @Override
//	 public List<Post> getPostsWithSkillsOnPublic(Long jobSeekerId) {
//		    // Fetch job seeker and their skills
//		    jobSeeker jobSeeker = findById(jobSeekerId);
//		    List<jobSeekerSkillDTO> jobSeekerSkillDtoList = 
//		    		jobSeekerSkillServiceI
//		    		.findByJobSeekerId(jobSeeker.getId())
//		            .stream()
//		            .map(this::convertJobSeekerSkillToDto)
//		            .collect(Collectors.toList());
//
//		    // Extract skill names
//		    Set<String> jobSeekerSkills = jobSeekerSkillDtoList.stream()
//		            .map(jobSeekerSkillDTO::getSkillName)
//		            .collect(Collectors.toSet());
//
//		    // Fetch posts based on job seeker skills
//		    Set<Post> posts = jobSeekerSkills.stream()
//		            .flatMap(skill -> postService.findByTitle(skill).stream())
//		            .collect(Collectors.toSet());
//
//		    if (posts.isEmpty()) {
//		        return Collections.emptyList();
//		    }
//
//		    // Calculate and sort post scores
//		    List<Post> sortedPosts = posts.stream()
//		            .map(post -> {
//		                postDTO postDto = convertPostToDto(post);
//		                Set<String> postSkills = postDto.getPostFieldsDto().stream()
//		                        .flatMap(pf -> pf.getSkills().stream())
//		                        .collect(Collectors.toSet());
//
//		                int score = calculateScore(new ArrayList<String>(postSkills),new ArrayList<String>( jobSeekerSkills));
//		                return new postScore(post, score);
//		            })
//		            .sorted(Comparator.comparingInt(postScore::getScore).reversed())
//		            .map(postScore::getPost)
//		            .collect(Collectors.toList());
//
//		    return sortedPosts;
//		}
	 
	 
	 /**
	  * @author BOBO
	  * The sorted method the score to sort the postScore objects. 
	  * Higher values (indicating more matched skills and fewer remaining skills) will come first due to the negative sign and the nature of the ratio.
	  */
	 @Override
	 public List<postDTO> getPostsWithSkillsOnPublic(Long jobSeekerId) {
	     // Fetch job seeker and their skills
	     jobSeeker jobSeeker = findById(jobSeekerId);
	     if(jobSeeker!=null)
	     {
	    	  Set<String> jobSeekerSkills = 
	 	    		 jobSeekerSkillServiceI.findByJobSeekerId(jobSeeker.getId())
	 	             .stream().map(this::convertJobSeekerSkillToDto)
	 	             .map(jobSeekerSkillDTO::getSkillName)
	 	             .collect(Collectors.toSet());
				System.out.println("Job Seeker Skills : "+jobSeekerSkills);
				
				Set<String> jobSeekerQualifications=
						jobSeekerQualificationServiceI.findByJobSeekerId(jobSeeker.getId())
						.stream().map(this::convertJobSeekerQualificationToDto)
						.map(jobSeekerQualificationDTO::getQualificationName)
						.collect(Collectors.toSet());
				System.out.println("Job Seeker Qualifications : "+jobSeekerQualifications);
				
				
				Set<postDTO> posts =jobSeekerSkills.stream()
						.flatMap(skill -> postService.findByTitle(skill).stream())
						.filter(post -> checkForApply(jobSeekerId, post))
						.map(this::convertPostToDto)
						.collect(Collectors.toSet());
				
				if (posts.isEmpty()) {
				return Collections.emptyList();
				}
				
				// Calculate and sort post scores
				List<postDTO> sortedPosts = posts.stream()				       
						.map(post -> {
				            Set<String> postSkills = post.getSkills().stream()
				                    .collect(Collectors.toSet());
				            Set<String> postQualifications = post.getQualifications().stream()
				                    .collect(Collectors.toSet());

				            int score = calculateScore(
				            		new ArrayList<String>(postQualifications),
				            		new ArrayList<>(postSkills),
				            		new ArrayList<String>(jobSeekerQualifications),
				            		new ArrayList<>(jobSeekerSkills));
				            
				            List<String>remainedSkills=applicationService
				            							.returningRemainedSkillsForListOfPosts(
				            									new ArrayList<String>(postSkills),
				            									new ArrayList<String>(jobSeekerSkills));
				            
				            List<String> remainedQualifications=applicationService
				            									.returningRemainedQualificationsForPostList(
				            											new ArrayList<String>(postQualifications),
				            											new ArrayList<String>(jobSeekerQualifications));
				            List<String> matchedSkills=new ArrayList<String>();
				            for(String matchedSkill:postSkills)
				            {
				            	if(!remainedSkills.contains(matchedSkill))
				            	{
				            		matchedSkills.add(matchedSkill);
				            	}
				            }
				            post.setMatchedSkills(matchedSkills);
				            List<String> matchedQualifications=new ArrayList<String>();
				            for(String matchedQualification:postQualifications)
				            {
				            	if(!remainedQualifications.contains(matchedQualification))
				            	{
				            		matchedQualifications.add(matchedQualification);
				            	}
				            }
				            post.setMatchedQulifications(matchedQualifications);
				            post.setRemainedSkills(remainedSkills);
			                post.setRemainedQualifications(remainedQualifications);
				            if(!matchedSkills.isEmpty())
				            {
				            	if((matchedSkills.size()+matchedQualifications.size())<((postSkills.size()+postQualifications.size())/2))
					            {
				            		  post.setState(0);
				            			post.setStatuseCode("Not match with : ("+(int)((Double.valueOf((matchedSkills.size()+matchedQualifications.size()))/Double.valueOf((postSkills.size()+postQualifications.size())))*100)+"%)");
							            System.out.println("Post Skills : "+postSkills+" ::: jobSeeker Skills : "+jobSeekerSkills);
						                System.out.println("REMAINED SKILLS FOR POST LIST : "+remainedSkills);
//							            System.out.println("Remained Skills From last Method : "+applicationService.remainedSkills);
					            }else 
					            {
					            	  post.setState(1);   
						            	post.setStatuseCode("Matched with : ("+(int)((Double.valueOf((matchedSkills.size()+matchedQualifications.size()))/Double.valueOf((postSkills.size()+postQualifications.size())))*100)+"%)");
							            System.out.println("Post Skills : "+postSkills+" ::: jobSeeker Skills : "+jobSeekerSkills);
						                System.out.println("REMAINED SKILLS FOR POST LIST : "+remainedSkills);
//							            System.out.println("Remained Skills From last Method : "+applicationService.remainedSkills);
					            }
				            	System.out.println("Matched Skills : "+matchedSkills);
					            return new postScore(post, score);
				            }else 
				            {
				            	postDTO post2=new postDTO();
				            	
				            	return new postScore(post2, score);
				            }
				            
				        })// Update the comparator to prioritize posts with more matched skills and fewer remaining skills
					    .sorted(Comparator.comparingInt(postScore -> {
					        postDTO post = ((postScore) postScore).getPost();
					        int matchedSkillsCount = post.getMatchedSkills().size() + post.getMatchedQulifications().size();
					        int remainedSkillsCount = post.getRemainedSkills().size() + post.getRemainedQualifications().size();
					        double ratio = (double) matchedSkillsCount / (matchedSkillsCount + remainedSkillsCount);
					        				// this multiply for retaining precision
					        System.out.println("Ratioooooo :::: "+(-ratio * 10000));
					        return  (int) (-ratio * 10000);  // The negative for getting the higher matched skills and qualifications and lower missedSkills Posts first
					    }))
					          
					    .map(postScore -> ((postScore) postScore).getPost()) // Extract the postDTO from postScore
					    .collect(Collectors.toList());
				
				Map<Long, postDTO> uniquePostsMap = new LinkedHashMap<>();
				sortedPosts.forEach(post -> uniquePostsMap.put(post.getId(), post));
				// Convert the map back to a list
				List<postDTO> uniquePostsList = new ArrayList<>(uniquePostsMap.values());
				
				return uniquePostsList;
	     }else 
	     {
	    	 return Collections.emptyList();
	     }
	 }
	 /**
	  * @author BOBO
	  * The sorted method the score to sort the postScore objects. 
	  * Higher values (indicating more matched skills and fewer remaining skills) will come first due to the negative sign and the nature of the ratio.
	  */
	 @Override
	 public List<postDTO> getPostsFromSearchAndJobSeekerSkills(Long jobSeekerId,List<postDTO> postDtoList) {
	     // Fetch job seeker and their skills
	     jobSeeker jobSeeker = findById(jobSeekerId);
	     if(jobSeeker!=null)
	     {
	    	  Set<String> jobSeekerSkills = 
	 	    		 jobSeekerSkillServiceI.findByJobSeekerId(jobSeeker.getId())
	 	             .stream().map(this::convertJobSeekerSkillToDto)
	 	             .map(jobSeekerSkillDTO::getSkillName)
	 	             .collect(Collectors.toSet());
				System.out.println("Job Seeker Skills : "+jobSeekerSkills);
				
				Set<String> jobSeekerQualifications=
						jobSeekerQualificationServiceI.findByJobSeekerId(jobSeeker.getId())
						.stream().map(this::convertJobSeekerQualificationToDto)
						.map(jobSeekerQualificationDTO::getQualificationName)
						.collect(Collectors.toSet());
				
				System.out.println("Job Seeker Qualifications : "+jobSeekerQualifications);
				
				
				Set<postDTO> posts =postDtoList.stream()
						.filter(post -> checkForApplyPostDto(jobSeekerId, post))
						.collect(Collectors.toSet());
				
				if (posts.isEmpty()) {
					return Collections.emptyList();
				}
				
				// Calculate and sort post scores
				List<postDTO> sortedPosts = posts.stream()				       
						.map(post -> {
				            Set<String> postSkills = post.getSkills().stream()
				                    .collect(Collectors.toSet());
				            Set<String> postQualifications = post.getQualifications().stream()
				                    .collect(Collectors.toSet());

				            int score = calculateScore(
				            		new ArrayList<String>(postQualifications),
				            		new ArrayList<>(postSkills),
				            		new ArrayList<String>(jobSeekerQualifications),
				            		new ArrayList<>(jobSeekerSkills));
				            
				            List<String>remainedSkills=applicationService
				            							.returningRemainedSkillsForListOfPosts(
				            									new ArrayList<String>(postSkills),
				            									new ArrayList<String>(jobSeekerSkills));
				            
				            List<String> remainedQualifications=applicationService
				            									.returningRemainedQualificationsForPostList(
				            											new ArrayList<String>(postQualifications),
				            											new ArrayList<String>(jobSeekerQualifications));
				            List<String> matchedSkills=new ArrayList<String>();
				            for(String matchedSkill:postSkills)
				            {
				            	if(!remainedSkills.contains(matchedSkill))
				            	{
				            		matchedSkills.add(matchedSkill);
				            	}
				            }
				            post.setMatchedSkills(matchedSkills);
				            List<String> matchedQualifications=new ArrayList<String>();
				            for(String matchedQualification:postQualifications)
				            {
				            	if(!remainedQualifications.contains(matchedQualification))
				            	{
				            		matchedQualifications.add(matchedQualification);
				            	}
				            }
				            post.setMatchedQulifications(matchedQualifications);
				            post.setRemainedSkills(remainedSkills);
			                post.setRemainedQualifications(remainedQualifications);
				            if(!matchedSkills.isEmpty())
				            {
				            	if((matchedSkills.size()+matchedQualifications.size())<((postSkills.size()+postQualifications.size())/2))
					            {
				            		  post.setState(0);
				            		  post.setStatuseCode("Not match with : ("+(int)((Double.valueOf((matchedSkills.size()+matchedQualifications.size()))/Double.valueOf((postSkills.size()+postQualifications.size())))*100)+"%)");

							            System.out.println("Post Skills : "+postSkills+" ::: jobSeeker Skills : "+jobSeekerSkills);
						                System.out.println("REMAINED SKILLS FOR POST LIST : "+remainedSkills);
//							            System.out.println("Remained Skills From last Method : "+applicationService.remainedSkills);
					            }else 
					            {
					            	  post.setState(1);   
				            			post.setStatuseCode("Matched with : ("+(int)((Double.valueOf((matchedSkills.size()+matchedQualifications.size()))/Double.valueOf((postSkills.size()+postQualifications.size())))*100)+"%)");

							            System.out.println("Post Skills : "+postSkills+" ::: jobSeeker Skills : "+jobSeekerSkills);
						                System.out.println("REMAINED SKILLS FOR POST LIST : "+remainedSkills);
//							            System.out.println("Remained Skills From last Method : "+applicationService.remainedSkills);
					            }
				            	System.out.println("Matched Skills : "+matchedSkills);
					            return new postScore(post, score);
				            }else 
				            {				            	
				            	return new postScore(post, score);
				            }
				            
				        })// Update the comparator to prioritize posts with more matched skills and fewer remaining skills
					    .sorted(Comparator.comparingInt(postScore -> {
					        postDTO post = ((postScore) postScore).getPost();
					        int matchedSkillsCount = post.getMatchedSkills().size() + post.getMatchedQulifications().size();
					        int remainedSkillsCount = post.getRemainedSkills().size() + post.getRemainedQualifications().size();
					        double ratio = (double) matchedSkillsCount / (matchedSkillsCount + remainedSkillsCount);
					        				// this multiply for retaining precision
					        System.out.println("Ratioooooo :::: "+(-ratio * 10000));
					        return  (int) (-ratio * 10000);  // The negative for getting the higher matched skills and qualifications and lower missedSkills Posts first
					    }))
					    .map(postScore -> ((postScore) postScore).getPost()) // Extract the postDTO from postScore
					    .collect(Collectors.toList());
				Map<Long, postDTO> uniquePostsMap = new LinkedHashMap<>();
				sortedPosts.forEach(post -> uniquePostsMap.put(post.getId(), post));
				// Convert the map back to a list
				List<postDTO> uniquePostsList = new ArrayList<>(uniquePostsMap.values());
				
				return uniquePostsList;
	     }else 
	     {
	    	 return Collections.emptyList();
	     }
	 }
	 
	 ////  return Type  :  : backEndResponse
	 public backEndResponse callFlaskAPI(String userSkills) {
		    // Define the URL of your Flask API endpoint
		    String response = "";
		    String apiUrl = "http://localhost:5000/match-skills";
		    backEndResponse backEndResponse=new backEndResponse();
		    // Create a RestTemplate instance
		    RestTemplate restTemplate = new RestTemplate();

		    // Prepare the request body
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);

		    // Create the request body as a map
		    Map<String, String> requestBodyMap = new HashMap<>();
		    requestBodyMap.put("skills", userSkills);

		    // Convert the map to a JSON string
		    ObjectMapper objectMapper = new ObjectMapper();
		    String requestBody = "";
		    try {
		        requestBody = objectMapper.writeValueAsString(requestBodyMap);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    // Log the request body
		    System.out.println("Request Body: " + requestBody);

		    // Create the request entity with headers and body
		    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		    // Make a POST request to the Flask API endpoint
		    ResponseEntity<String> responseEntity = null;
		    try {
		        responseEntity = restTemplate.exchange(
		            apiUrl,
		            HttpMethod.POST,
		            requestEntity,
		            String.class
		        );
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    // Handle the response
		    if (responseEntity != null) {
		        HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
		        if (statusCode == HttpStatus.OK) {
		            String responseBody = responseEntity.getBody();
		            response = responseEntity.getBody();
		            // Process the JSON response from the Flask API
		            System.out.println("Response from Flask API: " + responseBody);

		            // Parse the JSON response into a list of JobRecommendation objects
		            try {
		                List<pythonApiResponse> jobRecommendations = objectMapper.readValue(
		                    responseBody, new TypeReference<List<pythonApiResponse>>() {}
		                );

		               
		                List<pythonApiResponse> modelResponseForBackEndObject=new ArrayList<pythonApiResponse>();
		                List<postDTO> postDtos=new ArrayList<postDTO>();
		                // Access specific attributes
		                for (pythonApiResponse recommendation : jobRecommendations) {
		                    System.out.println("Job Title: " + recommendation.getJobTitle());
		                    System.out.println("Skills: " + recommendation.getSkills());
		                    if(recommendation.getRowType().equals("Train"))
		                    {
		                    	modelResponseForBackEndObject.add(recommendation);
		                    }else
		                    {
		                    	
		                    	System.out.println("The else reached with post Id : "+recommendation.getPostId());
		                    	Optional<Post> postOption=postService.findById(Long.valueOf(recommendation.getPostId()));
		                    	if(postOption.isPresent())
		                    	{
		                    		
		                    		postDTO postRetreived=postMapper.mapPostTODTO(postOption.get());
		                    		postDtos.add(postRetreived);
		                    	}
		                    	
		                    }
		                    // Access other attributes similarly   // here will check if the row is in site or not and handle all senarios and return an object including List <pythonApiResponse> for Train and List<postDTO> for posts
		                }
		                
		                backEndResponse.setPostDtosResponse(postDtos);
		                backEndResponse.setPythonResponses(modelResponseForBackEndObject);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        } else {
		            // Handle other status codes if needed
		            System.err.println("Failed to call Flask API. Status code: " + statusCode);
		        }
		    }
		    return backEndResponse;
		}
//	 @Override
//	 public List<postDTO> getPostsWithSkillsOnPublic(Long jobSeekerId) {
//	     jobSeeker jobSeeker = findById(jobSeekerId);
//	     if (jobSeeker == null) {
//	         return Collections.emptyList();
//	     }
//
//	     Set<String> jobSeekerSkills = jobSeekerSkillServiceI.findByJobSeekerId(jobSeekerId)
//	             .stream().map(this::convertJobSeekerSkillToDto) 
//	             .map(jobSeekerSkillDTO::getSkillName)
//	             .collect(Collectors.toSet());
//
//	     Set<String> jobSeekerQualifications = jobSeekerQualificationServiceI.findByJobSeekerId(jobSeekerId)
//	             .stream().map(this::convertJobSeekerQualificationToDto)
//	             .map(jobSeekerQualificationDTO::getQualificationName)
//	             .collect(Collectors.toSet());
//
//	     List<postDTO> posts = jobSeekerSkills.stream()
//	             .flatMap(skill -> postService.findByTitle(skill).stream())
//	             .map(this::convertPostToDto)
//	             .collect(Collectors.toList());
//
//	     Map<Long, postDTO> uniquePostsMap = new LinkedHashMap<>();
//
//	     for (postDTO post : posts) {
//	         Set<String> postSkills = new HashSet<>(post.getPostField().getSkills());
//	         Set<String> postQualifications = new HashSet<>(post.getPostField().getQualifications());
//
//	         List<String> remainedSkills = applicationService.returningRemainedSkillsForListOfPosts(new ArrayList<>(postSkills), new ArrayList<>(jobSeekerSkills));
//	         List<String> remainedQualifications = applicationService.returningRemainedQualificationsForPostList(new ArrayList<>(postQualifications), new ArrayList<>(jobSeekerQualifications));
//
//	         List<String> matchedSkills = new ArrayList<>(postSkills);
//	         matchedSkills.retainAll(jobSeekerSkills);
//
//	         List<String> matchedQualifications = new ArrayList<>(postQualifications);
//	         matchedQualifications.retainAll(jobSeekerQualifications);
//
//	         if (!matchedSkills.isEmpty()) {
//	             if ((matchedSkills.size() + matchedQualifications.size()) < ((postSkills.size() + postQualifications.size()) / 2)) {
//	                 post.setState(0);
//	             } else {
//	                 post.setState(1);
//	             }
//	         }
//
//	         post.setMatchedSkills(new ArrayList<>(matchedSkills));
//	         post.setMatchedQulifications(new ArrayList<>(matchedQualifications));
//	         post.setRemainedSkills(new ArrayList<>(remainedSkills));
//	         post.setRemainedQualifications(new ArrayList<>(remainedQualifications));
//
//	         uniquePostsMap.put(post.getId(), post);
//	     }
//
//	     List<postDTO> uniquePostsList = new ArrayList<>(uniquePostsMap.values());
//
//	     uniquePostsList.sort(Comparator.comparingInt(post -> {
//	         int score = calculateScore(post.getRemainedQualifications(), post.getRemainedSkills(), new ArrayList<>(jobSeekerQualifications), new ArrayList<>(jobSeekerSkills));
//	         return -score;
//	     }));
//
//	     return uniquePostsList;
//	 }
	  
	/*
	 *  @Override
	 public List<Post> getPostsWithSkillsOnPublic(Long jobSeekerId) {
	     // Fetch job seeker and their skills
	     jobSeeker jobSeeker = findById(jobSeekerId);
	     if(jobSeeker!=null)
	     {
	    	  List<jobSeekerSkillDTO> jobSeekerSkillDtoList =jobSeekerSkillServiceI
						.findByJobSeekerId(jobSeeker.getId())
						.stream()
						.map(this::convertJobSeekerSkillToDto)
						.collect(Collectors.toList());

				// Extract skill names
				Set<String> jobSeekerSkills = jobSeekerSkillDtoList.stream()
				.map(jobSeekerSkillDTO::getSkillName)
				.collect(Collectors.toSet());
				
				// Fetch posts based on job seeker skills
				Set<Post> posts = jobSeekerSkills.stream()
				.flatMap(skill -> postService.findByTitle(skill).stream())
				.collect(Collectors.toSet());
				
				if (posts.isEmpty()) {
				return Collections.emptyList();
				}
				
				// Calculate and sort post scores
				List<Post> sortedPosts = posts.stream()
				.map(post -> {
				postDTO postDto = convertPostToDto(post);
				Set<String> postSkills = postDto.getPostField().getSkills().stream()
				.collect(Collectors.toSet());
				
				int score = calculateScore(new ArrayList<String>(postSkills), new ArrayList<String>(jobSeekerSkills));
				return new postScore(post, score);
				})
				.sorted(Comparator.comparingInt(postScore::getScore).reversed())
				.map(postScore::getPost)
				.collect(Collectors.toList());
				
				return sortedPosts;

	     }else 
	     {
	    	 return Collections.emptyList();
	     }
	 }

	 */
//		 /**
//			 * 
//			 * @Author BOB
//			 * @return List <Post> that contains job seeker skills order by descending
//			 */
//			public List<Post> getPostsWithSkillsOnPublic(Long jobSeekerId) {
//		        
//				jobSeeker jobSeeker=findById(jobSeekerId);
//				List<User> followings=followService.getFollowingById(jobSeeker); // order by the followings of post employer or company 
//				
//				List<jobSeekerSkill> jobSeekerSkills=jobSeekerSkillServiceI.findByJobSeekerId(jobSeeker.getId());
//				List<jobSeekerSkillDTO>jobSeekerSkillDtoList=jobSeekerSkills.stream().map(this::convertJobSeekerSkillToDto).collect(Collectors.toList());
//				List<String> jobSeekerSkillsInStrings=new ArrayList<String>();
//				for(jobSeekerSkillDTO jobSeekerSkill:jobSeekerSkillDtoList)
//				{
//					jobSeekerSkillsInStrings.add(jobSeekerSkill.getSkillName());
//				} 
//				
//				Set <Post> posts = new HashSet<Post>();
//				List<postScore> postScores=new ArrayList<postScore>();
//				System.out.println("jobSeeker Skills : "+jobSeekerSkills);
//				
//				for (String skill:jobSeekerSkillsInStrings) {
//					posts.addAll(postService.findByTitle(skill));// Find by the title contains any word of jobSeekerSkills
//				} 
//				
//				if(posts!=null)
//				{
//					System.out.println("size : "+posts.size());
//					for (Post post:posts) {
//						postDTO postDto=convertPostToDto(post);
//						 List<String> postSkills=new ArrayList<String>();
//							for(postFieldDTO postF:postDto.getPostFieldsDto())
//							{
//								for(String skill:postF.getSkills())
//								{
//									if(postSkills.contains(skill))
//									{
//										
//									}else 
//									{
//										postSkills.add(skill);
//										System.out.println("skill : "+skill);
//									}
//								}	
//							}
////					    Set<String> postSkillsSet=new HashSet<String>(postSkills);
////					    postSkills=new ArrayList<String>(postSkillsSet);
//						int score =calculateScore(postSkills, jobSeekerSkillsInStrings);
//						System.out.println("calculated Score : "+score);
//						System.out.println("Post skills : "+postSkills);
//						postScores.add(new postScore(post, score));
//					}
//			        
//			        // Sort applications based on score in descending order
//			        Collections.sort(postScores, Comparator.comparingInt(postScore::getScore).reversed());
//			        
//			        // Extract applications from ApplicationScore objects
//			        List<Post> sortedPosts = new ArrayList<>();
//			        for (postScore postScore : postScores) {
//			        	sortedPosts.add(postScore.getPost());
//			        }
//			        
//			        return sortedPosts;
//				}else 
//				{
//					return null;
//				}
//				
//		    }
//			
			/**
			 * 
			 * @param postSkillSet
			 * @param applicationSkills
			 * @return calculate the score of matching between list of skills and another list of skills  
			 */
			public static int calculateScore(List<String> postQualifications,List<String> postSkillSet,List<String>applicationQualifications, List<String> applicationSkills) {   
				Map<String, Integer> postSkillCount = new HashMap<>();
			    Map<String, Integer> applicationSkillCount = new HashMap<>();
			    
			    Map<String, Integer> postQualificationCount = new HashMap<>();
			    Map<String, Integer> applicationsQualificationCount = new HashMap<>();
			    
			    // Count occurrences of each skill in post skills
			    for (String skill : postSkillSet) {
			        postSkillCount.put(skill.toLowerCase(), postSkillCount.getOrDefault(skill.toLowerCase(), 0) + 1);
			    }
			    
			    for (String qual : postQualifications) {
			        postQualificationCount.put(qual.toLowerCase(), postQualificationCount.getOrDefault(qual.toLowerCase(), 0) + 1);
			    }
			    
			    for (String qual : applicationQualifications) {
			    	applicationsQualificationCount.put(qual.toLowerCase(), applicationsQualificationCount.getOrDefault(qual.toLowerCase(), 0) + 1);
			    }
	
			    // Count occurrences of each skill in application skills
			    for (String skill : applicationSkills) {
			        applicationSkillCount.put(skill.toLowerCase(), applicationSkillCount.getOrDefault(skill.toLowerCase(), 0) + 1);
			    }
	
			    int score = 0;
			    // Calculate score based on the minimum occurrences of each skill
			    for (Map.Entry<String, Integer> entry : applicationSkillCount.entrySet()) {
			        String skill = entry.getKey();
			        int applicationCount = entry.getValue();
			        int postCount = postSkillCount.getOrDefault(skill, 0);
			        score += Math.min(applicationCount, postCount);
			    }
			    for (Map.Entry<String, Integer> entry : applicationsQualificationCount.entrySet()) {
			        String skill = entry.getKey();
			        int applicationCount = entry.getValue();
			        int postCount = postQualificationCount.getOrDefault(skill, 0);
			        score += Math.min(applicationCount, postCount);
			    }
			    System.out.println("Score Of Qualifications With Skills : "+score);
			    return score;
			}
	
		 /**
			 * 
			 * @Author BOB
			 * @Function Helper Class for store the score of each Application
			 */
			private static class postScore {
		        private postDTO post;
		        private int score;
		        
		        public postScore(postDTO post, int score) {
		            this.post = post;
		            this.score = score;
		        }
		        
		        public postDTO getPost() {
		            return post;
		        }
		        
		        public int getScore() {
		            return score;
		        }
		    }

		@Override
		public Optional<jobSeeker> findByEmail(String email) {
			
			return jobSeekerRepository.findByEmail(email);
		}

		@Transactional
		@Override
		public String insertJobSeekerSkillsAndQualificationsOptimized(saveSkillsRequest skillsRequest) {
		    try {
		        // Process skills
		        processSkills(skillsRequest.getUserId(), new HashSet<String>(skillsRequest.getSkills()));

		        //Process qualifications
		        processQualifications(skillsRequest.getUserId(),new HashSet<String>(skillsRequest.getQualifications()));

		        return "inserted";
		    } catch (Exception e) {
		        return "Failed to insert JobSeeker skills and qualifications.";
		    }
		}

		
		/**
		 * 
		 * @param userId
		 * @param skillDTOs
		 * @return void processing of skills and inserting skills within the db
		 */
		private void processSkills(Long userId, Set<String> skillDTOs) {
		 try {
			    List<jobSeekerSkillDTO>jobSeekerSkillsToInsert=new ArrayList<jobSeekerSkillDTO>();
			    List<jobSeekerSkillDTO> oldJobSeekerSkills=
			    		jobSeekerSkillServiceI
			    		.findByJobSeekerId(userId)
			    		.stream()
			    		.map(
			    				jobSeekerSkillMapper::mapJobSeekerSkillToDTO
			    			)
			    		.collect(Collectors.toList());
			    
			    for(jobSeekerSkillDTO jobSeekerSkilldto:oldJobSeekerSkills)
			    {
			    	if(skillDTOs.stream().anyMatch(str->str.equalsIgnoreCase(jobSeekerSkilldto.getSkillName())))
			    	{
			    		skillDTOs.remove(jobSeekerSkilldto.getSkillName());
			    	}
			    }
			    
			    for (String skillDTO : skillDTOs) {
			        
			    		jobSeekerSkillDTO jobSeekerSkillToInsert = new jobSeekerSkillDTO();
				    	Skill skill = skillServiceI.findByName(skillDTO);
				    	Skill insertedSkill=skill;
				    	if (skill == null) {
				            skill = new Skill();
				            skill.setSkillName(skillDTO);
				           insertedSkill=skillServiceI.insertForJobSeekerOperation(skill);		
				        }
				    	
				    	jobSeekerSkillToInsert.setSkill(insertedSkill); // Also here.
				        jobSeekerSkillToInsert.setJobSeekerId(userId); // initialize jobSeeker object with jobSeeker id that at the dto
				        jobSeekerSkillsToInsert.add(jobSeekerSkillToInsert);		
				       
			    	
			    }

			    List <jobSeekerSkill> jobSeekerSkillsList=jobSeekerSkillsToInsert.stream().map(this::convertFromDtoToJobSeekerSkill).collect(Collectors.toList());
			    if(!jobSeekerSkillsList.isEmpty())
			    {
			        jobSeekerSkillServiceI.insertAll(jobSeekerSkillsList);
			    }
		} catch (Exception e) {
			System.out.println("Errorrrrrrrr : "+e);
		}
		}

		
		private void processQualifications(Long userId,Set<String> qualificationDTOs) {
			try {
			List<jobSeekerQualificationDTO>oldJobSeekerQualifications=jobSeekerQualificationServiceI.findByJobSeekerId(userId)
					.stream().map(jobSeekerQualificationMapper::mapJobSeekerQualificationToDTO)
					.collect(Collectors.toList());
		    List<jobSeekerQualificationDTO>jobSeekerQualificationsToInsert=new ArrayList<jobSeekerQualificationDTO>();
		    for(jobSeekerQualificationDTO jobSeekerQualificationdto:oldJobSeekerQualifications)
		    {
		    	if(qualificationDTOs.stream().anyMatch(str->str.equalsIgnoreCase(jobSeekerQualificationdto.getQualificationName())))
		    	{
		    		qualificationDTOs.remove(jobSeekerQualificationdto.getQualificationName());
		    	}
		    }
		    
		    for (String qualificationDto : qualificationDTOs) {
		        jobSeekerQualificationDTO jobSeekerQualificationToInsert = new jobSeekerQualificationDTO();
		    	Qualification qualification = qualificationServiceI.findByName(qualificationDto);
		    	Qualification insertedQualification=qualification; //initialized if qualification exists for insert
		        if (qualification == null) {
		        	qualification = new Qualification();
		        	qualification.setQualificationName(qualificationDto);
		        	insertedQualification=qualificationServiceI.insert(qualification);
		        }
		        
		        jobSeekerQualificationToInsert.setQualification(insertedQualification); // Also here.				        
		        jobSeekerQualificationToInsert.setJobSeekerId(userId); // initialize jobSeeker object with jobSeeker id that at the dto
		        jobSeekerQualificationsToInsert.add(jobSeekerQualificationToInsert);
		        
		        
		    }
		    List <jobSeekerQualification> jobSeekerQualificationList=
		    		jobSeekerQualificationsToInsert
		    		.stream()
		    		.map(this::convertFromDtoToJobSeekerQualification)
		    		.collect(Collectors.toList());
		    
		    if(!jobSeekerQualificationList.isEmpty())
		    {
		        jobSeekerQualificationServiceI.insertAll(jobSeekerQualificationList);
		    }
		} catch (Exception e) {
			System.out.println("Errorrrrrrrr : "+e);
		}
		}
		

		private jobSeekerSkill convertFromDtoToJobSeekerSkill(jobSeekerSkillDTO dto)
		{
			return jobSeekerSkillMapper.mapDtoToJobSeekerSkillForInsert(dto);
		}
		
		private jobSeekerQualification convertFromDtoToJobSeekerQualification(jobSeekerQualificationDTO dto)
		{
			return jobSeekerQualificationMapper.mapDtoToJobSeekerQualificationForInsert(dto);
		}
		
		@Transactional
		@Override
		public String insertJobSeekerSkillsAndQualifications(saveSkillsRequest skillsRequest) {
			
//			String theReturn = null;
//			try {
//				List<Skill> skillsToInsert =new ArrayList<Skill>();
//				Map<String,Skill> existingSkills=new HashMap<String, Skill>();
//				Set<jobSeekerSkillDTO> newSkills=new HashSet<jobSeekerSkillDTO>(skillsRequest.getJobSeekerSkillDtoList());
//				
//				for(jobSeekerSkillDTO jobSeekerSkill:newSkills)
//				{
//			    	Skill skillSearch= skillServiceI.findByName(jobSeekerSkill.getSkillName());
//			    	if(skillSearch!=null)
//			    	{
//			    		existingSkills.put(skillSearch.getSkillName(), skillSearch); //putting the skill name and skill object for the existing skills hashMap
//			    	}else 
//			    	{
//			    		Skill skillObject=new Skill();
//			    		skillObject.setSkillName(jobSeekerSkill.getSkillName());
//			    		skillsToInsert.add(skillObject);
//			    	}
//				}
//				
//				skillServiceI.insertAll(skillsToInsert);
//				
////				List<Skill> allSkills=new ArrayList<Skill>(existingSkills.values());
////				allSkills.addAll(skillsToInsert);
//				
//				List<jobSeekerSkill>jobSeekerSkills=new ArrayList<jobSeekerSkill>();
//				for(jobSeekerSkillDTO skill:newSkills)
//				{
//					jobSeekerSkill jobSeekerSkillToInsert=new jobSeekerSkill();
//					jobSeekerSkillToInsert.setSkill(skill.getSkill());
//					jobSeekerSkillToInsert.setJobSeeker(skill.getJobSeeker());
//					jobSeekerSkillToInsert.setSkillDegree(skill.getSkillDegree());
//					jobSeekerSkills.add(jobSeekerSkillToInsert);
//				}
//				jobSeekerSkillServiceI.insertAll(jobSeekerSkills);
//				theReturn="jobSeeker skills inserted.";
//			} catch (Exception e) {
//				return theReturn;
//				
//			}
//			try {
//				List<qualification> qualificationsToInsert =new ArrayList<qualification>();
//				Map<String,qualification> existingQualification=new HashMap<String, qualification>();
//				Set<jobSeekerQualificationDTO> newQualification=new HashSet<jobSeekerQualificationDTO>(skillsRequest.getQualificationDtoList());
//				
//				for(jobSeekerQualificationDTO jobSeekerQual:newQualification)
//				{
//			    	qualification qualificationSearch= qualificationServiceI.findByName(jobSeekerQual.getQualificationName());
//			    	if(qualificationSearch!=null)
//			    	{
//			    		existingQualification.put(qualificationSearch.getQualificationName(), qualificationSearch); //putting the skill name and skill object for the existing skills hashMap
//			    	}else 
//			    	{
//			    		qualification qualificationObject=new qualification();
//			    		qualificationObject.setQualificationName(qualificationSearch.getQualificationName());
//			    		qualificationsToInsert.add(qualificationObject);
//			    	}
//				}
//				
//				qualificationServiceI.insertAll(qualificationsToInsert);
//				
////				List<Skill> allSkills=new ArrayList<Skill>(existingSkills.values());
////				allSkills.addAll(skillsToInsert);
//				
//				List<jobSeekerQualification>jobSeekerQualificationsToInsert=new ArrayList<jobSeekerQualification>();
//				for(jobSeekerQualificationDTO qualification:newQualification)
//				{
//					jobSeekerQualification jobSeekerQualificationToInsert=new jobSeekerQualification();
//					jobSeekerQualificationToInsert.setQualification(qualification.getQualification());
//					jobSeekerQualificationToInsert.setJobSeeker(qualification.getJobSeeker());
//					jobSeekerQualificationToInsert.setQualificationDegree(qualification.getQualificationDegree());
//					jobSeekerQualificationsToInsert.add(jobSeekerQualificationToInsert);
//				}
//				jobSeekerQualificationServiceI.insertAll(jobSeekerQualificationsToInsert);
//				theReturn+=" and qualifications inserted.";
//			} catch (Exception e) {
//				return theReturn;
//				
//			}
//		
//			return theReturn;
			return "";
	}

		@Override
		public skillsAndQualificationsRequest getJobSeekerSkillsAndQualificaitonsByJobSeekerId(Long jobSeekerId) {
			skillsAndQualificationsRequest request=new skillsAndQualificationsRequest();
			List <jobSeekerSkillDTO> returnedSkillList= jobSeekerSkillServiceI
															.findByJobSeekerId(jobSeekerId)
															.stream()
															.map(jobSeekerSkillMapper::mapJobSeekerSkillToDTO)
															.collect(Collectors.toList());
			List<jobSeekerQualificationDTO> jobSeekerQualificationsList=jobSeekerQualificationServiceI
																			.findByJobSeekerId(jobSeekerId)
																			.stream()
																			.map(jobSeekerQualificationMapper::mapJobSeekerQualificationToDTO)
																			.collect(Collectors.toList());
			request.setJobSeekerId(jobSeekerId);
			request.setJobSeekerQualificationsList(jobSeekerQualificationsList);
			request.setJobSeekerSkillList(returnedSkillList);
			return request;
		}
		 
		
}
package com.example.JOBSHOP.JOBSHOP.jobSeeker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.applicationService;
import com.example.JOBSHOP.JOBSHOP.DTOImpl.DTOToEntityMapper;
import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;
import com.example.JOBSHOP.JOBSHOP.Follow.followService;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.postRepository;
import com.example.JOBSHOP.JOBSHOP.jobSeekerProfile.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.jobSeekerProfile.jobSeekerProfileService;

@Service
public class jobSeekerService{

	
	 @Autowired
	 private jobSeekerRepository jobSeekerRepository;
	 
	 @Autowired 
	 private jobSeekerProfileService jobSeekerProfileService;
	 
	 @Autowired
	 private followService followService;
	 
	 @Autowired
	 private applicationService applicationService;
	 
	 @Autowired
	 private postRepository postService;
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
		
		public jobSeeker update(jobSeeker t)
		{
			if(getReferenceById(t.getId())!=null)
			{
//				logInfo("Employer Updated Successfully");
				return jobSeekerRepository.save(t);
			}else 
			{
//				logError("EmployerNotFound");
				return null;
				
			}
		}
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
		 jobSeekerProfile jobSeekerProfile=new jobSeekerProfile();
		 jobSeekerProfile.setJobSeeker(jobSeeker); 
		 jobSeeker jobSeekersaved=jobSeekerRepository.save(jobSeeker);
		 jobSeekerProfileService.insert(jobSeekerProfile);
		 return jobSeekersaved;
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
	 
	 /**
	 * 
	 * @author BOB
	 * @Function JobSeeker apply for Specific Opened posted job
	 */
	 public List<String> applyForPost(applicationDTO app)
	 {
		 Application apps=DTOToEntityMapper.mapDTOToApplication(app);
		 Post post=postService.findById(app.getPost().getId()).get();
		 if(applicationService.returningTheRemainedSkills(post.getSkills(),app.getSkills()).equals("No"))
		 {
			 return applicationService.remainedSkills;
		 }else 
		 {
			 applicationService.insert(apps);
			 return applicationService.remainedSkills;
		 }
	 }
	 
	 private postDTO mapPostToDTO(Post post)
	 {
		 return entityToDTOMapper.mapPostToDTO(post);
	 }
	 
	 public List<String> findSkillsById(Long id)
	 {
		 return jobSeekerRepository.findSkillsById(id);
	 }
	 /**
	 * 
	 * @author BOB
	 * @Function jobSeeker update his picture
	 */
	 public jobSeeker insertPicture(Long id,byte[] picture)
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
	 
	 public jobSeekerProfile findJobSeekerProfileWithjobSeekerID(Long jobSeekerId)
	 {
		return jobSeekerProfileService.findByJobSeeker_id(jobSeekerId).get();
	 } 
		 /**
			 * 
			 * @Author BOB
			 * @return List <Post> that contains job seeker skills order by descending
			 */
			public List<Post> getPostsWithSkillsOnPublic(Long jobSeekerId) {
		        
				jobSeeker jobSeeker=findById(jobSeekerId);
			
				List<String> jobSeekerSkills=jobSeeker.getSkills();
				Set <Post> posts = new HashSet<Post>();
				List<postScore> postScores=new ArrayList<postScore>();
				System.out.println("jobSeeker Skills : "+jobSeekerSkills);
				
				for (String skill:jobSeekerSkills) {
					posts.addAll(postService.findByTitle(skill));
				} 
				
				if(posts!=null)
				{
					System.out.println("size : "+posts.size());
					for (Post post:posts) {
						
						int score =calculateScore(post.getSkills(), jobSeekerSkills);
						System.out.println("calculated Score : "+score);
						System.out.println("Post skills : "+post.getSkills());
						postScores.add(new postScore(post, score));
					}
			        
			        // Sort applications based on score in descending order
			        Collections.sort(postScores, Comparator.comparingInt(postScore::getScore).reversed());
			        
			        // Extract applications from ApplicationScore objects
			        List<Post> sortedPosts = new ArrayList<>();
			        for (postScore postScore : postScores) {
			        	sortedPosts.add(postScore.getPost());
			        }
			        
			        return sortedPosts;
				}else 
				{
					return null;
				}
				
		    }
			
			private static int calculateScore(List<String> postSkillSet, List<String> applicationSkills) {   
				Map<String, Integer> postSkillCount = new HashMap<>();
			    Map<String, Integer> applicationSkillCount = new HashMap<>();
	
			    // Count occurrences of each skill in post skills
			    for (String skill : postSkillSet) {
			        postSkillCount.put(skill.toLowerCase(), postSkillCount.getOrDefault(skill.toLowerCase(), 0) + 1);
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
			    System.out.println("Score : "+score);
			    return score;
			}
	
		 /**
			 * 
			 * @Author BOB
			 * @Function Helper Class for store the score of each Application
			 */
			private static class postScore {
		        private Post post;
		        private int score;
		        
		        public postScore(Post post, int score) {
		            this.post = post;
		            this.score = score;
		        }
		        
		        public Post getPost() {
		            return post;
		        }
		        
		        public int getScore() {
		            return score;
		        }
		    }
		 
}
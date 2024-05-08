package com.example.JOBSHOP.JOBSHOP.Application.service;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationMapper;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.postField.DTO.postFieldDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.applicationReturnedSkillsAndQualifications;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerService;

import jakarta.transaction.Transactional;

@Service
public class applicationServiceImpl implements applicationServiceInerface{

	@Autowired
	private applicationRepository applicationRepository;

	public List<String> remainedSkills=new ArrayList<String>();
	public List<String> remainedQualifications=new ArrayList<String>();
	public applicationReturnedSkillsAndQualifications applicationReturnedSkillsAndQualifications;
	
	public applicationReturnedSkillsAndQualifications getApplicationReturnedSkillsAndQualifications()
	{
		 applicationReturnedSkillsAndQualifications applicationReturnedSkillsAndQualifications=new applicationReturnedSkillsAndQualifications();
		 applicationReturnedSkillsAndQualifications.setRemainedQualifications(remainedQualifications);
		 applicationReturnedSkillsAndQualifications.setRemainedSkills(remainedSkills);
		 return applicationReturnedSkillsAndQualifications;
	}
	
	@Override
    public Application getReferenceById(Long id)
	{
		return applicationRepository.getReferenceById(id);
	}
   
	@Override
	public List<Application> findAll()
	{
		return applicationRepository.findAll();
	}
	
	@Override
	public Application insert(Application t)
	{
		
		return applicationRepository.save(t);
	}
	@Override
	public Application findById(Long id)
	{
		Optional<Application> finded=applicationRepository.findById(id);
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
//		applicationRepository.updateEntity(t.getId(),t.getStatusCode()); 
//	}
	
	@Override
	public Application update(Application t)
	{
		if(getReferenceById(t.getId())!=null)
		{
//			logInfo("Employer Updated Successfully");
			return applicationRepository.save(t);
		}else 
		{
//			logError("EmployerNotFound");
			return null;
			
		}
	}
	
	@Override
	public List<Application> insertAll(List<Application> entity)
	{
		return applicationRepository.saveAll(entity);
	}
	
	@Override
	public void deleteById(Long id)
	{
		Application t=findById(id);
		if(t!=null)
		{
			applicationRepository.deleteById(id);
		}
	}
	

	@Override
	public List<Application> findByJobSeekerIdOrderByCreatedDate(Long id) {
		return applicationRepository.findByJobSeekerIdOrderByCreatedDateDesc(id);
	}
	
	
	
	private applicationDTO convertFrongApplicationToDTO(Application app)
	{
		return applicationMapper.mapApplicationToDTO(app);
	}

	@Override
	public List<applicationDTO> getBestApplicationsForPost(postDTO Post) {
        
		List<Application> applications = applicationRepository
		        .findByPostId(Post.getId())
		        .stream()
		        .filter(app -> {
		            // Check if statusCode is not 'Rejected' or is null
		            return app.getStatusCode() == null || app.getStatusCode().equals("Accepted") || !app.getStatusCode().equals("Rejected");
		        })
		        .collect(Collectors.toList()); // Collect the filtered applications into a List


		List<applicationDTO> appDtos=applications.stream().
				map(this::convertFrongApplicationToDTO).collect(Collectors.toList());
		
		List<ApplicationScore> applicationScores = new ArrayList<>();
        
		List<String> postSkills=new ArrayList<String>();
			for(String skill:Post.getSkills())
			{
				postSkills.add(skill);
			}

			List<String> postQualifications=new ArrayList<String>();
			for(String qual:Post.getQualifications())
			{
				postQualifications.add(qual);
			}
		

        // Calculate score for each application
        for (applicationDTO applicationdto : appDtos) {
            int score = jobSeekerService.calculateScore(
            		postQualifications,
            		postSkills,
            		applicationdto.getQualifications(),
            		applicationdto.getSkills());
            
			  List<String>remainedSkills=returningRemainedSkillsForListOfPosts(
											new ArrayList<String>(postSkills),
											new ArrayList<String>(applicationdto.getSkills()));

			List<String> remainedQualifications=returningRemainedQualificationsForPostList(
													new ArrayList<String>(postQualifications),
													new ArrayList<String>(applicationdto.getQualifications()));
			List<String> matchedSkills=new ArrayList<String>();
			
			for(String matchedSkill:postSkills)
			{
				if(!remainedSkills.contains(matchedSkill))
				{
					matchedSkills.add(matchedSkill);
				}
			}
			
			applicationdto.setMatchedSkills(matchedSkills);
			
			List<String> matchedQualifications=new ArrayList<String>();
			
			for(String matchedQualification:postQualifications)
			{
				if(!remainedQualifications.contains(matchedQualification))
				{
					matchedQualifications.add(matchedQualification);
				}
			}
			
			applicationdto.setMatchedQualifications(matchedQualifications);
			applicationdto.setRemainedSkills(remainedSkills);
			applicationdto.setRemainedQualifications(remainedQualifications);
			applicationdto.setPostSkills(postSkills);
			applicationdto.setPostQualifications(postQualifications);
			applicationdto.setPostExperienc(Post.getExperience());
			 if(!matchedSkills.isEmpty())
	            {
				 if(applicationdto.getStatuseCode()!=null)
				 {
					 if(!applicationdto.getStatuseCode().equals("Accepted"))
					 {
						 if((matchedSkills.size()+matchedQualifications.size())<((postSkills.size()+postQualifications.size())/2))
				            {
			            			applicationdto.setStatuseCode("Not match with : ("+(int)((Double.valueOf((matchedSkills.size()+matchedQualifications.size()))/Double.valueOf((postSkills.size()+postQualifications.size())))*100)+"%)");
			            		
				            }else 
				            {                  //(matchedSkills.size()+matchedQualifications.size())/
				            	applicationdto.setStatuseCode("Matched with : ("+(int)((Double.valueOf((matchedSkills.size()+matchedQualifications.size()))/Double.valueOf((postSkills.size()+postQualifications.size())))*100)+"%)");
				            } 
					 }else
					 {
						 applicationdto.setStatuseCode("Accepted with : ("+(int)((Double.valueOf((matchedSkills.size()+matchedQualifications.size()))/Double.valueOf((postSkills.size()+postQualifications.size())))*100)+"%)");
					 }
					
				 }else 
				 {
					 if((matchedSkills.size()+matchedQualifications.size())<((postSkills.size()+postQualifications.size())/2))
			            {
		            			applicationdto.setStatuseCode("Not match with : ("+(int)((Double.valueOf((matchedSkills.size()+matchedQualifications.size()))/Double.valueOf((postSkills.size()+postQualifications.size())))*100)+"%)");
		            		
			            }else 
			            {                  //(matchedSkills.size()+matchedQualifications.size())/
			            	applicationdto.setStatuseCode("Matched with : ("+(int)((Double.valueOf((matchedSkills.size()+matchedQualifications.size()))/Double.valueOf((postSkills.size()+postQualifications.size())))*100)+"%)");
			            } 
				 }
	            	
	            }
            applicationScores.add(new ApplicationScore(applicationdto, score));
        }
        
        // Sort applications based on score in descending order
        Collections.sort(applicationScores, Comparator.comparingInt(ApplicationScore::getScore).reversed());
        
        // Extract applications from ApplicationScore objects
        List<applicationDTO> sortedApplications = new ArrayList<>();
        for (ApplicationScore applicationScore : applicationScores) {
            sortedApplications.add(applicationScore.getApplication());
        }
        return sortedApplications;
    }
	
	
	/*
	 public int calculateScore(Set<String> postSkillSet, Set<String> applicationSkills) {
    // Create maps to count occurrences of skills in post and application
    Map<String, Long> postSkillCount = postSkillSet.stream()
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    
    Map<String, Long> applicationSkillCount = applicationSkills.stream()
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    // Calculate score based on the minimum occurrences of each skill
    int score = applicationSkillCount.entrySet().stream()
            .mapToInt(entry -> {
                String skill = entry.getKey();
                long applicationCount = entry.getValue();
                long postCount = postSkillCount.getOrDefault(skill, 0L);
                return (int) Math.min(applicationCount, postCount);
            })
            .sum();

    System.out.println("Score: " + score);
    return score;
}

	 */
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
	/* 
	 * 
	 * @author BOBO
	 * @param postSkills
	 * @param applicationSkills
	 * @function calculate the score of postSkills contains how much of application skills
	 */
//	 private int calculateScore(List<String> postSkills, List<String> applicationSkills) {
//	       int score = 0;
//	       for (int i=0;i<postSkills.size();i++) {
//			postSkills.set(i,postSkills.get(i).toLowerCase());
//	       }
//	        Set<String> postSkillSet = new HashSet<>(postSkills);
//	        
//	        for (String skill : applicationSkills) {
//	            if (postSkillSet.contains(skill.toLowerCase())) {
//	                score++;
//	            }
//	        }
//	        
//	        return score;
//	    }

	@Override
	 public String returningTheRemainedSkills(List<String> postSkills,List<String> applicationSkills)
	 {
		 int score = 0;
		 System.out.println("Application Skills From returning : "+applicationSkills);
	       for (int i=0;i<applicationSkills.size();i++) { 
	    	   applicationSkills.set(i,applicationSkills.get(i).toLowerCase());
	       }
	       
	        for (String skill : postSkills) {
	            if (applicationSkills.contains(skill.toLowerCase())) {
	                score++;
	            }else 
	            {
	            	remainedSkills.add(skill);
	            }
	        }
	        
		 if(score<(postSkills.size()/2))
		 {
			 System.out.println("The score is : "+ score +" Post Skills List : "+postSkills.size()+"\npost skills after calculate : "+(postSkills.size()/2));
			 return "No"; // No refere to not qualified to apply and return the list of remained skills
		 }else 
		 {			 
			 System.out.println("The score is : "+ score +" Post Skills List : "+postSkills.size()+"\npost skills after calculate : "+(postSkills.size()/2));

			 return "Yes";
		 }
		
	 }
	
	@Override
	 public List<String> returningRemainedSkillsForListOfPosts(List<String> postSkills,List<String> applicationSkills)
	 {
		 int score = 0;
		 List<String> remainedSkillsReturn=new ArrayList<String>();
		 System.out.println("Application Skills From returning : "+applicationSkills);
	       for (int i=0;i<applicationSkills.size();i++) { 
	    	   applicationSkills.set(i,applicationSkills.get(i).toLowerCase());
	       }
	       
	        for (String skill : postSkills) {
	            if (applicationSkills.contains(skill.toLowerCase())) {
	                score++;
	            }else 
	            {
	            	remainedSkillsReturn.add(skill);
	            }
	        }
	        
		 if(score<(postSkills.size()/2))
		 {
			 System.out.println("The score is : "+ score +" Post Skills List : "+postSkills.size()+"\npost skills after calculate : "+(postSkills.size()/2));
//			 return "No"; // No refere to not qualified to apply and return the list of remained skills
			 return remainedSkillsReturn;
		 }else 
		 {			 
			 System.out.println("The score is : "+ score +" Post Skills List : "+postSkills.size()+"\npost skills after calculate : "+(postSkills.size()/2));
			 return remainedSkillsReturn;
//			 return "Yes";
		 }
		
	 }
	@Override
	 public List<String> returningRemainedQualificationsForPostList(List<String> postSkills,List<String> applicationSkills)
	 {
		 int score = 0;
		 List<String> remainedSkillsReturn=new ArrayList<String>();
		 System.out.println("Application Skills From returning : "+applicationSkills);
	       for (int i=0;i<applicationSkills.size();i++) { 
	    	   applicationSkills.set(i,applicationSkills.get(i).toLowerCase());
	       }
	       
	        for (String skill : postSkills) {
	            if (applicationSkills.contains(skill.toLowerCase())) {
	                score++;
	            }else 
	            {
	            	remainedSkillsReturn.add(skill);
	            }
	        }
	        
		 if(score<(postSkills.size()/2))
		 {
			 System.out.println("The score is : "+ score +" Post qual List : "+postSkills.size()+"\npost quals after calculate : "+(postSkills.size()/2));
//			 return "No"; // No refere to not qualified to apply and return the list of remained skills
			 return remainedSkillsReturn;
		 }else 
		 {			 
			 System.out.println("The score is : "+ score +" Post qual List : "+postSkills.size()+"\npost quals after calculate : "+(postSkills.size()/2));
			 return remainedSkillsReturn;
//			 return "Yes";
		 }
		
	 }
	@Override
	 public String returningRemainedQualifications(List<String> postQualifications, List<String> applicationQualifications)
	 {
		 int score = 0;
		 System.out.println("Application qualifications From returning : "+applicationQualifications);
	       for (int i=0;i<applicationQualifications.size();i++) { 
	    	   applicationQualifications.set(i,applicationQualifications.get(i).toLowerCase());
	       }
	       
	        for (String skill : postQualifications) {
	            if (applicationQualifications.contains(skill.toLowerCase())) {
	                score++;
	            }else 
	            {
	            	remainedQualifications.add(skill);
	            }
	        }
	        
		 if(score<(postQualifications.size()/2))
		 {
			 System.out.println("The score is : "+ score +" Post Skills List : "+postQualifications.size()+"\npost Qualifications After calculate : "+(postQualifications.size()/2));
			 return "No"; // No refere to not qualified to apply and return the list of remained skills
		 }else 
		 {			 
			 System.out.println("The score is : "+ score +" Post Skills List : "+postQualifications.size()+"\npost Qualifications After calculate : "+(postQualifications.size()/2));

			 return "Yes";
		 }
		
	 }
		/**
		 * 
		 * @author BOBO
		 * @param postSkills
		 * @param applicationSkills
		 * @function calculate the score of postSkills contains how much of application skills with similarity 
		 */
//	 public int calculateScoreForSimilarity(List<String> postSkills, List<String> applicationSkills) {
//	        
//		 int score = 0;	 
//	        Set<String> postSkillSet = new HashSet<>(postSkills);
//	        for(String postSkill:postSkillSet)
//            {
//	        	  for (String skill : applicationSkills) {
//	   	           
//	        		  // Using Jaro-Winkler distance for string similarity comparison
//	                  JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();
//	                  double similarity = jaroWinkler.apply(skill, postSkill);
//	              	if (similarity>=0.5) {
//	  	                score++;
//	  	            }else 
//	  	            {
//	  	            	remainedSkills.add(postSkill);
//	  	            }
//	  	            
//	  	        }
//	  	        
//            	
//            }
//	      
//	    
//	        return score;
//	    }
	/**
	 * 
	 * @Author BOB
	 * @Function Helper Class for store the score of each Application
	 */
	private static class ApplicationScore {
        private applicationDTO application;
        private int score;
        
        public ApplicationScore(applicationDTO application, int score) {
            this.application = application;
            this.score = score;
        }
        
        public applicationDTO getApplication() {
            return application;
        }
        
        public int getScore() {
            return score;
        }
    }
	@Override
	public Application findByJobSeekerIdAndPostId(Long jobSeekerId, Long postId) {
		return applicationRepository.findByPostIdAndJobSeekerId(postId,jobSeekerId);
	}

	@Transactional
	@Override
	public Application accept(Long id) {
		applicationRepository.acceptApplication(id);
		return findById(id);
	}

	@Transactional
	@Override
	public Application reject(Long id) {
		applicationRepository.rejectApplication(id);
		return findById(id);
	}
	
}

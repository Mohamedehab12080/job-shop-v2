package com.example.JOBSHOP.JOBSHOP.Application;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.JaroWinklerDistance;

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

import com.example.JOBSHOP.JOBSHOP.Post.Post;

@Service
public class applicationService{

	@Autowired
	private applicationRepository applicationRepository;

	public List<String> remainedSkills=new ArrayList<String>();

	

    public Application getReferenceById(Long id)
	{
		return applicationRepository.getReferenceById(id);
	}
   
	public List<Application> findAll()
	{
		return applicationRepository.findAll();
	}
	
	
	public Application insert(Application t)
	{
		
		return applicationRepository.save(t);
	}
	
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
	public List<Application> insertAll(List<Application> entity)
	{
		return applicationRepository.saveAll(entity);
	}
	
	public void deleteById(Long id)
	{
		Application t=getReferenceById(id);
		if(t!=null)
		{
			applicationRepository.deleteById(id);
		}
	}
	
	/**
	 * 
	 * @Auther BOB {}
	 * @Function find JobSeeker's submitted applications order by LIFO
	 */
	public List<Application> findByJobSeekerIdOrderByCreatedDate(Long id) {
		return applicationRepository.findByJobSeekerIdOrderByCreatedDateDesc(id);
	}
	
	/**
	 * 
	 * @Author BOB
	 * @Function Get List <Application> for a specific Post from higher matched to lower matched for the Employer
	 */
	public List<Application> getBestApplicationsForPost(Post Post) {
        
		List<Application> applications = applicationRepository.findByPostId(Post.getId());
        
        List<ApplicationScore> applicationScores = new ArrayList<>();
        
        // Calculate score for each application
        for (Application application : applications) {
            int score = calculateScore(Post.getSkills(), application.getSkills());
            applicationScores.add(new ApplicationScore(application, score));
        }
        
        // Sort applications based on score in descending order
        Collections.sort(applicationScores, Comparator.comparingInt(ApplicationScore::getScore).reversed());
        
        // Extract applications from ApplicationScore objects
        List<Application> sortedApplications = new ArrayList<>();
        for (ApplicationScore applicationScore : applicationScores) {
            sortedApplications.add(applicationScore.getApplication());
        }
        
        return sortedApplications;
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

	 /**
	  * @author BOBO
	  * @param postSkills
	  * @param applicationSkills
	  * @return No for not insert and yes for insert and the remained skills for applier
	  */
	 public String returningTheRemainedSkills(List<String> postSkills, List<String> applicationSkills)
	 {
		 int score = 0;
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
			 System.out.println("The score is : "+ score +" Post Skills List : "+postSkills.size()+"\n"+(postSkills.size()/2));
			 return "No";
		 }else 
		 {			 System.out.println("The score is : "+ score +" Post Skills List : "+postSkills.size()+"\n"+(postSkills.size()/2));

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
	 public int calculateScoreForSimilarity(List<String> postSkills, List<String> applicationSkills) {
	        
		 int score = 0;	 
	        Set<String> postSkillSet = new HashSet<>(postSkills);
	        for(String postSkill:postSkillSet)
            {
	        	  for (String skill : applicationSkills) {
	   	           
	        		  // Using Jaro-Winkler distance for string similarity comparison
	                  JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();
	                  double similarity = jaroWinkler.apply(skill, postSkill);
	              	if (similarity>=0.5) {
	  	                score++;
	  	            }else 
	  	            {
	  	            	remainedSkills.add(postSkill);
	  	            }
	  	            
	  	        }
	  	        
            	
            }
	      
	    
	        return score;
	    }
	/**
	 * 
	 * @Author BOB
	 * @Function Helper Class for store the score of each Application
	 */
	private static class ApplicationScore {
        private Application application;
        private int score;
        
        public ApplicationScore(Application application, int score) {
            this.application = application;
            this.score = score;
        }
        
        public Application getApplication() {
            return application;
        }
        
        public int getScore() {
            return score;
        }
    }
	
}

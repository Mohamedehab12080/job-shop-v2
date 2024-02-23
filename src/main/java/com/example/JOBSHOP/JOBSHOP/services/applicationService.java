package com.example.JOBSHOP.JOBSHOP.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.repositories.applicationRepository;

@Service
public class applicationService{

	@Autowired
	private applicationRepository applicationRepository;

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
	
	 private int calculateScore(List<String> postSkills, List<String> applicationSkills) {
	        int score = 0;
	       for (int i=0;i<postSkills.size();i++) {
			postSkills.set(i,postSkills.get(i).toLowerCase());
	       }
	        Set<String> postSkillSet = new HashSet<>(postSkills);
	        
	        for (String skill : applicationSkills) {
	            if (postSkillSet.contains(skill.toLowerCase())) {
	                score++;
	            }
	        }
	        
	        return score;
	    }
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

package com.example.JOBSHOP.JOBSHOP.Application;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
@Entity
public class Application extends baseEntity<Long>{
 
	private List<String>Skills;
		
	@ManyToOne
	@JoinColumn(name="jobSeeker_id")
	private jobSeeker jobSeeker;
	
    private String Experience;
        
    @ManyToOne
    @JoinColumn(name="Post_id")
    private Post Post;
    
	public List<String> getSkills() {
		return Skills;
	}
	public void setSkills(List<String> Skills) {
		this.Skills = Skills;
	}
	public jobSeeker getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(jobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	public String getExperience() {
		return Experience;
	}
	public void setExperience(String Experience) {
		this.Experience = Experience;
	}
	public Post getPost() {
		return Post;
	}
	public void setPost(Post post) {
		this.Post = post;
	}
    
    
}

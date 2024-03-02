	package com.example.JOBSHOP.JOBSHOP.Application;
	
	import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.fasterxml.jackson.annotation.JsonBackReference;
	import com.fasterxml.jackson.annotation.JsonIgnore;
	
	
	public class applicationDTO extends baseEntityDTO<Long>{
	
		
		private List<String>Skills;
		private jobSeeker jobSeekerr;
	    private String Experience;  
	    private Post post;	
	
	    
	    public List<String> getSkills() {
			return Skills;
		}
		public void setSkills(List<String> Skills) {
			this.Skills = Skills;
		}
		public jobSeeker getJobSeeker() {
			return jobSeekerr;
		}
		public void setJobSeeker(jobSeeker jobSeekerr) {
			this.jobSeekerr = jobSeekerr;
		}
		public String getExperience() {
			return Experience;
		}
		public void setExperience(String Experience) {
			this.Experience = Experience;
		}
		public Post getPost() {
			return post;
		}
		public void setPost(Post post) {
			this.post = post;
		}
	    
	    
	}

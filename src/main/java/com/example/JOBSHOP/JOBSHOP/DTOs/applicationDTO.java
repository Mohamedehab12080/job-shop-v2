	package com.example.JOBSHOP.JOBSHOP.DTOs;
	
	import java.util.List;
	
	import com.example.JOBSHOP.JOBSHOP.models.Post;
	import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
	import com.fasterxml.jackson.annotation.JsonBackReference;
	import com.fasterxml.jackson.annotation.JsonIgnore;
	
	
	public class applicationDTO extends baseEntityDTO<Long>{
	
		
		private List<String>Skills;
	
		
		private jobSeeker jobSeekerr;
		
	    private String Experience;
	    
	    private String approvalState;
	    
	    private Post Postt;	
	
	    public List<String> getAdditionalSkills() {
			return Skills;
		}
		public void setAdditionalSkills(List<String> Skills) {
			this.Skills = Skills;
		}
		public jobSeeker getJobSeeker() {
			return jobSeekerr;
		}
		public void setJobSeeker(jobSeeker jobSeekerr) {
			this.jobSeekerr = jobSeekerr;
		}
		public String getAdditionalExperience() {
			return Experience;
		}
		public void setAdditionalExperience(String Experience) {
			this.Experience = Experience;
		}
		public String getApprovalState() {
			return approvalState;
		}
		public void setApprovalState(String approvalState) {
			this.approvalState = approvalState;
		}
		public Post getPost() {
			return Postt;
		}
		public void setPost(Post postt) {
			this.Postt = postt;
		}
	    
	    
	}

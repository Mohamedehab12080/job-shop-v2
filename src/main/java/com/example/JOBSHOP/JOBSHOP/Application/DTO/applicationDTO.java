	package com.example.JOBSHOP.JOBSHOP.Application.DTO;
	
	import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Application.qualification.applicationQualification;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.DTO.applicationQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.skill.applicationSkill;
import com.example.JOBSHOP.JOBSHOP.Application.skill.DTO.applicationSkillDTO;
import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class applicationDTO extends baseEntityDTO<Long>{
	
		
		private List<String>Skills=new ArrayList<String>();
		private List<String>qualifications=new ArrayList<String>();
		private jobSeeker jobSeekerr;
		private String jobSeekerEmail;
		private Long jobSeekerId;
	    private String Experience;  
	    private Post post;	
	    private Long postId;
	    private List<applicationQualificationDTO> applicationQualifications=new ArrayList<applicationQualificationDTO>();
	    private List<applicationSkillDTO> applicationSkills=new ArrayList<applicationSkillDTO>();
	    
	    
	    
	    public Long getPostId() {
			return postId;
		}
		public void setPostId(Long postId) {
			this.postId = postId;
		}
		public List<applicationQualificationDTO> getApplicationQualifications() {
			return applicationQualifications;
		}
		public void setApplicationQualifications(List<applicationQualificationDTO> applicationQualifications) {
			this.applicationQualifications = applicationQualifications;
		}
		public List<applicationSkillDTO> getApplicationSkills() {
			return applicationSkills;
		}
		public void setApplicationSkills(List<applicationSkillDTO> applicationSkills) {
			this.applicationSkills = applicationSkills;
		}
		public List<String> getQualifications() {
			return qualifications;
		}
		public void setQualifications(List<String> qualifications) {
			this.qualifications = qualifications;
		}
		public jobSeeker getJobSeekerr() {
			return jobSeekerr;
		}
		public void setJobSeekerr(jobSeeker jobSeekerr) {
			this.jobSeekerr = jobSeekerr;
		}
		public String getJobSeekerEmail() {
			return jobSeekerEmail;
		}
		public void setJobSeekerEmail(String jobSeekerEmail) {
			this.jobSeekerEmail = jobSeekerEmail;
		}
		public Long getJobSeekerId() {
			return jobSeekerId;
		}
		public void setJobSeekerId(Long jobSeekerId) {
			this.jobSeekerId = jobSeekerId;
		}
		public List<String> getSkills() {
			return Skills;
		}
		public void setSkills(List<String> Skills) {
			this.Skills = Skills;
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

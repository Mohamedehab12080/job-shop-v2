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
		private String jobSeekerUserName;
		private Long jobSeekerId;
		private String jobSeekerAddress;
	    private String experience;  
	    private String jobSeekerEducation;
	    private List<String> postSkills;
	    private List<String> postQualifications;
	    private String postExperienc;
	    private String postTitle;
	    private String companyName;
	    private Post post;	
	    private Long postId;
	    private List<String> MatchedSkills;
	    private List<String> MatchedQualifications;
	    private List<String> remainedSkills;
	    private List<String> remainedQualifications;
	    private List<applicationQualificationDTO> applicationQualifications=new ArrayList<applicationQualificationDTO>();
	    private List<applicationSkillDTO> applicationSkills=new ArrayList<applicationSkillDTO>();
	    private String jobSeekerPicture;
	    
	    
	    
	    public String getPostTitle() {
			return postTitle;
		}
		public void setPostTitle(String postTitle) {
			this.postTitle = postTitle;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getPostExperienc() {
			return postExperienc;
		}
		public void setPostExperienc(String postExperienc) {
			this.postExperienc = postExperienc;
		}
		public List<String> getPostSkills() {
			return postSkills;
		}
		public void setPostSkills(List<String> postSkills) {
			this.postSkills = postSkills;
		}
		public List<String> getPostQualifications() {
			return postQualifications;
		}
		public void setPostQualifications(List<String> postQualifications) {
			this.postQualifications = postQualifications;
		}
		public String getJobSeekerEducation() {
			return jobSeekerEducation;
		}
		public void setJobSeekerEducation(String jobSeekerEducation) {
			this.jobSeekerEducation = jobSeekerEducation;
		}
		public String getJobSeekerAddress() {
			return jobSeekerAddress;
		}
		public void setJobSeekerAddress(String jobSeekerAddress) {
			this.jobSeekerAddress = jobSeekerAddress;
		}
		public List<String> getMatchedSkills() {
			return MatchedSkills;
		}
		public void setMatchedSkills(List<String> matchedSkills) {
			MatchedSkills = matchedSkills;
		}
		public List<String> getMatchedQualifications() {
			return MatchedQualifications;
		}
		public void setMatchedQualifications(List<String> matchedQualifications) {
			MatchedQualifications = matchedQualifications;
		}
		public List<String> getRemainedSkills() {
			return remainedSkills;
		}
		public void setRemainedSkills(List<String> remainedSkills) {
			this.remainedSkills = remainedSkills;
		}
		public List<String> getRemainedQualifications() {
			return remainedQualifications;
		}
		public void setRemainedQualifications(List<String> remainedQualifications) {
			this.remainedQualifications = remainedQualifications;
		}
		public String getJobSeekerPicture() {
			return jobSeekerPicture;
		}
		public void setJobSeekerPicture(String jobSeekerPicture) {
			this.jobSeekerPicture = jobSeekerPicture;
		}
		public String getJobSeekerUserName() {
			return jobSeekerUserName;
		}
		public void setJobSeekerUserName(String jobSeekerUserName) {
			this.jobSeekerUserName = jobSeekerUserName;
		}
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
			return experience;
		}
		public void setExperience(String experience) {
			this.experience = experience;
		}
		public Post getPost() {
			return post;
		}
		public void setPost(Post post) {
			this.post = post;
		}
	    
	    
	}

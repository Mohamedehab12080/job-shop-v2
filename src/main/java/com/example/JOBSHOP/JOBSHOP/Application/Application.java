	package com.example.JOBSHOP.JOBSHOP.Application;
	
	
	import java.util.ArrayList;
	import java.util.List;
	
	import com.example.JOBSHOP.JOBSHOP.Application.qualification.applicationQualification;
	import com.example.JOBSHOP.JOBSHOP.Application.skill.applicationSkill;
	import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
	
	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Entity;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.OneToMany;
	
	import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
	import com.fasterxml.jackson.annotation.JsonIgnore;
	import com.example.JOBSHOP.JOBSHOP.Post.Post;
	
	@Entity
	public class Application extends baseEntity<Long>{
	 
	
		@ManyToOne
		@JoinColumn(name="jobSeeker_id")
		private jobSeeker jobSeeker;
		
	    private String Experience;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "application" ,cascade = CascadeType.ALL)
	    private List<applicationQualification> applicationQualifications=new ArrayList<applicationQualification>();
	   
	    @JsonIgnore
	    @OneToMany(mappedBy = "application" ,cascade = CascadeType.ALL)
	    private List<applicationSkill> apllicationSkills=new ArrayList<applicationSkill>();
	   
	    
	    @ManyToOne
	    @JoinColumn(name="Post_id")
	    private Post Post;
	    
	   
	    
		public List<applicationSkill> getApllicationSkills() {
			return apllicationSkills;
		}
		public void setApllicationSkills(List<applicationSkill> apllicationSkills) {
			this.apllicationSkills = apllicationSkills;
		}
		public List<applicationQualification> getApplicationQualifications() {
			return applicationQualifications;
		}
		public void setApplicationQualifications(List<applicationQualification> applicationQualifications) {
			this.applicationQualifications = applicationQualifications;
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

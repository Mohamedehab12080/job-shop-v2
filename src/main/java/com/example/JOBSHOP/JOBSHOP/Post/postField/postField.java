package com.example.JOBSHOP.JOBSHOP.Post.postField;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.fields.Field;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class postField extends baseEntity<Long>{

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name="employerField_id")
//	private employerField employerField;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="field_id")
	private Field field;
	  
	@ManyToOne
	@JoinColumn(name="jobModel_id")
	private jobModel jobModel;
	
//	@OneToOne
//	@JoinColumn(name="Post_id")
//	private Post post;
	
	
	@Column(length = 512) 
	private List<String> skills=new ArrayList<String>();
	@Column(length = 512) 
	private List<String> qualifications=new ArrayList<String>();
	
//	public Post getPost() {
//		return post;
//	}
//	public void setPost(Post post) {
//		this.post = post;
//	}
	
	
	public List<String> getQualifications() {
		return qualifications;
	}
	public jobModel getJobModel() {
		return jobModel;
	}
	public void setJobModel(jobModel jobModel) {
		this.jobModel = jobModel;
	}
	public void setQualifications(List<String> qualifications) {
		this.qualifications = qualifications;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	
	
//	public employerField getEmployerField() {
//		return employerField;
//	}
//	public void setEmployerField(employerField employerField) {
//		this.employerField = employerField;
//	}
//	public Post getPost() {
//		return post;
//	}
//	public void setPost(Post post) {
//		this.post = post;
//	}

	
	
}

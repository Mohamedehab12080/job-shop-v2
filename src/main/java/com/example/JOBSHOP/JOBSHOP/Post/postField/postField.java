package com.example.JOBSHOP.JOBSHOP.Post.postField;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class postField extends baseEntity<Long>{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employerField_id")
	private employerField employerField;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name="Post_id")
	private Post Post;
	
	private List<String> skills=new ArrayList<String>();
	
	private List<String> qualifications=new ArrayList<String>();
	
	
	public List<String> getQualifications() {
		return qualifications;
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
	public employerField getEmployerField() {
		return employerField;
	}
	public void setEmployerField(employerField employerField) {
		this.employerField = employerField;
	}
	public Post getPost() {
		return Post;
	}
	public void setPost(Post post) {
		this.Post = post;
	}
	
}

package com.example.JOBSHOP.JOBSHOP.models;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class postField extends baseEntity<Long>{

	@OneToOne
	@JoinColumn(name="employerField_id")
	private employerField employerField;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="Post_id")
	private Post Post;
	
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

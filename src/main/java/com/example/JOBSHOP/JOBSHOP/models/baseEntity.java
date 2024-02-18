package com.example.JOBSHOP.JOBSHOP.models;

import java.time.LocalDateTime;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;

@MappedSuperclass //not entity
@EntityListeners({AuditingEntityListener.class}) //to use auditing annotations
public class baseEntity <ID>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;
	private Integer statuseCode;
	@CreatedBy
	private String createdBy;
	@CreatedDate
	private LocalDateTime createdDate;
	@LastModifiedBy
	private String lastModifiedBy;
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	public Integer getStatuseCode() {
		return statuseCode;
	}
	public void setStatuseCode(Integer statuseCode) {
		this.statuseCode = statuseCode;
	}
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
}

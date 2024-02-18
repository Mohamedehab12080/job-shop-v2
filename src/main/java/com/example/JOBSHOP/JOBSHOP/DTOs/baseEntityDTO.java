package com.example.JOBSHOP.JOBSHOP.DTOs;

import java.time.LocalDateTime;

public class baseEntityDTO <ID>{

	
	private ID id;
	private Integer statuseCode;
	
	private String createdBy;
	
	private LocalDateTime createdDate;
	
	private String lastModifiedBy;
	
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


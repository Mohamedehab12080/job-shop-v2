package com.example.JOBSHOP.JOBSHOP.fields;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Field extends baseEntity<Long> {
	
	@Column(unique = true)
	private String fieldName;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	 
	
}

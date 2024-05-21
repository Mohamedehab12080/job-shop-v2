package com.example.JOBSHOP.JOBSHOP.location;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;

import jakarta.persistence.Entity;

@Entity
public class location extends baseEntity<Long>{

	private String locationValue;

	public String getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}
	
	
}

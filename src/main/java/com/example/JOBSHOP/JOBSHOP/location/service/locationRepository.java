package com.example.JOBSHOP.JOBSHOP.location.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.location.location;

@Repository
public interface locationRepository extends JpaRepository<location,Long>{

	@Query(value = "SELECT l.locationValue from location l")
	List<String> findAllValues();

	location findByLocationValue(String locationValue);

}

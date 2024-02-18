package com.example.JOBSHOP.JOBSHOP.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;
import com.example.JOBSHOP.JOBSHOP.DTOs.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.requests.jobSeekerRequest;
import com.example.JOBSHOP.JOBSHOP.services.jobSeekerService;

@RestController
@RequestMapping("/jobSeeker")
public class jobSeekerController {

	@Autowired
	private jobSeekerService jobSeekerService;
	
	@PostMapping("/apply")
	public ResponseEntity<?>Apply(@RequestBody applicationDTO app)
	{
//		System.out.println("Application saved Skills: "+app.getJobSeeker().getSkills()+
//				": application Post Skills: "+app.getPost().getPostFields().get(1).getEmployerField().getCompanyField().getSkills());
		return ResponseEntity.status(HttpStatus.CREATED).body(jobSeekerService.applyForPost(app));
	}
	
	@GetMapping("/findAllApp/{id}")
	public List<applicationDTO> findAllApplicationsWithJobSeekerID(@PathVariable Long id)
	{
		List<Application> appList=jobSeekerService.findAllApplicationsForJobSeeker(id);
		return appList.stream()
				.map(this::convertApplications)
				.collect(Collectors.toList());
	}
	 
	private applicationDTO convertApplications(Application app)
	{
		return entityToDTOMapper.mapApplicationToDTO(app);
	}
	
	@PostMapping("/insertPicture")
	public ResponseEntity<?> uploadFile(@RequestBody jobSeeker jobSeeker,@RequestPart("file")MultipartFile file) throws SQLException, IOException
	{	
		jobSeeker.setPicture(createBlob(file.getBytes()));
		return ResponseEntity.ok(jobSeekerService.insert(jobSeeker));
	}
	
//	public jobSeeker mapJobSeekerRequestToJobSeeker(jobSeekerRequest jobSeeker) throws SQLException, IOException
//	{
//		jobSeeker dto=new jobSeeker();
//		dto.setId(jobSeeker.getId());
//		dto.setCreatedBy(jobSeeker.getCreatedBy());
//		dto.setAddress(jobSeeker.getAddress());
//		dto.setContacts(jobSeeker.getContacts());
//		dto.setCreatedDate(jobSeeker.getCreatedDate());
//		dto.setLastModifiedBy(jobSeeker.getLastModifiedBy());
//		dto.setLastModifiedDate(jobSeeker.getLastModifiedDate());
//		dto.setEmail(jobSeeker.getEmail());
//		dto.setPassword(jobSeeker.getPassword());
//		dto.setUserName(jobSeeker.getUserName());
//		dto.setStatuseCode(jobSeeker.getStatuseCode());
//		dto.setUserType(jobSeeker.getUserType());
//		dto.setEducation(jobSeeker.getEducation());
//		dto.setSkills(jobSeeker.getSkills());
//		dto.setPicture(createBlob(jobSeeker.getPicture().getBytes()));
//		dto.setEmploymentState(jobSeeker.getEmploymentState()); 
//		return dto;
//	}
//	@PostMapping("/insertBlob")
//	public ResponseEntity<?> insertJobSeekerBLOB(@RequestBody jobSeeker jobSeeker) throws SQLException, IOException
//	{
//		jobSeeker.setPicture(createBlob((MultipartFile)jobSeeker.getPicture()));
//		return ResponseEntity.ok(jobSeeker);
//	}
	private Blob createBlob(byte[] data) throws SQLException, IOException {
        return new SerialBlob(data);
    } 
	@PostMapping("/insert")
	public ResponseEntity<?> insertJobSeeker(@RequestBody jobSeeker jobSeeker)
	{
		return ResponseEntity.ok(jobSeekerService.insert(jobSeeker));
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
	{
		return ResponseEntity.ok(jobSeekerService.getJobSeekerWithID(id));
	}
}

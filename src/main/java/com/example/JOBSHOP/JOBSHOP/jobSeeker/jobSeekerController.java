package com.example.JOBSHOP.JOBSHOP.jobSeeker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.applicationService;
import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;

@RestController
@RequestMapping("/jobSeeker")
public class jobSeekerController {

	@Autowired
	private jobSeekerService jobSeekerService;
	
	@Autowired 
	private applicationService applicationService;
	
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
	
	@PutMapping("/insertPicture/{id}")
	public ResponseEntity<?> uploadFile(@PathVariable Long id,@RequestBody byte[] file) throws SQLException, IOException
	{	
		try {
			return ResponseEntity.ok(jobSeekerService.insertPicture(id,file));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertJobSeeker(@RequestBody jobSeeker jobSeeker)
	{
		return ResponseEntity.ok(jobSeekerService.insert(jobSeeker));
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
	{
		return ResponseEntity.ok(jobSeekerService.findById(id));
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
//	private Blob createBlob(byte[] data) throws SQLException, IOException {
//        return new SerialBlob(data);
//    } 
	

}

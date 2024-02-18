package com.example.JOBSHOP.JOBSHOP.controllers;

import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerFieldDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.postDTO;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.models.employerField;
import com.example.JOBSHOP.JOBSHOP.services.employerFieldService;
import com.example.JOBSHOP.JOBSHOP.services.employerService;

@RestController
@RequestMapping("/employer")
public class employerController {

	@Autowired
	private employerService employerService;
	
	@Autowired
	private employerFieldService employerFieldService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertEmployer(@RequestBody Employer employer)
	{
		return ResponseEntity.ok(employerService.insertEmployer(employer)); 
	}
	@GetMapping("/findPost/{empId}")
	public List<postDTO> findAllPostsWithEmpId(@PathVariable Long empId)
	{
		List<Post> Posts=employerService.findByEmpId(empId);
		return Posts.stream()
				.map(this::convertPost)
				.collect(Collectors.toList()); 
	}
	@GetMapping("/findAll/{compId}")
	public List<employerDTO> findAllEmployersWithCompanyAdminId(@PathVariable Long compId)
	{
		List<Employer> employer= employerService.findAllWithCompanyAdministrator(compId);
        return employer.stream()
                .map(this::convert)
                .collect(Collectors.toList());
	} 
	
	private postDTO convertPost(Post post)
	{
		return entityToDTOMapper.mapPostToDTO(post);
	}
	private employerDTO convert(Employer employer)
	{
		return entityToDTOMapper.mapEmployerToDTO(employer);
	}
	@PostMapping("/post")
	public ResponseEntity<?> makeAPost(@RequestBody Post post)
	{
		return ResponseEntity.ok(employerService.createAPost(post));
	}
	
	@GetMapping("/findFields/{id}")
	public List<employerFieldDTO> findEmployerFieldsById(@PathVariable Long id)
	{
		List <employerField> listFields=employerFieldService.findAllEmployerFieldsWithId(id);
		return listFields.stream()
				.map(this::convertToEmployerFieldDTO)
				.collect(Collectors.toList());
	}
	
	private employerFieldDTO convertToEmployerFieldDTO(employerField employerField)
	{
		return entityToDTOMapper.mapEmployerFieldToDTO(employerField);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<employerDTO> findById(@PathVariable Long id)
	{
		Employer employer=employerService.findById(id);
		System.out.println("employer Posts : "+employer.getPosts().get(0).getId());
		return ResponseEntity.ok(entityToDTOMapper.mapEmployerToDTO(employer));
	}
}

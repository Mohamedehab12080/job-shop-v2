package com.example.JOBSHOP.JOBSHOP.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;
import com.example.JOBSHOP.JOBSHOP.DTOs.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.models.companyField;
import com.example.JOBSHOP.JOBSHOP.services.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.services.companyFieldService;
import com.example.JOBSHOP.JOBSHOP.services.employerService;

import jakarta.validation.Valid;

import com.example.JOBSHOP.JOBSHOP.models.employerField;
@RestController
@RequestMapping("/company")
public class companyAdminController {
 
	@Autowired
	private companyAdminService companyAdminService;
	@Autowired
	private employerService employerService;
	@Autowired 
	private companyFieldService companyFieldService;
	
	
//	@DeleteMapping("/deleteEmployer/{id}")
//	public int deleteEmployerWithIdQuery(@PathVariable Long id)
//	{
//		return companyAdminService.deleteEmployer(id);
//	} 
	
	@DeleteMapping("/deleteEmployerWithId/{id}")
	public int deleteEmployer(@PathVariable Long id)
	{
		return companyAdminService.deleteEmployerWithId(id);
	}
	
	@PostMapping("/giveEmployerFields")
	public ResponseEntity<?> giveEmployerFields(@RequestBody /*@Valid*/ List<employerField> employerFields)
	{
		companyAdminService.giveEmployerFields(employerFields,10);
		return ResponseEntity.ok("Employer has its fields now ");
	}
	
	@PostMapping("/giveEmployerField")
	public ResponseEntity<?> giveEmployerField(@RequestBody  /*@Valid*/ employerField employerField)
	{
		return ResponseEntity.ok(companyAdminService.giveEmployerField(employerField)); 
	}
	
	@PostMapping("/createEmployer")
	public ResponseEntity<?> insertEmployer(@RequestBody /*@Valid*/ Employer employer)
	{
		return ResponseEntity.ok(employerService.insert(employer));
	}
	
	@PostMapping("/createField")
	public ResponseEntity<?> insertField(@RequestBody @Valid companyField companyField)
	{
		return ResponseEntity.ok(companyFieldService.insertCompanyField(companyField));
	}
	
	@GetMapping("/findAllFields/{id}")
	public List<companyFieldDTO> findAllFields(@PathVariable Long id)
	{
		List<companyField> comp=companyFieldService.findCompanyFieldsWithAdminId(id);
		return comp.stream()
				.map(this::convertCompantField)
				.collect(Collectors.toList());
	}
	
	private companyFieldDTO convertCompantField(companyField companyField)
	{
		return entityToDTOMapper.mapCompanyFieldToDTO(companyField);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertCompanyAdministrator(@RequestBody /*@Valid */ companyAdministrator companyAdministrator)
	{
		return ResponseEntity.ok(companyAdminService.insert(companyAdministrator));
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll()
	{
		return ResponseEntity.ok(companyAdminService.findAll());
	}
}

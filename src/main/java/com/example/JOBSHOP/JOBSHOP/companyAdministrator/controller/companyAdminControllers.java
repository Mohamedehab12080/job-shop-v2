package com.example.JOBSHOP.JOBSHOP.companyAdministrator.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.service.companyFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.degrees.service.qualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.skills.service.skillServiceInterface;

@Controller
@RequestMapping("/companyAdmin")
public class companyAdminControllers {

	@Autowired
	private companyAdminService companyAdminService;
	
	@Autowired
	private skillServiceInterface skillServiceI;
	
	@Autowired
	private companyFieldServiceInterface companyFieldServiceI;
	
	@Autowired
	private qualificationServiceInterface qualificationServiceI;
	
	private String companyAdminUserName;
	@GetMapping("/form")
	public String testSignUp(Model model)
	{
		jobSeeker jobSeeker=new jobSeeker();
		jobSeeker.setUserType(Role.jobSeeker);
		model.addAttribute("jobSeeker",jobSeeker);
		companyAdministrator companyAdministrator=new companyAdministrator();
		companyAdministrator.setUserType(Role.Admin);
		System.out.print("company UserName"+companyAdminUserName);
		companyAdministrator.setEmail(companyAdminUserName);
		model.addAttribute("companyAdministrator",companyAdministrator);
		return "signup";
	}
	
	
	@GetMapping("/add-field/{userId}")
//	@PreAuthorize("hasRole('Admin')")
	public String addFields(@PathVariable("userId") Long id, Model model)
	{
		
		companyFieldDTO companyField=new companyFieldDTO();
		companyAdministrator com=companyAdminService.findById(id);
		if(com!=null)
		{
			companyField.setCompanyAdministratorId(com.getId());
			companyField.setCompanyName(com.getCompanyName());
			
			model.addAttribute("companyField",companyField);
			model.addAttribute("skills",skillServiceI.findAllDistinct());
			model.addAttribute("qualifications",qualificationServiceI.findAll());

			List<companyField>companyFieldList=companyFieldServiceI.findCompanyFieldsWithAdminId(com.getId());
			List<companyFieldDTO> companyFieldDtoList=companyFieldList.stream().map(this::convertCompanyFieldToDTO).collect(Collectors.toList());
			
			model.addAttribute("addedfields",companyFieldDtoList);
			return "add-fields";
		}else 
		{
			return "login";
		}
	}
	
//	@GetMapping("/add-field/{userName}/{fieldId}")
//	public String addFieldsAfterFirstAdd(@PathVariable("userName") String userName,@PathVariable("fieldId") Long fieldId, Model model)
//	{
//		
//		companyFieldDTO companyField=new companyFieldDTO();
//		companyAdministrator com=companyAdminService.findByUserName(userName);
//		companyField.setCompanyAdministratorId(com.getId());
//		companyField.setCompanyName(com.getCompanyName());
//		model.addAttribute("companyField",companyField);
//		model.addAttribute("skills",skillServiceI.findAll());
//		return "add-fields";
//	}
	
	@PostMapping("/save-field")
	public String saveCompanyField(@ModelAttribute("companyField") companyFieldDTO companyFieldDto,@RequestParam("userName") Long userName)
	{
		List<String> skills=companyFieldDto.getSkills();
		skills.remove("");
		System.out.println("List of skills added : "+skills);
		Long oldCompanyField=companyFieldServiceI.findIdByFieldName(companyFieldDto.getFieldName());
		if(oldCompanyField==null)
		{
			companyField companyFieldResult=companyFieldServiceI.insertCompanyFieldAndSkillsAndQualifications(companyFieldDto);
			if(companyFieldResult!=null)
			{
				return "redirect:/companyAdmin/add-field/"+userName+"?success_register"; //we will send success at the parameter of this url to show the added field with its skills and it can be deleted 
			}else
			{
				return "redirect:/companyAdmin/add-field/"+userName; //we will send success at the parameter of this url to show the added field with its skills and it can be deleted 
			}
		}else 
		{
			return "redirect:/companyAdmin/add-field/"+userName+"?already_exists"; //we will send success at the parameter of this url to show the added field with its skills and it can be deleted 
		}
	}
	
	@GetMapping("/delete-field/{id}")
	public String deleteField(@PathVariable("id") Long id ,@RequestParam("userName") Long userName)
	{
		System.out.println("user Name :"+ userName);
		String result=companyFieldServiceI.deleteById(id);
		if(result.equalsIgnoreCase("deleted"))
		{
			return "redirect:/companyAdmin/add-field/"+userName+"?deleted"; //we will send success at the parameter of this url to show the added field with its skills and it can be deleted 

		}else 
		{
			return "redirect:/companyAdmin/add-field/"+userName; //we will send success at the parameter of this url to show the added field with its skills and it can be deleted 
		}
		
	}
	
	@PostMapping("/save-company")
	public String saveCompanyAdmin(@ModelAttribute("companyAdministrator") companyAdministrator companyAdministrator,@RequestParam("logo") MultipartFile profileImage,Model model)
	{
		System.out.println("companyAdministrator info: "+companyAdministrator.getUserName()
		+": "+companyAdministrator.getEmail()
		+": "+companyAdministrator.getUserType()
		+": "+companyAdministrator.getCompanyName());
		companyAdminUserName=companyAdministrator.getUserName();
		return "redirect:/companyAdmin/form?company_success";
	}
	
	private companyFieldDTO convertCompanyFieldToDTO(companyField companyField)
	{
		return companyFieldMapper.mapCompanyFieldToDTO(companyField);
	}
}

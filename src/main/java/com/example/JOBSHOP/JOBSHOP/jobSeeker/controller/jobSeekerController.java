package com.example.JOBSHOP.JOBSHOP.jobSeeker.controller;

import java.io.IOException;



import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationMapper;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceInerface;
import com.example.JOBSHOP.JOBSHOP.degrees.qualification;
import com.example.JOBSHOP.JOBSHOP.degrees.service.qualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.service.jobSeekerQualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.requests.saveSkillsRequest;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.service.jobSeekerSkillServiceInterface;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;
import com.example.JOBSHOP.JOBSHOP.skills.service.skillServiceInterface;

@Controller
@RequestMapping("/jobSeeker")
public class jobSeekerController {

	@Autowired
	private jobSeekerServiceInterface jobSeekerServiceI;
	
	@Autowired
	private skillServiceInterface skillServiceI;
	
	@Autowired
	private qualificationServiceInterface qualificationServiceI;
	
	@Autowired
	private jobSeekerQualificationServiceInterface jobSeekerQualificationServiceI;
	@Autowired
	private jobSeekerSkillServiceInterface jobSeekerSkillServiceI;
	

//	@PostMapping("/save-jobSeeker")
//	public String saveJobSeeker(@ModelAttribute("jobSeeker") jobSeeker jobSeeker,@RequestParam("profileImage") MultipartFile profileImage) throws IOException
//	{
//		
//		return "redirect:/companyAdmin/form";
//	}
//	
//	@GetMapping("/add-skills/{userId}")
////	@PreAuthorize("isAuthenticated() and hasRole('jobSeeker')")
//	public String addSkills (@PathVariable("userId") Long userId, Model model)
//	{
//		
//		jobSeeker jobSeeker=jobSeekerServiceI.findById(userId);
////		saveSkillsRequest modelObject=new saveSkillsRequest();
//		
//		List<Skill> skillsList=skillServiceI.findAllDistinct();
//		model.addAttribute("pre_defined_skills",skillsList);
//
//		List<qualification> qualificationsList=qualificationServiceI.findAll();
//		model.addAttribute("pre_defined_qualifications",qualificationsList);
//		
//		if(jobSeeker!=null) {
//			List<jobSeekerSkill>jobSeekerSkillList=jobSeekerSkillServiceI.findByJobSeekerId(jobSeeker.getId());
//			List<jobSeekerSkillDTO> jobSeekerSkillListDtoList=jobSeekerSkillList.stream().map(this::convertJobSeekerSkill).collect(Collectors.toList());
//			//Key(degree)  value skillName				
//				
//				model.addAttribute("skillsMap",jobSeekerSkillListDtoList); // sending map that contains skill name and skill degree
//				List<jobSeekerQualification>jobSeekerQualifications=jobSeekerQualificationServiceI.findByJobSeekerId(jobSeeker.getId());
//				List<jobSeekerQualificationDTO> jobSeekerQualificationDtoList=jobSeekerQualifications.stream().map(this::convertJobSeekerQualification).collect(Collectors.toList());
//				model.addAttribute("qualificationMap",jobSeekerQualificationDtoList);
//				
////				model.addAttribute("requestData",modelObject);
//		}
//		
//		return "add-skill";
//	}
	
	private jobSeekerQualificationDTO convertJobSeekerQualification(jobSeekerQualification jobSeekerQualification)
	{
		return jobSeekerQualificationMapper.mapJobSeekerQualificationToDTO(jobSeekerQualification);
	}
	
	private jobSeekerSkillDTO convertJobSeekerSkill(jobSeekerSkill jobSeekerSkill)
	{
		return jobSeekerSkillMapper.mapJobSeekerSkillToDTO(jobSeekerSkill);
	}
	
	@PostMapping("/save-skill")
	@ResponseBody
	public String saveCompanyField(@RequestBody saveSkillsRequest requestData/*,@RequestParam("userId") Long userId*/)
	{
			
			String saveSkillResult=jobSeekerServiceI.insertJobSeekerSkillsAndQualificationsOptimized(requestData); // instead of List of objects it will be list of skills and list of qualifications 
			if(saveSkillResult.contains("inserted"))
			{
//				return ResponseEntity.ok(null);
				return "redirect:/jobSeeker/add-skill/"+requestData.getUserId()+"?success_register"; //we will send success at the parameter of this url to show the added field with its skills and it can be deleted 
			}else 
			{
//				return (ResponseEntity<?>) ResponseEntity.noContent();
				return "redirect:/jobSeeker/add-skill/"+requestData.getUserId()+"?fail_insert"; //we will send success at the parameter of this url to show the added field with its skills and it can be deleted 
			}
	}
 
	@DeleteMapping("/delete-skill/{id}")
//	@PreAuthorize("hasRole('jobSeeker')")
	public ModelAndView deleteSkill(@PathVariable("id") Long id)
	{
		String result=jobSeekerSkillServiceI.deleteById(id);
		ModelAndView model=new ModelAndView();
		if(result.equals("deleted"))
		{
			model.addObject("deleteSkill", "skill deleted");
		}else 
		{
			model.addObject("deleteSkill",null);
		}
		model.setViewName("add-skill");
		return model;
		
	}
	@PostMapping("/apply")
	public ResponseEntity<?>Apply(@RequestBody applicationDTO app)
	{
//		System.out.println("Application saved Skills: "+app.getJobSeeker().getSkills()+
//				": application Post Skills: "+app.getPost().getPostFields().get(1).getEmployerField().getCompanyField().getSkills());
		return ResponseEntity.status(HttpStatus.CREATED).body(jobSeekerServiceI.applyForPost(app));
	}
	
	@GetMapping("/findAllApp/{id}")
	public List<applicationDTO> findAllApplicationsWithJobSeekerID(@PathVariable Long id)
	{
		List<Application> appList=jobSeekerServiceI.findAllApplicationsForJobSeeker(id);
		return appList.stream()
				.map(this::convertApplications)
				.collect(Collectors.toList());
	}
	 
	private applicationDTO convertApplications(Application app)
	{
		return applicationMapper.mapApplicationToDTO(app);
	}
	
	@PutMapping("/insertPicture/{id}")
	public ResponseEntity<?> uploadFile(@PathVariable Long id,@RequestBody byte[] file) throws SQLException, IOException
	{	
		try {
			return ResponseEntity.ok(jobSeekerServiceI.insertPicture(id,file));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertJobSeeker(@RequestBody jobSeeker jobSeeker)
	{
		return ResponseEntity.ok(jobSeekerServiceI.insert(jobSeeker));
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
	{
		return ResponseEntity.ok(jobSeekerServiceI.findById(id));
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

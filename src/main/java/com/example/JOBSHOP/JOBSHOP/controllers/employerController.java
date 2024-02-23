package com.example.JOBSHOP.JOBSHOP.controllers;

import java.io.IOException;



import java.sql.SQLException;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerFieldDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerProfileDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.postDTO;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.models.employerField;
import com.example.JOBSHOP.JOBSHOP.models.employerProfile;
import com.example.JOBSHOP.JOBSHOP.services.employerFieldService;
import com.example.JOBSHOP.JOBSHOP.services.employerProfileService;
import com.example.JOBSHOP.JOBSHOP.services.employerService;
import com.example.JOBSHOP.JOBSHOP.services.postService;


@Controller
//@RequestMapping("/employer")
public class employerController {

	@Autowired
	private employerService employerService;
	
	@Autowired
	private employerFieldService employerFieldService;
	@Autowired
	private employerProfileService employerProfileService;
	@Autowired 
	private postService postService;
	
	@GetMapping("/findProfile/{id}")
	public ResponseEntity<employerProfileDTO> findProfile(@PathVariable Long id)
	{
		return ResponseEntity.ok(convertEmployerProfileToDTO(employerProfileService.findByEmployer(id)));
	}
	@PutMapping("/insertPicture/{id}")
	public ResponseEntity<?> uploadFile(@PathVariable Long id,@RequestBody byte[] file) throws SQLException, IOException
	{	
		try {
			return ResponseEntity.ok(employerService.insertPicture(id,file));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping(value="/sss",method = RequestMethod.GET)
	public  ModelAndView getPosts()
	{
		ModelAndView modelAndView = new ModelAndView("index");
		List<Post> listPost=postService.findByEmployer(2L);
		
		 List<postDTO> dtoList=listPost.stream() 
				 .map(this::convertPost) 
		 .collect(Collectors.toList()); 
		 System.out.println(dtoList.get(0).getCompanyName());
		 modelAndView.addObject("posts", dtoList); 
		 return modelAndView;
		
	} 
	
	@GetMapping("/findPost/{empId}")
	public List<postDTO> findAllPostsWithEmpId(@PathVariable Long empId)
	{
		List<Post> Posts=employerService.findPostsByEmployerId(empId);
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
	
	
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
	{
		Employer employer=employerService.findById(id);
		if(employer!=null)
		{
//			System.out.println("employer Posts : "+employer.getPosts().get(0).getId());
			return ResponseEntity.ok(entityToDTOMapper.mapEmployerToDTO(employer));
		}else 
		{
			return null;
		}
	}
	
	private employerFieldDTO convertToEmployerFieldDTO(employerField employerField)
	{
		return entityToDTOMapper.mapEmployerFieldToDTO(employerField);
	}
	
	private employerProfileDTO convertEmployerProfileToDTO(employerProfile employerProfile)
	{
		return entityToDTOMapper.mapEmployerProfileToDTO(employerProfile);
	}
}

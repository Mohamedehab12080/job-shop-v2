package com.example.JOBSHOP.JOBSHOP.Employer.controller;

import java.io.IOException;





import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTOMapper;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO.employerFieldDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO.employerFieldMapper;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerFieldShowDTO.employerFieldShowDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.service.employerFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.employerProfile;
import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.DTO.employerProfileDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.DTO.employerProfileMapper;
import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.service.employerProfileServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Employer.service.employerService;
import com.example.JOBSHOP.JOBSHOP.Employer.service.employerServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.exception.postException;
import com.example.JOBSHOP.JOBSHOP.Post.service.postService;
import com.example.JOBSHOP.JOBSHOP.Registration.controllers.registerUserRequest;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.userService;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJob;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO.companyFieldJobDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO.companyFieldJobDTOMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.service.companyFieldJobServiceInterface;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.DTO.jobQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.DTO.jobQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.service.jobQualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.DTO.jobSkillModelDTO;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.DTO.jobSkillModelDTOMapper;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.service.jobSkillModelServiceInterface;
import com.example.JOBSHOP.JOBSHOP.response.ApiResponse;


@RestController
@RequestMapping("/api/employer")
public class employerController {

	@Autowired
	private employerServiceInterface employerService;
	
	@Autowired
	private employerFieldServiceInterface employerFieldService;
	@Autowired
	private employerProfileServiceImpl employerProfileService;
	@Autowired 
	private postService postService;
	@Autowired
	private userServiceInterface userServiceI;
	@Autowired
	private companyProfileService companyProfileService;
	@Autowired
	private companyFieldJobServiceInterface companyFieldJobServiceI;
	@Autowired
	private jobSkillModelServiceInterface jobSkillModelServiceI;
	@Autowired
	private jobQualificationServiceInterface jobQualificationServiceI;
	
	@GetMapping("/findProfile/{id}")
	public ResponseEntity<employerProfileDTO> findProfile(@PathVariable Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		
		if(user!=null && user.getUserType().name().equals("Employer"))
		{
			return new ResponseEntity<>(convertEmployerProfileToDTO(employerProfileService.findByEmployer(id)),HttpStatus.OK);
		}else
		{
			throw new UserException("user not found for this token");
		}
		
		
	}
	@PutMapping("/insertPicture/{id}")
	public ResponseEntity<?> uploadFile(@PathVariable Long id,@RequestBody String picture) throws SQLException, IOException
	{	
		try {
			return ResponseEntity.ok(employerService.insertPicture(id,picture));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	

	}
	
	@PutMapping("/update")
	public ResponseEntity<employerDTO> updateCompanyUser(
			@RequestBody registerUserRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("Employer"))
		{
			employerDTO returnedUser=employerService.update(reqUSer.getId(),req);
			
			if(returnedUser!=null)
			{
				return new ResponseEntity<>(returnedUser,HttpStatus.OK);
			}
			
			else 
			{
				throw new UserException("User can't be updated");
			}
			
		}
		
		else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	@GetMapping("/findAll/{compId}")
	public ResponseEntity<List<employerDTO> >
	findAllEmployersWithCompanyAdminId(
			@PathVariable Long compId
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null && user.getUserType().equals(Role.Admin))
		{
			List<Employer> employer= employerService.findAllWithCompanyAdministrator(compId);
	        return new ResponseEntity<List<employerDTO>>(employer.stream()
	                .map(this::convert)
	                .collect(Collectors.toList()),HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	} 
	
	
	private postDTO convertPost(Post post)
	{
		return postMapper.mapPostTODTO(post);
	} 
	private employerDTO convert(Employer employer)
	{
		return employerDTOMapper.mapEmployerToDTO(employer);
	}
	
	@GetMapping("/post-request")
	public String makeAPostRquest(Model model)
	{
		
		return "make-post";
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> makeAPost(
			@RequestBody postDTO post,
			@RequestHeader("Authorization")String jwt)throws UserException,postException
	{
		User user =userServiceI.findUserByJwt(jwt);
		if(user!=null && user.getUserType().name().equals("Employer"))
		{
			Employer emp=employerService.findById(user.getId());
			Post savedPost=employerService.createAPost(emp,post);
			System.out.println("saved Post Null "+savedPost.getId());
			Post returnedPost=postService.findById(savedPost.getId());
			post.setId(returnedPost.getId());
			post.setCompanyName(companyProfileService
					.findById(post.getProfileId())
					.getCompanyAdministrator()
					.getCompanyName());
			post.setEmployerUserName(user.getUserName());
			System.out.println("Field Name From inserting post DTo  : "+post.getFieldName());
			post.setFieldName(employerFieldService.findById(user.getId(),post.getField()).getCompanyField().getField().getFieldName());
			return new ResponseEntity<>(post,HttpStatus.CREATED);
		}else
		{
			throw new UserException("User not found for this token");
		}
	}
	
	@PostMapping("/postWithJob")
	public ResponseEntity<?> createAPostWithJobs(
			@RequestBody postDTO post,
			@RequestHeader("Authorization")String jwt)throws UserException,postException
	{
		User user =userServiceI.findUserByJwt(jwt);
		if(user!=null && user.getUserType().name().equals("Employer"))
		{
			Employer emp=employerService.findById(user.getId());
			Post savedPost=employerService.createAPostWithJobs(emp,post);
			System.out.println("saved Post Null "+savedPost.getId());
			Post returnedPost=postService.findById(savedPost.getId());
			post.setId(returnedPost.getId());
			post.setCompanyName(companyProfileService
					.findById(post.getProfileId())
					.getCompanyAdministrator()
					.getCompanyName());
			post.setEmployerUserName(user.getUserName());
			System.out.println("Field Name From inserting post DTo  : "+post.getFieldName());
			post.setFieldName(employerFieldService.findById(user.getId(),post.getField()).getCompanyField().getField().getFieldName());
			return new ResponseEntity<>(post,HttpStatus.CREATED);
		}else
		{
			throw new UserException("User not found for this token");
		}
	}
	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<?> updatePost(
			@PathVariable("postId") Long postId,
			@RequestBody postDTO podtDto,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
			User user =userServiceI.findUserByJwt(jwt);
			if(user!=null)
			{
				return new ResponseEntity< >(postService.update(postId,podtDto),HttpStatus.OK);
			}else 
			{
				throw new UserException("user not found for this token");
			}
	}

	@GetMapping("/findPost/{id}")
	public ResponseEntity<postDTO> findPostById(@PathVariable("id") Long id,
			@RequestHeader("Authorization") String jwt) throws UserException,postException
	{
		User user=userServiceI.findUserByJwt(jwt);
		
		if(user!=null && user.getUserType().name().equals("Employer"))
		{
			Post post=postService.findById(id);
			postDTO postDTo=convertPost(post);
			return new ResponseEntity<postDTO>(postDTo,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
	@DeleteMapping("/deletePost/{id}")
	public ResponseEntity<ApiResponse> DeletePost(@PathVariable("id") Long id,
			@RequestHeader("Authorization") String jwt) throws UserException,postException
	{
		User user=userServiceI.findUserByJwt(jwt);
		
		if(user!=null && user.getUserType().name().equals("Employer"))
		{
			
			postService.deleteById(id);
			ApiResponse res=new ApiResponse("Post deleted successfully",true);
			return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
	@GetMapping("/findAllPosts/{id}")
	public ResponseEntity<List<postDTO>> findAllEmployerPosts(@PathVariable("id") Long id,
			@RequestHeader("Authorization") String jwt) throws UserException,postException
	{
		User user=userServiceI.findUserByJwt(jwt);
		
		if(user!=null && user.getUserType().name().equals("Employer"))
		{
			List<postDTO>ListDto=postService.findByEmployerId(id).stream().
					map(this::convertPost).collect(Collectors.toList());
					
			return new ResponseEntity<>(ListDto,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	
	@GetMapping("/findFields/{id}")
	public ResponseEntity<List<employerFieldShowDTO>> findEmployerFieldsById(
			@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		
		User user=userServiceI.findUserByJwt(jwt);
				
		if(user!=null)
		{
			List <employerFieldShowDTO> listFields=employerFieldService.findAllEmployerFieldsWithId(id).stream()
					.map(this::convertToEmployerFieldDTO)
					.collect(Collectors.toList());
			List <employerFieldShowDTO> updatedListFields=new ArrayList<employerFieldShowDTO>();
			for(employerFieldShowDTO empF:listFields)
			{
				List<companyFieldJobDTO> dtos=companyFieldJobServiceI.findByCompanyFieldId(empF.getCompanyFieldId()).stream().map(companyFieldJobDTOMapper::mapcompanyFieldJobToDTO).collect(Collectors.toList());
				List<companyFieldJobDTO> updatedDTOS2=new ArrayList<companyFieldJobDTO>();
				for(companyFieldJobDTO dtosJ:dtos)
				{
					List<jobSkillModelDTO> jobSkillDTO=jobSkillModelServiceI.findByJobModelId(dtosJ.getJobId()).stream().map(jobSkillModelDTOMapper::mapJobSkillModelToDTO).collect(Collectors.toList());
					List<String>skillsName=new ArrayList<String>();
					for(jobSkillModelDTO dtoSkill:jobSkillDTO)
					{
						skillsName.add(dtoSkill.getSkillName());
					}
					dtosJ.setSkillsName(skillsName);
					System.out.println("Skills Name From Call : "+skillsName);
					List<jobQualificationDTO> jobQualificationDtos=jobQualificationServiceI.findByJobModelId(dtosJ.getJobId()).stream().map(jobQualificationMapper::mapJobQualificationToDTO).collect(Collectors.toList());
					List<String> qualficiationsName=new ArrayList<String>();
					for(jobQualificationDTO dtoQual:jobQualificationDtos)
					{
						qualficiationsName.add(dtoQual.getQualificationName());
					}
					dtosJ.setQualificationsName(qualficiationsName);
					updatedDTOS2.add(dtosJ);
				}
				empF.setCompanyFieldJobDTOs(updatedDTOS2);
				updatedListFields.add(empF);
			}
			return new ResponseEntity<>(updatedListFields,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
	
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
	{
		Employer employer=employerService.findById(id);
		if(employer!=null)
		{
//			System.out.println("employer Posts : "+employer.getPosts().get(0).getId());
			return ResponseEntity.ok(employerDTOMapper.mapEmployerToDTO(employer));
		}else 
		{
			return null;
		}
	}
	
	private employerFieldShowDTO convertToEmployerFieldDTO(employerField employerField)
	{
		employerFieldShowDTO showDto=employerFieldMapper.mapEmployerFieldToDTO(employerField);
		
		List<String> jobsName=new ArrayList<String>();
		List<companyFieldJobDTO> companayFieldJobs=companyFieldJobServiceI
													.findByCompanyFieldId(
															employerField
															.getCompanyField()
															.getId())
															.stream()
															.map(companyFieldJobDTOMapper::mapcompanyFieldJobToDTO)
															.collect(Collectors.toList());
		for(companyFieldJobDTO fieldJobDto :companayFieldJobs) {
			jobsName.add(fieldJobDto.getJobName());
		}
		showDto.setFieldJobs(jobsName);
		return showDto;
	}

	private employerProfileDTO convertEmployerProfileToDTO(employerProfile employerProfile)
	{
		return employerProfileMapper.mapEmployerProfileToDTO(employerProfile);
	}

}

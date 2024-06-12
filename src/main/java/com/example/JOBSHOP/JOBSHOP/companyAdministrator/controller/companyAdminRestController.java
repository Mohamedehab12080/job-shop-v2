package com.example.JOBSHOP.JOBSHOP.companyAdministrator.controller;

import java.io.IOException;





import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTOMapper;
import com.example.JOBSHOP.JOBSHOP.Employer.controller.employerInsertRequest;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO.employerFieldForInsert;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO.employerFieldMapper;
import com.example.JOBSHOP.JOBSHOP.Employer.service.employerService;
import com.example.JOBSHOP.JOBSHOP.Registration.controllers.registerUserRequest;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.security.jwtProvider;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.userProfile;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.service.companyFieldService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.DTO.companyProfileDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.DTO.companyProfileMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.response.AuthResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.Valid;

/**
 * @author BOBO 
 * @class All company Administrator controller has been Tested
 */
@RestController 
@RequestMapping("/api/company")
public class companyAdminRestController {
 
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private companyAdminService companyAdminService;
	@Autowired
	private employerService employerService;
	@Autowired 
	private companyFieldService companyFieldService;
	@Autowired
	private companyProfileService companyProfileService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private jwtProvider jwtProvider;
	@Autowired 
	private userServiceInterface userServiceI;
	@Autowired
	private followService followService;
//	@DeleteMapping("/deleteEmployer/{id}")
//	public int deleteEmployerWithIdQuery(@PathVariable Long id)
//	{
//		return companyAdminService.deleteEmployer(id);
//	} 
	
	
	
	@GetMapping("/findComapnyFields/{compId}")//(Tested)
	public ResponseEntity<List<companyFieldDTO>>
	findllFieldsOfCompany(
			@PathVariable Long compId,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		System.out.printf("JWT FROM THE HEADER: ",jwt);
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null)
		{
			return new ResponseEntity<List<companyFieldDTO>>(companyFieldService.findCompanyFieldsWithAdminId(compId)
				.stream().map(this::convertCompanyField).collect(Collectors.toList()),HttpStatus.OK);
		}else 
		{
			throw new UserException("User not found for this token");
		}
					
	}
	
	@PutMapping("/update")
	public ResponseEntity<companyAdministratorDTO> updateCompanyUser(
			@RequestBody registerUserRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("Admin"))
		{
			companyAdministratorDTO returnedUser=companyAdminService.update(reqUSer.getId(),req);
			
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
	/**
	 * 
	 * @Author BOB
	 * @Function company administrator gives each employer his specific List <employerField> (Tested).
	 */
	@PostMapping("/giveEmployerFields") //(Tested)
	public ResponseEntity<?> 
	giveEmployerFields(
			@RequestBody /*@Valid*/ giveEmployerFieldsRequest giveEmployerFiedldsRequest
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().equals(Role.Admin))
		{
			if(giveEmployerFiedldsRequest.getEmployerFields()!=null 
					&& !giveEmployerFiedldsRequest.getEmployerFields().isEmpty())
			{
				companyAdminService.giveEmployerFields(
						giveEmployerFiedldsRequest.getEmployerFields()
						.stream()
						.map(employerFieldMapper::mapDtoToInsertToEmployerField)
						.collect(Collectors.toList()),10); // give employer Fields in batchs
					
				return new ResponseEntity<>("Employer has its fields success",HttpStatus.OK);
			}else 
			{
				return new ResponseEntity<>("Failed To give employer empty list of fields",HttpStatus.OK);
			}
			
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
	
//	/**
//	 * 
//	 * @Author BOB
//	 * @Function give employer Field one by one.
//	 */
//	@PostMapping("/giveEmployerField")
//	public ResponseEntity<?> giveEmployerField(@RequestBody  /*@Valid*/ employerField employerField)
//	{
//		return ResponseEntity.ok(companyAdminService.giveEmployerField(employerField)); 
//	}
	
	/**
	 * 
	 * @Author BOB
	 * @Function create employer (Tested) with its all info Tested
	 */
	@PostMapping("/createEmployer") //(Tested)
	public ResponseEntity<?> insertEmployer(
			
			@RequestBody /*@Valid*/ employerInsertRequest employerRequest
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
//		AuthResponse res=null;
		if(reqUSer!=null && reqUSer.getUserType().name().equals("Admin"))
		{
			employerDTO employerDto=new employerDTO();
			Employer employer=null;
			List<employerField> employerFields=new ArrayList<employerField>();
			
			if(employerRequest!=null)
			{

				  employerDto.setAddress(employerRequest.getAddress());
				  companyAdministrator compAdm=new companyAdministrator();
				  compAdm.setId(reqUSer.getId());
				  employerDto.setCompanyAdmin(compAdm);
				  employerDto.setCompanyAdministratorId(reqUSer.getId());
				  employerDto.setContacts(employerRequest.getContacts());
				  employerDto.setUserName(employerRequest.getUserName());
				  employerDto.setEmail(employerRequest.getEmail());
				  employerDto.setPassword(passwordEncoder.encode(employerRequest.getPassword()));
				  employerDto.setUserType(Role.Employer);
				  employerDto.setCreatedDate(LocalDateTime.now());
				  employerFields=employerRequest
						  .getEmployerFields()
						  .stream()
						  .map(employerFieldMapper::mapDtoToInsertToEmployerField)
						  .collect(Collectors.toList()); //getting employerFields From Request Body
				  employer=employerService.insert(employerDTOMapper.mapDTOToEmployer(employerDto));
//				  Authentication authentication=new UsernamePasswordAuthenticationToken(employerDto.getEmail(),employerDto.getPassword());
//				  SecurityContextHolder.getContext().setAuthentication(authentication);
//					
//					String token=jwtProvider.generateToken(authentication);
//					
//					res=new AuthResponse(token,true);
					
			}
			 
			 if(employer!=null)
			 {
				 for (int i = 0; i < employerFields.size(); i++) {
					 employerFields.get(i).setEmployer(employer);
				}
				 companyAdminService.giveEmployerFields(employerFields,10);
				 return new ResponseEntity<>(employer,HttpStatus.CREATED);
			 }
			 else 
			 {
				 return ResponseEntity.badRequest().build();
			 }

		}else 
		{
			throw new UserException("user not found for this token");
		}
			
	}
	
	/**
	 * 
	 * @Author BOB
	 * @Function Delete employer (Tested) by employer Id.
	 */
	@DeleteMapping("/deleteEmployerWithId/{id}") //(Tested)
	public ResponseEntity<?> deleteEmployer(@PathVariable Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("Admin"))
		{
			int a=companyAdminService.deleteEmployerWithId(id);
			if(a==0)
			{
				return new ResponseEntity<>("can't delete",HttpStatus.NOT_ACCEPTABLE);
			}else 
			{
				return new ResponseEntity<>("Deleted Successfully",HttpStatus.ACCEPTED);
			}
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
	/**
	 * 
	 * @author BOB 
	 * @function Create company Field  (Tested) with its skills and required qualifications
	 */
		@PostMapping("/createField")//(Tested)
		public ResponseEntity<?> insertField(
				@RequestBody @Valid companyFieldDTO companyField
				,BindingResult bind,@RequestHeader("Authorization") String jwt) throws UserException
		{
			User reqUSer=userServiceI.findUserByJwt(jwt);
			if(reqUSer!=null && reqUSer.getUserType().name().equals("Admin"))
			{
					companyField insertedCompanyField=companyFieldService
							.insertCompanyFieldAndSkillsAndQualifications(reqUSer.getId(),companyField);
					if(insertedCompanyField!=null)
					{
						return ResponseEntity.ok(convertCompanyField(insertedCompanyField));
					}else
					{
						return ResponseEntity.badRequest().body("Already exist");
					}
				
			}else
			{
				throw new UserException("User not found for this token");
			}
			
		}

	/**
	 * 
	 * @author BOB
	 * @Function Load the companyFields (Tested) for select at any page that need them in front end.
	 */
	@GetMapping("/findAllFields")//(Tested)
	public List<companyFieldDTO> findAllCompanyFields(
			BindingResult bind,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("Admin"))
		{
			List<companyField> comp=companyFieldService.findCompanyFieldsWithAdminId(reqUSer.getId());
			return comp.stream()
					.map(this::convertCompanyField)
					.collect(Collectors.toList()); 
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	
	/**
	 * @author BOB
	 * @Function Converting from companyField To companyFieldDTO.
	 */
	private companyFieldDTO convertCompanyField(companyField companyField)
	{
		return companyFieldMapper.mapCompanyFieldToDTO(companyField);
	}
	
//	
//	/**
//	 * 
//	 * @author BOB 
//	 * @Fucntion Inserting companyAdministrator (Tested) including insert of companyProfile
//	 */
//	@PostMapping("/insert")//(Tested)
//	public ResponseEntity<?> insertCompanyAdministrator(@RequestBody /*@Valid */ companyAdministrator companyAdministrator)
//	{
//		companyAdministrator added=companyAdministrator;
//		added.setCreatedDate(LocalDateTime.now());
//		companyAdministrator companyAdminAdded=companyAdminService.insert( added);
//	    return ResponseEntity.ok(companyAdminAdded);
//	
//	}
	
//	/**
//	 * 
//	 * @author BOB 
//	 * @Fucntion Inserting companyAdministrator picture (Tested).
//	 */
//	@PutMapping("/insertPicture/{id}")//(Tested)
//	public ResponseEntity<?> uploadFile(@PathVariable Long id,@RequestBody byte[] file) throws SQLException, IOException
//	{	
//		try {
//			return ResponseEntity.ok(companyAdminService.insertPicture(id,file));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//	
//
//
//	/**
//	 * 
//	 * @author BOB 
//	 * @Fucntion Find all companyAdministrators (Tested).
//	 */
//	@GetMapping("/findAll")//(Tested)
//	public List<companyAdministratorDTO> findAll()
//	{
//		List <companyAdministrator> companyAdminList=companyAdminService.findAll();
//		return companyAdminList.stream()
//				.map(this::convertCompanyAdminToDTO)
//				.collect(Collectors.toList());
//	}
	
	/**
	 * 
	 * @author BOB 
	 * @Fucntion Find companyProfile for companyAdministrator (Tested).
	 */
	@GetMapping("/findProfile")//(Tested)
	public ResponseEntity<companyProfileDTO> findCompanyProfile(
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("Admin"))
		{
			companyAdministrator comp=new companyAdministrator();
			comp.setId(reqUSer.getId());
			userProfile user = new userProfile(comp, followService);
//			System.out.println(""+user.getFollowings().get(0).getUserName());
			companyProfile compPro = companyProfileService.findByCompanyAdmin(reqUSer.getId());
			if(!user.getFollowers().isEmpty())
			{
				compPro.setFollowers(user.getFollowers());
				
			}else if(!user.getFollowings().isEmpty())
			{
				compPro.setFollowings(user.getFollowings());
			}else 
			{
				compPro.setFollowings(user.getFollowings());
				compPro.setFollowers(user.getFollowers());
			}
			return new ResponseEntity<companyProfileDTO>(convertCompanyProfile(compPro),HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
	/**
	 * 
	 * @author BOB 
	 * @Fucntion Converting companyProfile to companyProfileDTO (Helper)
	 */
	private companyProfileDTO convertCompanyProfile(companyProfile companyProfile)
	{
		
		return companyProfileMapper.mapCompanyProfileToDTO(companyProfile);
	}
	
	/**
	 * 
	 * @author BOB 
	 * @Fucntion Converting companyAdministrator to companyAdministratorDTO (Helper)
	 */
	private companyAdministratorDTO convertCompanyAdminToDTO(companyAdministrator companyAdmins)
	{
		return companyAdministratorMapper.mapCompanyAdminToDTO(companyAdmins);
	}
	
}

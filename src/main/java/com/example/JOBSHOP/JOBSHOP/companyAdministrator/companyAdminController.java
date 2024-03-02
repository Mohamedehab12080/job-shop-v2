package com.example.JOBSHOP.JOBSHOP.companyAdministrator;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;
import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.employerRequest;
import com.example.JOBSHOP.JOBSHOP.Employer.employerService;
import com.example.JOBSHOP.JOBSHOP.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyField.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.companyField.companyFieldService;
import com.example.JOBSHOP.JOBSHOP.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyProfile.companyProfileDTO;
import com.example.JOBSHOP.JOBSHOP.companyProfile.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.employerField.employerField;

import jakarta.validation.Valid;

/**
 * @author BOBO 
 * @class All company Administrator controller has been Tested
 */
@RestController 
@RequestMapping("/company")
public class companyAdminController {
 
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private companyAdminService companyAdminService;
	@Autowired
	private employerService employerService;
	@Autowired 
	private companyFieldService companyFieldService;
	@Autowired
	private companyProfileService companyProfileService;
	
//	@DeleteMapping("/deleteEmployer/{id}")
//	public int deleteEmployerWithIdQuery(@PathVariable Long id)
//	{
//		return companyAdminService.deleteEmployer(id);
//	} 
	
	
	@GetMapping("/findComapnyFields/{compId}")
	public List<companyFieldDTO> findllFieldsOfCompany(@PathVariable Long compId)
	{ 
		try {
			return companyFieldService.findCompanyFieldsWithAdminId(compId)
					.stream().map(this::convertCompanyField).collect(Collectors.toList());
		} catch (Exception e) {
			logger.info("Exception of Find company Fields : "+e);
			return null;
		}
	}
	
	/**
	 * 
	 * @Author BOB
	 * @Function company administrator gives each employer his specific List <employerField> (Tested).
	 */
	@PostMapping("/giveEmployerFields")
	public ResponseEntity<?> giveEmployerFields(@RequestBody /*@Valid*/ List<employerField> employerFields)
	{
		companyAdminService.giveEmployerFields(employerFields,10); // give employer Fields in batchs
		return ResponseEntity.ok("Employer has its fields now ");
	}
	
//	
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
	@PostMapping("/createEmployer")
	public ResponseEntity<?> insertEmployer(@RequestBody /*@Valid*/ employerRequest employerRequest)
	{
		Employer employer=null;
		List<employerField> employerFields=new ArrayList<employerField>();
		if(employerRequest!=null)
		{

			  employer=employerRequest.getEmployer(); //getting employer From Request Body
			  employerFields=employerRequest.getEmployerFields(); //getting employerFields From Request Body
			  employer=employerService.insert(employer);
		}
		 
		 if(employer!=null)
		 {
			 for (int i = 0; i <employerFields.size(); i++) {
				 employerFields.get(i).setEmployer(employer);
			}
			 companyAdminService.giveEmployerFields(employerFields,10);
		 }
		return ResponseEntity.ok(employer);
	}
	
	/**
	 * 
	 * @Author BOB
	 * @Function Delete employer (Tested) by employer Id.
	 */
	@DeleteMapping("/deleteEmployerWithId/{id}")
	public String deleteEmployer(@PathVariable Long id)
	{
		int a=companyAdminService.deleteEmployerWithId(id);
		if(a==0)
		{
			return "can't be deleted";
		}else 
		{
			return "Deleted Successfully";
		}
	}
	
	/**
	 * 
	 * @author BOB 
	 * @function Create company Field  (Tested) with its skills and required qualifications
	 */
	@PostMapping("/createField")
	public ResponseEntity<?> insertField(@RequestBody @Valid companyField companyField)
	{
		return ResponseEntity.ok(convertCompanyField(companyFieldService.insertCompanyField(companyField)));
	}
	
	/**
	 * 
	 * @author BOB
	 * @Function Load the companyFields (Tested) for select at any page that need them in front end.
	 */
	@GetMapping("/findAllFields/{id}")
	public List<companyFieldDTO> findAllCompanyFields(@PathVariable Long id)
	{
		List<companyField> comp=companyFieldService.findCompanyFieldsWithAdminId(id);
		return comp.stream()
				.map(this::convertCompanyField)
				.collect(Collectors.toList()); 
	}
	
	@PutMapping("/updateField")
	public ResponseEntity<companyFieldDTO> updateField(@RequestBody companyField companyField)
	{
		try {
			return ResponseEntity.ok(convertCompanyField(companyFieldService.updateCompanyField(companyField))); 
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * @author BOB
	 * @Function Converting from companyField To companyFieldDTO.
	 */
	private companyFieldDTO convertCompanyField(companyField companyField)
	{
		return entityToDTOMapper.mapCompanyFieldToDTO(companyField);
	}
	
	/**
	 * 
	 * @author BOB 
	 * @Fucntion Inserting companyAdministrator (Tested) including insert of companyProfile
	 */
	@PostMapping("/insert")
	public ResponseEntity<?> insertCompanyAdministrator(@RequestBody /*@Valid */ companyAdministrator companyAdministrator)
	{
		return ResponseEntity.ok(companyAdminService.insert(companyAdministrator));
	}
	
	/**
	 * 
	 * @author BOB 
	 * @Fucntion Inserting companyAdministrator picture (Tested).
	 */
	@PutMapping("/insertPicture/{id}")
	public ResponseEntity<?> uploadFile(@PathVariable Long id,@RequestBody byte[] file) throws SQLException, IOException
	{	
		try {
			return ResponseEntity.ok(companyAdminService.insertPicture(id,file));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	

	@PutMapping("/update") // (Tested)
	public ResponseEntity<companyAdministratorDTO> updateAdministrator(@RequestBody companyAdministrator companyAdmin) // sending company admin id in the object 
	{	
		try {
			return ResponseEntity.ok(convertCompanyAdminToDTO(companyAdminService.update(companyAdmin))); 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 
	 * @author BOB 
	 * @Fucntion Find all companyAdministrators (Tested).
	 */
	@GetMapping("/findAll")
	public List<companyAdministratorDTO> findAll()
	{
		List <companyAdministrator> companyAdminList=companyAdminService.findAll();
		return companyAdminList.stream()
				.map(this::convertCompanyAdminToDTO)
				.collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @author BOB 
	 * @Fucntion Find companyProfile for companyAdministrator (Tested).
	 */
	@GetMapping("/findProfile/{id}") 
	public companyProfileDTO findCompanyProfile(@PathVariable Long id)
	{
		return convertCompanyProfile(companyProfileService.findByCompanyAdmin(id));
	}
	
	/**
	 * 
	 * @author BOB 
	 * @Fucntion Converting companyProfile to companyProfileDTO (Helper)
	 */
	private companyProfileDTO convertCompanyProfile(companyProfile companyProfile)
	{
		return entityToDTOMapper.mapCompanyProfileToDTO(companyProfile);
	}
	
	/**
	 * 
	 * @author BOB 
	 * @Fucntion Converting companyAdministrator to companyAdministratorDTO (Helper)
	 */
	private companyAdministratorDTO convertCompanyAdminToDTO(companyAdministrator companyAdmins)
	{
		return entityToDTOMapper.mapCompanyAdminToDTO(companyAdmins);
	}
	
}

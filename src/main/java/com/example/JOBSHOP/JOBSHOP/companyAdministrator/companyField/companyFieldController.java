package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.service.companyFieldServiceInterface;

@RestController
@RequestMapping("/companyField")
public class companyFieldController {

	@Autowired
	private companyFieldServiceInterface companyFieldServiceI;
	
	@GetMapping("/findAll/{id}") // (Tested)
	public List<companyFieldDTO> getCompanyFieldsByAdminId(@PathVariable("id") Long id)
	{
		List<companyField> companyFieldList=companyFieldServiceI.findCompanyFieldsWithAdminId(id);
		return companyFieldList.stream()
				.map(this::convertCompanyfieldToDto)
				.collect(Collectors.toList());
	}
	private companyFieldDTO convertCompanyfieldToDto(companyField companyField)
	{
		return companyFieldMapper.mapCompanyFieldToDTO(companyField); // map to companyField Dto.
	}
	
	@DeleteMapping("/delete/{id}")// (Tested)
	public String deleteCompanyField(@PathVariable("id") Long id)
	{
		return companyFieldServiceI.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	public String updateCompanyField(@PathVariable("id") Long id ,@RequestBody companyField companyField)
	{
		return companyFieldServiceI.updateCompanyField(id,companyField);
	}
	
	
}

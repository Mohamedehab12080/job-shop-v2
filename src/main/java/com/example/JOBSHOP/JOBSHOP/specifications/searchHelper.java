package com.example.JOBSHOP.JOBSHOP.specifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.models.companyProfile;
import com.example.JOBSHOP.JOBSHOP.models.postField;
import com.example.JOBSHOP.JOBSHOP.services.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.services.companyFieldService;
import com.example.JOBSHOP.JOBSHOP.services.companyProfileService;

@Component
public class searchHelper {

	@Autowired
	private companyAdminService companyAdminService;
	
	@Autowired
	private companyFieldService companyFieldService;
	
	public companyProfile findCompanyProfile(String companyName)
	{
		return companyAdminService.findcompanyProfileIdByCompanyName(companyName);
	}
	public postField findPostFieldWithFieldName(String fieldName)
	{
		return companyFieldService.findPostFieldWithFieldName(fieldName);
	}
	
}

package com.example.JOBSHOP.JOBSHOP.Post.Specifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.service.companyFieldService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdminService;

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

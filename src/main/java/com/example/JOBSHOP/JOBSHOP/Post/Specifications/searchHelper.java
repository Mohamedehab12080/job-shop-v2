package com.example.JOBSHOP.JOBSHOP.Post.Specifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.companyField.companyFieldService;
import com.example.JOBSHOP.JOBSHOP.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyProfile.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.postField.postField;

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

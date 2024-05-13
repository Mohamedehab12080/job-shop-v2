package com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.User.DTO.userDTOMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;

public class companyAdministratorMapper {

	public static companyAdministratorDTO mapCompanyAdminToDTO(companyAdministrator companyAdmin)
	{
		List<String> companyFields=new ArrayList<String>();
		List<String> employersUserNames=new ArrayList<String>();
		companyAdministratorDTO dto=new companyAdministratorDTO();
		dto.setId(companyAdmin.getId());
		dto.setAddress(companyAdmin.getAddress());
		dto.setCompanyName(companyAdmin.getCompanyName());
		dto.setContacts(companyAdmin.getContacts());
		dto.setEmail(companyAdmin.getEmail());
		dto.setPassword(companyAdmin.getPassword());
		dto.setUserName(companyAdmin.getUserName());
		dto.setPicture(companyAdmin.getPicture());
		dto.setEmployers(employersUserNames);
		dto.setUserType(companyAdmin.getUserType());
		dto.setReq_user(companyAdmin.isReq_user());
		dto.setIs_signin_with_google(companyAdmin.isLogin_with_google());
		dto.setFollowers(userDTOMapper.toUserDTos(companyAdmin.getFollowers()));
		dto.setFollowings(userDTOMapper.toUserDTos(companyAdmin.getFollowings()));
		dto.setDescription(companyAdmin.getDescription());
		dto.setPicture(companyAdmin.getPicture());
		for(int i=0;i<companyAdmin.getCompanyFields().size();i++)
		{
			companyFields.add(companyAdmin.getCompanyFields().get(i).getFieldName());
		}
		dto.setCompanyFields(companyFields);
		for(int i=0;i<companyAdmin.getEmployers().size();i++)
		{
			employersUserNames.add(companyAdmin.getEmployers().get(i).getUserName());
		}
		dto.setEmployersUserName(employersUserNames);
		dto.setCoverImage(companyAdmin.getCoverImage());
		dto.setGender(companyAdmin.getGender());
		return dto;
	}
	
	public static companyAdministrator mapDTOToCompanyAdmin(companyAdministratorDTO companyAdmin)
	{
		companyAdministrator dto=new companyAdministrator();
		dto.setDescription(companyAdmin.getDescription());
		dto.setId(companyAdmin.getId());
		dto.setAddress(companyAdmin.getAddress());
		dto.setCompanyName(companyAdmin.getCompanyName());
		dto.setContacts(companyAdmin.getContacts());
		dto.setCreatedDate(companyAdmin.getCreatedDate());
		dto.setEmail(companyAdmin.getEmail());
		dto.setPassword(companyAdmin.getPassword());
		dto.setUserName(companyAdmin.getUserName());
		dto.setReq_user(companyAdmin.isReq_user());
		dto.setFollowers(userDTOMapper.toUsers(companyAdmin.getFollowers()));
		dto.setFollowings(userDTOMapper.toUsers(companyAdmin.getFollowings()));
		dto.setLogin_with_google(companyAdmin.isIs_signin_with_google());
//		dto.setCompanyFields(companyAdmin.getCompanyFields());
//		dto.setEmployers(companyAdmin.getEmployers());
		dto.setUserType(companyAdmin.getUserType());
		dto.setGender(companyAdmin.getGender());
		dto.setCoverImage(companyAdmin.getCoverImage());
		return dto;
	}
}

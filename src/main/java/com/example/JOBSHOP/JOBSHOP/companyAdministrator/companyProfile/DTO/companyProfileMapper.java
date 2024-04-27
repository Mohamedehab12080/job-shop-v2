package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.DTO;


import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.User.DTO.UserDTO;
import com.example.JOBSHOP.JOBSHOP.User.DTO.userDTOMapper;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;

public class companyProfileMapper {

	public static companyProfileDTO mapCompanyProfileToDTO(companyProfile Profile)
	{
		companyProfileDTO dto=new companyProfileDTO();
		companyAdministrator companyAdmin=Profile.getCompanyAdmin();
		List<String> companyFields=new ArrayList<String>();
		List<UserDTO>userList=new ArrayList<UserDTO>();
		List<UserDTO>userList2=new ArrayList<UserDTO>();
		dto.setCompanyName(companyAdmin.getCompanyName());
		dto.setAdminUserName(companyAdmin.getUserName());
		dto.setContacts(companyAdmin.getContacts());
		dto.setCompanyAdmin(companyAdmin);
		dto.setId(Profile.getId());
		for (companyField compField:Profile.getCompanyAdmin().getCompanyFields())
		{
			companyFields.add(compField.getFieldName());
		}
		
		if(Profile.getFollowers()!=null)
		{
			for(User user:Profile.getFollowers())
			{
				userList.add(convertUserTODTO(user));
			}
			dto.setFollowers(userList);
			dto.setFollowersCount(Profile.getFollowers().size());
		}else if(Profile.getFollowings()!=null)
		{
			for(User user:Profile.getFollowings())
			{
				userList2.add(convertUserTODTO(user));
			}
			dto.setFollowing(userList2);
			dto.setFollowingsCount(Profile.getFollowings().size());
		}else 
		{
			for(User user:Profile.getFollowings())
			{
				userList2.add(convertUserTODTO(user));
			}
			dto.setFollowing(userList2);
			dto.setFollowingsCount(Profile.getFollowings().size());
			for(User user:Profile.getFollowers())
			{
				userList.add(convertUserTODTO(user));
			}
			dto.setFollowers(userList);
			dto.setFollowersCount(Profile.getFollowers().size());

		}
		dto.setFields(companyFields);
		dto.setPicture(companyAdmin.getPicture());
		return dto;
	}
	
	private static UserDTO convertUserTODTO(User user)
	{
		return userDTOMapper.mapDTOTOUserForFollow(user);
	}
	
	public static companyProfile mapDTOTOCompanyProfile(companyProfileDTO dto)
	{
		companyProfile comp=new companyProfile();
		List<User>userList=new ArrayList<User>();
		List<User>userList2=new ArrayList<User>();
		comp.setId(dto.getAdminId());
		comp.setPosts(dto.getPosts());
		comp.setCompanyAdmin(dto.getCompanyAdmin());
		if(dto.getFollowers()!=null)
		{
			for(UserDTO user:dto.getFollowers())
			{
				userList.add(convertDTOTOUser(user));
			}
			comp.setFollowers(userList);
		}else if(dto.getFollowing()!=null)
		{
			for(UserDTO user:dto.getFollowing())
			{
				userList2.add(convertDTOTOUser(user));
			}
			comp.setFollowings(userList2);
		}else 
		{
			for(UserDTO user:dto.getFollowing())
			{
				userList2.add(convertDTOTOUser(user));
			}
			comp.setFollowings(userList2);
			for(UserDTO user:dto.getFollowers())
			{
				userList.add(convertDTOTOUser(user));
			}
			comp.setFollowers(userList);

		}

		comp.setPosts(dto.getPosts());
		return comp;
	}
	
	private static User convertDTOTOUser(UserDTO dto)
	{
		return userDTOMapper.mapDTOTOUserForFollow(dto);
	}
}

package com.example.JOBSHOP.JOBSHOP.User.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.User.model.User;

public class userDTOMapper {

	public static UserDTO mapDTOTOUserForFollow(User user)
	{
		UserDTO dto=new UserDTO();
		dto.setAddress(user.getAddress());
		dto.setEmail(user.getEmail());
		dto.setId(user.getId());
		dto.setPicture(user.getPicture());
		dto.setUserName(user.getUserName());
		dto.setIs_signin_with_google(user.isLogin_with_google());
		dto.setReq_user(user.isReq_user());
		dto.setFollowers(toUserDTos(user.getFollowers()));
		dto.setFollowings(toUserDTos(user.getFollowings()));
		dto.setUserType(user.getUserType());
		dto.setGender(user.getGender());
		return dto;
	}
	
	public static User mapDTOTOUserForFollow(UserDTO dto)
	{
		User user=new User();
		user.setAddress(dto.getAddress());
		user.setEmail(dto.getEmail());
		user.setId(dto.getId());
		user.setPicture(dto.getPicture());
		user.setUserName(dto.getUserName());
		user.setLogin_with_google(dto.isIs_signin_with_google());
		user.setReq_user(dto.isReq_user());
		user.setFollowers(toUsers(dto.getFollowers()));
		user.setFollowings(toUsers(dto.getFollowers()));
		user.setUserType(dto.getUserType());
		user.setGender(dto.getGender());
		return user;
	}
	
	public static List<User> toUsers(List<UserDTO> followers)
	{
		List<User> users=new ArrayList<User>();
		
		for(UserDTO user:followers)
		{
			User usertype=new User();
			usertype.setAddress(user.getAddress());
			usertype.setEmail(user.getEmail());
			usertype.setId(user.getId());
			usertype.setPicture(user.getPicture());
			usertype.setUserName(user.getUserName());
			usertype.setLogin_with_google(user.isIs_signin_with_google());
			usertype.setReq_user(user.isReq_user());	
			usertype.setGender(user.getGender());
			users.add(usertype);
		}
		return users;
	}
	
	public static List<UserDTO> toUserDTos(List<User> followers)
	{
		List<UserDTO> userDtos=new ArrayList<UserDTO>();
		
		for(User user:followers)
		{
			UserDTO dto=new UserDTO();
			dto.setAddress(user.getAddress());
			dto.setEmail(user.getEmail());
			dto.setId(user.getId());
			dto.setPicture(user.getPicture());
			dto.setUserName(user.getUserName());
			dto.setIs_signin_with_google(user.isLogin_with_google());
			dto.setReq_user(user.isReq_user());	
			dto.setGender(user.getGender());
			userDtos.add(dto);
		}
		return userDtos;
	}
	
	
}

package com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.User.DTO.UserDTO;
import com.example.JOBSHOP.JOBSHOP.User.DTO.userDTOMapper;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.Follow;

public class followDTOMapper {

	public static followDTO mapFollowToDTO(Follow follow)
	{
		followDTO dto=new followDTO();
		dto.setCreatedBy(follow.getCreatedBy());
		dto.setCreatedDate(follow.getCreatedDate());
		dto.setLastModifiedDate(follow.getLastModifiedDate());
		dto.setLastModifiedBy(follow.getLastModifiedBy());
		dto.setId(follow.getId());
		dto.setStatuseCode(follow.getStatusCode());
		dto.setFollower(userDTOMapper.mapDTOTOUserForFollow(follow.getFollower()));
		dto.setFollowing(userDTOMapper.mapDTOTOUserForFollow(follow.getFollowing()));
		return dto;
	}
	
}

package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.service.postServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.service.userUtils;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdministratorServiceInterface;

@RestController
@RequestMapping("/api/companyProfile")
public class companyProfileController {

	@Autowired
	private companyAdministratorServiceInterface companyAdminServiceI;
	@Autowired
	private followService followService;
	
	@Autowired
	private userServiceInterface userServiceI;
	@Autowired
	private companyProfileService companyProfileService;
	
	@Autowired
	private postServiceInterface postServiceI;
	
	@GetMapping("/getInfo/{companyAdminId}")
	public ResponseEntity<companyProfileResponse> getAllCompanyDataWithAdminId(
				@PathVariable Long companyAdminId,
				@RequestHeader("Authorization") String jwt
			)throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			boolean isrequestUser=false;
			companyAdministrator companyAdmin=companyAdminServiceI.findById(companyAdminId);
			companyProfile profile=new companyProfile(companyAdmin,followService);
			companyProfile profile2=companyProfileService.findByCompanyAdmin(companyAdminId);
			if(user.getId() == companyAdminId){
				isrequestUser=true;
			}else 
			{
				isrequestUser=false;
			}
			companyAdministratorDTO companyAdministratorDTO=companyAdministratorMapper.mapCompanyAdminToDTO(companyAdmin);
			companyAdministratorDTO.setReq_user(userUtils.isReqUser(user,companyAdmin));
			companyAdministratorDTO.setFollowed(userUtils.isFollowedByReqUser(user, companyAdmin));
//			skillsAndQualificationsRequest skillsAndQualificationsRequest=jobSeekerService.getJobSeekerSkillsAndQualificaitonsByJobSeekerId(jobSeekerId);
			companyProfileResponse response=new companyProfileResponse(
					profile,
					isrequestUser,
					companyAdministratorDTO,
					postServiceI
					.findByCompanyProfile(profile2.getId())
					.stream()
					.map(postMapper::mapPostTODTO)
					.collect(Collectors.toList())); 
			return new ResponseEntity<companyProfileResponse>(response,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
	
	}
}

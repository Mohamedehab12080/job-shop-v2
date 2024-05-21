package com.example.JOBSHOP.JOBSHOP.Post.Specifications;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.service.postRepository;
import com.example.JOBSHOP.JOBSHOP.Post.service.postServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdministratorServiceInterface;

@Component
public class searchHelper {
//
//	private final companyAdministratorServiceInterface companyAdminService;
//
//	private final postRepository postServiceI;
//	
//    public searchHelper(companyAdministratorServiceInterface companyAdminService2,postRepository postServiceI) {
//        this.companyAdminService = companyAdminService2;
//        this.postServiceI=postServiceI;
//    }
////	@Autowired
////	private postService postService;
////	
//	public companyProfile findCompanyProfile(String companyName) throws UserException
//	{
//		return companyAdminService.findcompanyProfileIdByCompanyName(companyName);
//	}
//	public List<Post> findAllPostsForTheFieldName(String fieldName)
//	{
////		return null;
//		return postServiceI.findByPostFieldsFieldFieldName(fieldName);
//	}
}

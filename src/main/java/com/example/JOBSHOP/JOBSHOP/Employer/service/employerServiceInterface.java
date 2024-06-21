package com.example.JOBSHOP.JOBSHOP.Employer.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.employerProfile;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Registration.controllers.registerUserRequest;

public interface employerServiceInterface {

	Employer insert(Employer employer);

	Employer getReferenceById(Long id);

	List<Employer> findAll();

	Employer findById(Long id);

	employerDTO update(Long id,registerUserRequest t);

	void deleteById(Long id);

	Post createAPost(Employer emp, postDTO post);

	List<Post> findPostsByEmployerId(Long id);

	List<Employer> findAllWithCompanyAdministrator(Long id);

	Employer insertPicture(Long id, String picture);

	Post createAPostWithJobs(Employer user, postDTO postDTO);

}

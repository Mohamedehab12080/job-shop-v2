package com.example.JOBSHOP.JOBSHOP.services;

import java.util.ArrayList;
import com.example.JOBSHOP.JOBSHOP.repositories.postRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.DTOImpl.entityToDTOMapper;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerDTO;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.repositories.employerRepository;

@Service
public class employerService {

	@Autowired
	private employerRepository employerRepository;
	@Autowired
	private postRepository postRepository;
	
	private List<employerDTO> employerDTOList;
	
	public List<Post> findByEmpId(Long id)
	{
		return postRepository.findByEmployerId(id);
	}
	
	public Post createAPost(Post post)
	{
		return postRepository.save(post);
	}
	public List<Employer> findAll()
	{
		return employerRepository.findAll();
	}
	public List<Employer> findAllWithCompanyAdministrator(Long id)
	{
		return employerRepository.findByCompanyAdministratorId(id);
	}
	public Employer insertEmployer(Employer employer)
	{
		return employerRepository.save(employer);
	}
	public Employer findById(Long id)
	{
		return employerRepository.findById(id).get();
	}
}

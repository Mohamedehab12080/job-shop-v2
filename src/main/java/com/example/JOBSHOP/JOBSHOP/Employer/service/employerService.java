package com.example.JOBSHOP.JOBSHOP.Employer.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTOMapper;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.service.employerFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;
import com.example.JOBSHOP.JOBSHOP.Post.postField.DTO.postFieldDTO;
import com.example.JOBSHOP.JOBSHOP.Post.postField.DTO.postFieldMapper;
import com.example.JOBSHOP.JOBSHOP.Post.postField.service.postFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.service.postServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Registration.controllers.registerUserRequest;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJob;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.companyFieldJobSkill;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.service.companyFieldJobServiceInterface;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.service.companyFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;
import com.example.JOBSHOP.JOBSHOP.degrees.service.qualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.jobQualificationModel;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.DTO.jobQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.DTO.jobQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.service.jobQualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.jobSkillModel;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.DTO.jobSkillModelDTOMapper;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.service.jobSkillModelServiceInterface;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.service.jobServiceInterface;
import com.example.JOBSHOP.JOBSHOP.location.location;
import com.example.JOBSHOP.JOBSHOP.location.service.locationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;
import com.example.JOBSHOP.JOBSHOP.skills.service.skillServiceInterface;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class employerService implements employerServiceInterface{

	@Autowired
	private employerRepository employerRepository;
	
	@Autowired
	private postServiceInterface postServiceI;
	
	@Autowired
	private postFieldServiceInterface postFieldServiceI;

	@Autowired
	private employerFieldServiceInterface employerFieldServiceI;
	
	@Autowired
	private companyProfileService companyProfileService;
	
	@Autowired
	private locationServiceInterface locationServiceI;
	
	@Autowired
	private skillServiceInterface skillServiceI;
	
	@Autowired
	private jobServiceInterface jobServiceI;
	
	@Autowired
	private companyFieldServiceInterface companyFieldServiceI;
	
	@Autowired
	private companyFieldJobServiceInterface companyFieldJobServiceI;
	
	@Autowired
	private jobSkillModelServiceInterface jobSkillModelServiceI;
	
	@Autowired
	private jobQualificationServiceInterface jobQualificationServiceI;
	
	@Autowired
	private qualificationServiceInterface qualificationServiceI;
	
	@Override
	public Employer insert(Employer employer)
	{
		return employerRepository.save(employer);
	}
	
	@Override
	public Employer getReferenceById(Long id)
	{
		return employerRepository.getReferenceById(id);
	}
   
	@Override
	public List<Employer> findAll()
	{
		return employerRepository.findAll();
	}
	
	
	@Override
	public Employer findById(Long id)
	{
		Optional<Employer> finded=employerRepository.findById(id);
		if(finded.isPresent())
		{
			return finded.get();
		}else 
		{
			return null;
		}
		
	}
	
//	public void updateEntityStatus(Application t)
//	{
//		applicationRepository.updateEntity(t.getId(),t.getStatusCode()); 
//	}
	
	@Override
	@Transactional
	public employerDTO update(Long id,registerUserRequest t)
	{
		Employer oldEmployer=findById(id);
		if(oldEmployer!=null)
		{
			if(t.getUserName() !=null )
			{
				oldEmployer.setUserName(t.getUserName());
			}
			
			if(t.getAddress()!=null)
			{
				oldEmployer.setAddress(t.getAddress());
			}

			if(t.getPicture()!=null)
			{
				oldEmployer.setPicture(t.getPicture());
			}
			
			if(t.getCoverImage()!=null)
			{
				oldEmployer.setCoverImage(t.getCoverImage());
			}
			
			if(t.getGender()!=null)
			{
				oldEmployer.setGender(t.getGender());
			}
			
			Employer inserted =employerRepository.save(oldEmployer);
			employerDTO dto=employerDTOMapper.mapEmployerToDTO(oldEmployer);
			dto.setReq_user(true);
			return dto;
		}else 
		{
			return null;
		}
	}
	
	@Override
	public void deleteById(Long id)
	{
		Employer t=getReferenceById(id);
		if(t!=null)
		{
			employerRepository.deleteById(id);
		}
	}
	
	
	/*
	 @Override
public Post createAPost(Post post) {
    Post postforInsert = post;
    List<postField> postFields = new ArrayList<>();
    
    // Cache for skills and qualifications to reduce database queries
    Map<Long, Set<String>> cachedSkills = new HashMap<>();
    Map<Long, Set<String>> cachedQualifications = new HashMap<>();
    
    for (postField postF : postforInsert.getPostFields()) {
        if (postF.getSkills() == null) {
            Set<String> skills = cachedSkills.getOrDefault(postF.getId(), new HashSet<>());
            if (skills.isEmpty()) {
                // Fetch skills from cache or database if not cached
                for (companyFieldSkill skill : postF.getEmployerField().getCompanyField().getCompanyFieldSkills()) {
                    skills.add(skill.getCompanyFieldSkill().getSkillName());
                }
                cachedSkills.put(postF.getId(), skills);
            }
            postF.setSkills(new ArrayList<>(skills));
        }
        
        if (postF.getQualifications() == null) {
            Set<String> qualifications = cachedQualifications.getOrDefault(postF.getId(), new HashSet<>());
            if (qualifications.isEmpty()) {
                // Fetch qualifications from cache or database if not cached
                for (companyFieldQualification qual : postF.getEmployerField().getCompanyField().getCompanyFieldQualifications()) {
                    qualifications.add(qual.getQualification().getQualificationName());
                }
                cachedQualifications.put(postF.getId(), qualifications);
            }
            postF.setQualifications(new ArrayList<>(qualifications));
        }
        
        postFields.add(postF);
    }

    postforInsert.setPostFields(postFields);
    return postService.insert(postforInsert);
}
//
//	 */
	@Override
	@Transactional
	public Post createAPost(Employer user,postDTO postDTO) {
	    try {
	    	
	    	postDTO.setProfileId(
	    			companyProfileService
	    			.findByCompanyAdmin
	    			(user.getCompanyAdmin().getId())
	    			.getId());
	    	System.out.println("The Id Of Profile  : "+postDTO.getProfileId());
	    	postDTO.setEmployerId(user.getId());
	    	System.out.println("The ID of Employer : "+user.getId());
	        Post post = convertDTOTOPOST(postDTO);
	        
	        System.out.println("The Employer id from post Dto : "+postDTO.getEmployerId());
	        
	        postField postField=postDTO.getPostField2();
	        	
	        Set<String> skills = new HashSet<>();
	        Set<String> qualifications = new HashSet<>();
	          employerField employerField = employerFieldServiceI.findById(user.getId(), postDTO.getField());
		        System.out.println("The user id and Field id  : "+user.getId()+" : : : "+postDTO.getField());

	            if (postField.getSkills().isEmpty()) {		
	                employerField
	                .getCompanyField()
	                .getCompanyFieldSkills()
	                .forEach(
	                		skill ->
	                			skills
	                				.add(skill
	                						.getCompanyFieldSkill()
	                						.getSkillName()));
	                postField.setSkills(new ArrayList<>(skills));
	            }else 
	            {
	            	postField.getSkills().forEach(skill-> skills.add(skill));
	                postField.setSkills(new ArrayList<>(skills));
	            }
	            
//	            if (postField.getQualifications().isEmpty()) {
//	                employerField.getCompanyField().getCompanyFieldQualifications().forEach(qual -> qualifications.add(qual.getQualification().getQualificationName()));
//	                postField.setQualifications(new ArrayList<>(qualifications));
//	            }
//	            else 
//	            {
//	            	postField.getQualifications().forEach(qual-> qualifications.add(qual));
//	                postField.setQualifications(new ArrayList<>(qualifications));
//	            }
	            
	     
	            	
	     	        postFieldServiceI.insert(postField);
	     	        post.setPostFields(postField);
	     	       if(post.getLocation() !=null && !post.getLocation().isEmpty())
	     			{
	     				location loc=locationServiceI.findByValue(post.getLocation());
	     				if(loc ==null)
	     				{
	     					location locToInsert=new location();
	     					locToInsert.setLocationValue(post.getLocation());
	     					locationServiceI.insert(locToInsert);
	     				}
	     			}
	     	       
	     	       Post insertedPost = postServiceI.insert(post);
	     	       
	     	      if (!skills.isEmpty()) {
	  	        	post.setTitle(post.getTitle() + "{ " + skills.stream().collect(Collectors.joining(", "))+" }");
	                  postServiceI.updatePostForCreate(post);
	  	        }
	     	      String csvFilePath = "D:\\Programming\\Springboot\\GraduationProject\\JOBSHOP\\src\\main\\java\\com\\example\\JOBSHOP\\JOBSHOP\\Employer\\service\\output2.csv";
			        String[] newRowData = {""+insertedPost.getId(),postDTO.getTitle(), String.join(",", postDTO.getSkills()), "in site"};
		        		System.out.println("data for insert : :  : "+Arrays.toString(newRowData));
			        try {
			        FileReader fileReader = new FileReader(csvFilePath);
		            List<String[]> csvData=new ArrayList<String[]>();
		            try (CSVReader cvReader = new CSVReaderBuilder(fileReader).build()) {
		                csvData = cvReader.readAll();
		            }
		            
		            // Append new data to the CSV data
		            csvData.add(newRowData);
		            // Write updated data back to the CSV file
		            FileWriter fileWriter = new FileWriter(csvFilePath);
		            try (CSVWriter csvWriter = new CSVWriter(fileWriter)) {
		                csvWriter.writeAll(csvData);
		            }

		            System.out.println("New row added successfully.");

		        } catch (IOException | CsvValidationException e) {
		            System.out.println("ERror : "+e.getMessage());
		        }

	        return post;
	    } catch (Exception e) {
	        System.out.println("Exception from createAPost method: " + e);
	        return null;
	    }
	}
//	
//
//	@Override
//	@Transactional
//	public Post createAPostWithJobs(Employer user,postDTO postDTO) {
//	    try {
//	    	
//	    	postDTO.setProfileId(
//	    			companyProfileService
//	    			.findByCompanyAdmin
//	    			(user.getCompanyAdmin().getId())
//	    			.getId());
//	    	postDTO.setEmployerId(user.getId());
//	        Post post = convertDTOTOPOST(postDTO);
//	        
//	        
//	        postField postField=postDTO.getPostField2();
//	        	
//	        Set<String> skills = new HashSet<>();
//	        Set<String> qualifications = new HashSet<>();
//	        employerField employerField = employerFieldServiceI.findById(user.getId(), postDTO.getField());
//
//	        jobModel jobModel=jobServiceI.findById(postDTO.getJobId());
//	        
//	        System.out.println("Field Id : "+postField.getField().getId()+" user ID : "+user.getCompanyAdmin().getId());
//	        
//	        companyField compF=companyFieldServiceI.findByFieldIdAndCompanyId(postField.getField().getId(),user.getCompanyAdmin().getId());
//	       
//	        
//	        if(jobModel!=null)
//	        {
//	        	
//	        }else 
//	        {
//	        	jobModel jobModelForInsert=new jobModel();
//	        	jobModelForInsert.setName(postDTO.getJobName());
//	        	jobModel=jobServiceI.insert(jobModelForInsert);
//	        }
//	        	postField.setJobModel(jobModel);
//	        	
//	        	
//	        if(compF!=null)
//        	{
//	        	System.out.println("company Field Returned ");
//    	        companyFieldJob companyFieldJob=companyFieldJobServiceI.findByJobModelIdAndCompanyFieldId(jobModel.getId(),compF.getId());
//    	        if(companyFieldJob!=null)
//    	        {
//    	        	System.out.println("company Field Job Returned ");
//    	        }else
//    	        {
//    	        	companyFieldJob companyFieldJobForInsert=new companyFieldJob();
//    	        	companyFieldJobForInsert.setCompanyField(compF);
//    	        	companyFieldJobForInsert.setJobModel(jobModel);
//    	        	companyFieldJob=companyFieldJobServiceI.insert(companyFieldJobForInsert);
//    	        }
//    	       
//    	            	        
//    	            	        
//    	        if (postField.getSkills().isEmpty()) {
//    	        		jobSkillModelServiceI.findByJobModelId(jobModel.getId()).stream().map(jobSkillModelDTOMapper::mapJobSkillModelToDTO).collect(Collectors.toList()).forEach(skill->skills.add(skill.getSkillName()));;
//    	                postField.setSkills(new ArrayList<>(skills));
//    	            }else 
//    	            {
//    	            	postField.getSkills().forEach(skill-> skills.add(skill));
//    	            	for(String skill:skills)
//    	    	        {
//    	    	        	Skill skillObj=skillServiceI.findByName(skill);
//    	    	        	if(skillObj!=null)
//    	    	        	{
//    	    	        		
//    	    	        	}else 
//    	    	        	{
//    	    	        		Skill skillToInsert=new Skill();
//    	    	        		skillToInsert.setSkillName(skill);
//    	    	        		skillObj=skillServiceI.insertForJobSeekerOperation(skillObj);
//    	    	        	}
//    	    	        	
//    	    	        	jobSkillModel jobSkillModel= jobSkillModelServiceI.findByJobModelIdAndSkillId(jobModel.getId(),skillObj.getId());
//    	    	        	if(jobSkillModel!=null)
//    	    	        	{
//    	    	        		
//    	    	        	}else
//    	    	        	{
//    	    	        		jobSkillModel jobSkillModelForInsert=new jobSkillModel();
//    	    	        		jobSkillModelForInsert.setJobModel(jobModel);
//    	    	        		jobSkillModelForInsert.setSkill(skillObj);
//    	    	        		jobSkillModel=jobSkillModelServiceI.insert(jobSkillModelForInsert);
//    	    	        	}
//    	    	        }
//    	    	        
//    	                postField.setSkills(new ArrayList<>(skills));
//    	            }
//    	            
//    	            if (postField.getQualifications().isEmpty()) {
//    	            	jobQualificationServiceI.findByJobModelId(jobModel.getId()).stream().map(jobQualificationMapper::mapJobQualificationToDTO).collect(Collectors.toList()).forEach(qual -> qualifications.add(qual.getQualificationName()));
//    	                postField.setQualifications(new ArrayList<>(qualifications));
//    	            }
//    	            else 
//    	            {
//    	            	postField.getQualifications().forEach(qual-> qualifications.add(qual));
//    	            	for(String qual:qualifications)
//    	    	        {
//    			        	System.out.println("company Qualifications Sent : "+qual);
//    	    	        	Qualification qualificationObj=qualificationServiceI.findByName(qual);
//    	    	        	if(qualificationObj!=null)
//    	    	        	{
//
//    	    	        	}else
//    	    	        	{
//    	    	        		Qualification qualific=new Qualification();
//    	    	        		qualific.setQualificationName(qual);
//    	    	        		qualificationObj=qualificationServiceI.insert(qualificationObj);
//    	    	        	}
//    	    	        	
//    	    	        	jobQualificationModel jobQualModel=jobQualificationServiceI.findByJobModelIdAndQualificationId(jobModel.getId(),qualificationObj.getId());
//    	    	        	if(jobQualModel!=null)
//    	    	        	{
//    	    	        		
//    	    	        	}else 
//    	    	        	{
//    	    	        		jobQualificationModel jobQualModelInsert=new jobQualificationModel();
//    	    	        		jobQualModelInsert.setJobModel(jobModel);
//    	    	        		jobQualModelInsert.setQualification(qualificationObj);
//    	    	        		jobQualModel=jobQualificationServiceI.insert(jobQualModelInsert);
//    	    	        	}
//    	    	        }
//
//    	                postField.setQualifications(new ArrayList<>(qualifications));
//    	            }
//
//    	     	        postFieldServiceI.insert(postField);
//    	     	        post.setPostFields(postField);
//    	     	       if(post.getLocation() !=null && !post.getLocation().isEmpty())
//    	     			{
//    	     				location loc=locationServiceI.findByValue(post.getLocation());
//    	     				if(loc ==null)
//    	     				{
//    	     					location locToInsert=new location();
//    	     					locToInsert.setLocationValue(post.getLocation());
//    	     					locationServiceI.insert(locToInsert);
//    	     				}
//    	     			}
//    	     	       
//    	     	       Post insertedPost = postServiceI.insert(post);
//    	     	       
//    	     	      if (!skills.isEmpty()) {
//    	  	        	post.setTitle(post.getTitle() + "{ " + skills.stream().collect(Collectors.joining(", "))+" }");
//    	                  postServiceI.updatePostForCreate(post);
//    	  	        }
//    	     	      String csvFilePath = "D:\\Programming\\Springboot\\GraduationProject\\JOBSHOP\\src\\main\\java\\com\\example\\JOBSHOP\\JOBSHOP\\Employer\\service\\output.csv";
//    			        String[] newRowData = {""+insertedPost.getId(),postDTO.getTitle(), String.join(",", postDTO.getSkills()), "in site"};
//    		        		System.out.println("data for insert : :  : "+Arrays.toString(newRowData));
//    			        try {
//    			        FileReader fileReader = new FileReader(csvFilePath);
//    		            List<String[]> csvData=new ArrayList<String[]>();
//    		            try (CSVReader cvReader = new CSVReaderBuilder(fileReader).build()) {
//    		                csvData = cvReader.readAll();
//    		            }
//    		            
//    		            // Append new data to the CSV data
//    		            csvData.add(newRowData);
//    		            // Write updated data back to the CSV file
//    		            FileWriter fileWriter = new FileWriter(csvFilePath);
//    		            try (CSVWriter csvWriter = new CSVWriter(fileWriter)) {
//    		                csvWriter.writeAll(csvData);
//    		            }
//
//    		            System.out.println("New row added successfully.");
//
//    		        } catch (IOException | CsvValidationException e) {
//    		            System.out.println("ERror : "+e.getMessage());
//    		        }
//
//    	        return post;
//
//        	}else 
//        	{
//        		return null;
//        	}
//	        
//	      	    } catch (Exception e) {
//	        System.out.println("Exception from createAPost method: " + e);
//	        return null;
//	    }
//	}
	
	@Override
	@Transactional
	public Post createAPostWithJobs(Employer user, postDTO postDTO) {
	    try {
	        postDTO.setProfileId(
	                companyProfileService
	                        .findByCompanyAdmin(user.getCompanyAdmin().getId())
	                        .getId()
	        );
	        postDTO.setEmployerId(user.getId());
	        Post post = convertDTOTOPOST(postDTO);

	        postField postField = postDTO.getPostField2();

	        Set<String> skills = new HashSet<>();
	        Set<String> qualifications = new HashSet<>();
	        employerField employerField = employerFieldServiceI.findById(user.getId(), postDTO.getField());

	        jobModel jobModel = jobServiceI.findById(postDTO.getJobId());

	        System.out.println("Field Id : " + postField.getField().getId() + " user ID : " + user.getCompanyAdmin().getId());

	        companyField compF = companyFieldServiceI.findByFieldIdAndCompanyId(postField.getField().getId(), user.getCompanyAdmin().getId());

	        if (jobModel == null) {
	            jobModel jobModelForInsert = new jobModel();
	            jobModelForInsert.setName(postDTO.getJobName());
	            jobModel = jobServiceI.insert(jobModelForInsert);
	        }

	        postField.setJobModel(jobModel);

	        if (compF != null) {
	            System.out.println("company Field Returned ");
	            companyFieldJob companyFieldJob = companyFieldJobServiceI.findByJobModelIdAndCompanyFieldId(jobModel.getId(), compF.getId());
	            if (companyFieldJob == null) {
	                companyFieldJob companyFieldJobForInsert = new companyFieldJob();
	                companyFieldJobForInsert.setCompanyField(compF);
	                companyFieldJobForInsert.setJobModel(jobModel);
	                companyFieldJob = companyFieldJobServiceI.insert(companyFieldJobForInsert);
	            }

	            // Handle skills
	            if (postField.getSkills().isEmpty()) {
	                jobSkillModelServiceI.findByJobModelId(jobModel.getId())
	                        .stream()
	                        .map(jobSkillModelDTOMapper::mapJobSkillModelToDTO)
	                        .collect(Collectors.toList())
	                        .forEach(skill -> skills.add(skill.getSkillName()));
	                postField.setSkills(new ArrayList<>(skills));
	            } else {
	                postField.getSkills().forEach(skill -> skills.add(skill));
	                for (String skill : skills) {
	                    Skill skillObj = skillServiceI.findByName(skill);
	                    if (skillObj == null) {
	                        Skill skillToInsert = new Skill();
	                        skillToInsert.setSkillName(skill);
	                        skillObj = skillServiceI.insertForJobSeekerOperation(skillToInsert);
	                    }

	                    jobSkillModel jobSkillModel = jobSkillModelServiceI.findByJobModelIdAndSkillId(jobModel.getId(), skillObj.getId());
	                    if (jobSkillModel == null) {
	                        jobSkillModel jobSkillModelToInsert = new jobSkillModel();
	                        jobSkillModelToInsert.setJobModel(jobModel);
	                        jobSkillModelToInsert.setSkill(skillObj);
	                        jobSkillModel = jobSkillModelServiceI.insert(jobSkillModelToInsert);
	                    }
	                }
	            }

	            // Handle qualifications
	            if (postField.getQualifications().isEmpty()) {
	                jobQualificationServiceI.findByJobModelId(jobModel.getId())
	                        .stream()
	                        .map(jobQualificationMapper::mapJobQualificationToDTO)
	                        .collect(Collectors.toList())
	                        .forEach(qual -> qualifications.add(qual.getQualificationName()));
	                postField.setQualifications(new ArrayList<>(qualifications));
	            } else {
	                postField.getQualifications().forEach(qual -> qualifications.add(qual));
	                for (String qual : qualifications) {
	                    Qualification qualificationObj = qualificationServiceI.findByName(qual);
	                    if (qualificationObj == null) {
	                        Qualification qualificationToInsert = new Qualification();
	                        qualificationToInsert.setQualificationName(qual);
	                        System.out.println("Value To insert : " + qual);
	                        qualificationObj = qualificationServiceI.insert(qualificationToInsert);
	                    }

	                    jobQualificationModel jobQualModel = jobQualificationServiceI.findByJobModelIdAndQualificationId(jobModel.getId(), qualificationObj.getId());
	                    if (jobQualModel == null) {
	                        jobQualificationModel jobQualificationModelObj = new jobQualificationModel();
	                        jobQualificationModelObj.setJobModel(jobModel);
	                        jobQualificationModelObj.setQualification(qualificationObj);
	                        jobQualificationServiceI.insert(jobQualificationModelObj);
	                    }
	                }
	            }

	            postField.setJobModel(jobModel);
	            postField = postFieldServiceI.insert(postField);

	            post.setPostFields(postField);
	            Post InsertedPost= postServiceI.insert(post);
	            if (!skills.isEmpty()) {
	  	        	post.setTitle(post.getTitle() + "{ " + skills.stream().collect(Collectors.joining(", "))+" }");
	                  postServiceI.updatePostForCreate(post);
	  	        }
	     	      String csvFilePath = "D:\\Programming\\Springboot\\GraduationProject\\JOBSHOP\\src\\main\\java\\com\\example\\JOBSHOP\\JOBSHOP\\Employer\\service\\output2.csv";
			        String[] newRowData = {""+InsertedPost.getId(),postDTO.getTitle(), String.join(",", postDTO.getSkills()), "in site"};
		        		System.out.println("data for insert : :  : "+Arrays.toString(newRowData));
			        try {
			        FileReader fileReader = new FileReader(csvFilePath);
		            List<String[]> csvData=new ArrayList<String[]>();
		            try (CSVReader cvReader = new CSVReaderBuilder(fileReader).build()) {
		                csvData = cvReader.readAll();
		            }
		            
		            // Append new data to the CSV data
		            csvData.add(newRowData);
		            // Write updated data back to the CSV file
		            FileWriter fileWriter = new FileWriter(csvFilePath);
		            try (CSVWriter csvWriter = new CSVWriter(fileWriter)) {
		                csvWriter.writeAll(csvData);
		            }

		            System.out.println("New row added successfully.");

		        } catch (IOException | CsvValidationException e) {
		            System.out.println("ERror : "+e.getMessage());
		        }

	        return post;

	        } else {
	            throw new EntityNotFoundException("Company field not found");
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to create post with jobs", e);
	    }
	}

	public class CsvHandler {

	    private static final String CSV_FILE_PATH = "D:\\Partition E\\4th Year\\Graduation project\\AI\\jobs.csv";
	    private static final String CSV_SEPARATOR = ",";

	    private String[] headers;

	    // Read CSV File
	    public List<Map<String, String>> readCSV() {
	        List<Map<String, String>> data = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
	            String line;
	            if ((line = br.readLine()) != null) {
	                headers = line.split(CSV_SEPARATOR); // Read headers from the first line
	                while ((line = br.readLine()) != null) {
	                    String[] values = line.split(CSV_SEPARATOR);
	                    System.out.println("values  Reading :"+values);
	                    Map<String, String> row = new LinkedHashMap<>();
	                    for (int i = 0; i < headers.length; i++) {
	                        row.put(headers[i], values[i]);
	                    }
	                    data.add(row);
	                }
	            }
	            System.out.println("Data reading : "+data);
	        } catch (IOException e) {
	            System.out.println("Error reading CSV: " + e.getMessage());
	        }
	        return data;
	    }

	    // Write to CSV File
	    public void writeCSV(List<Map<String, String>> data) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
	            if (!data.isEmpty()) {
	                // Write headers
	                bw.write(String.join(CSV_SEPARATOR, headers));
	                bw.newLine();
	                // Write data
	                for (Map<String, String> row : data) {
	                    List<String> rowValues = new ArrayList<>();
	                    for (String header : headers) {
	                        String value = row.get(header);
	                        // Handle list value (e.g., skills)
	                        if ("skills".equals(header) && value != null && !value.isEmpty()) {
	                            // Convert skills list to CSV string
	                            value = String.join(", ", Arrays.asList(value.split(",")));
	                        }
	                        rowValues.add(value);
	                    }
	                    bw.write(String.join(CSV_SEPARATOR, rowValues));
	                    bw.newLine();
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Error writing CSV: " + e.getMessage());
	        }
	    }

	    // Insert Data
	    public void insertData(String postId, String jobTitle, List<String> skills, String rowType) {
	        List<Map<String, String>> data = readCSV();
	        Map<String, String> newRow = new LinkedHashMap<>();
	        newRow.put("postId", postId);
	        newRow.put("Job Title", jobTitle);
	        newRow.put("skills", String.join(",", skills)); // Convert list of skills to CSV string
	        newRow.put("rowType", rowType);
	        data.add(newRow);
	        writeCSV(data);
	    }

	    
	}
	
	private postField convertPostFieldDtoToPostField(postFieldDTO dto)
	{
		return postFieldMapper.mapDTOToPostField(dto);
	}
	@Override
	public List<Post> findPostsByEmployerId(Long id)
	{
		return postServiceI.findByEmployerId(id);
	}
	
	@Override
	public List<Employer> findAllWithCompanyAdministrator(Long id)
	{
		return employerRepository.findByCompanyAdministratorId(id);
	}
	

	@Override
	 public Employer insertPicture(Long id,String picture)
	 {
	try {
		Optional<Employer> employerUpdate=employerRepository.findById(id);
		 if(employerUpdate.isPresent())
		 {
			 System.out.println("Job Seeker :" +employerUpdate.get().getEmail());
			 Employer employerForUpdate=employerUpdate.get();
			 employerForUpdate.setPicture(picture);
			 return employerRepository.save(employerForUpdate);
		 }else  
		 {
			 return null;
		 }
	} catch (Exception e) {
		return null;
	}
	 }
	
	
	private Post convertDTOTOPOST(postDTO postDto)
	{
		return postMapper.mapDTOToPost(postDto);
	}
	
}

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
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service.companyProfileService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

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
	    	postDTO.setEmployerId(user.getId());
	        Post post = convertDTOTOPOST(postDTO);
	        Post insertedPost = postServiceI.insert(post);
	        
	        postField postField=postDTO.getPostField2();
	        Set<String> skills = new HashSet<>();
	        Set<String> qualifications = new HashSet<>();
	          employerField employerField = employerFieldServiceI.findById(postDTO.getField());
	            if (postField.getSkills().isEmpty()) {
	                employerField.getCompanyField().getCompanyFieldSkills().forEach(skill -> skills.add(skill.getCompanyFieldSkill().getSkillName()));
	                postField.setSkills(new ArrayList<>(skills));
	            }else 
	            {
	            	postField.getSkills().forEach(skill-> skills.add(skill));
	                postField.setSkills(new ArrayList<>(skills));
	            }
	            
	            if (postField.getQualifications().isEmpty()) {
	                employerField.getCompanyField().getCompanyFieldQualifications().forEach(qual -> qualifications.add(qual.getQualification().getQualificationName()));
	                postField.setQualifications(new ArrayList<>(qualifications));
	            }
	            else 
	            {
	            	postField.getQualifications().forEach(qual-> qualifications.add(qual));
	                postField.setQualifications(new ArrayList<>(qualifications));
	            }
	            
	            postField.setPost(insertedPost);
	            
//	            CsvHandler csvHandler=new CsvHandler();
//		        csvHandler.insertData(""+post.getId(), ""+post.getTitle(), postDTO.getSkills() ,"in site");
		     // Read existing CSV file
	            //D:\\Programming\\Springboot\\GraduationProject\\JOBSHOP\\src\\main\\java\\com\\example\\JOBSHOP\\JOBSHOP\\Employer\\service\\
	            String csvFilePath = "D:\\Programming\\Springboot\\GraduationProject\\JOBSHOP\\src\\main\\java\\com\\example\\JOBSHOP\\JOBSHOP\\Employer\\service\\output.csv";
		        String[] newRowData = {""+post.getId(),postDTO.getTitle(), String.join(",", postDTO.getSkills()), "in site"};
	        		System.out.println("data for insert : :  : "+Arrays.toString(newRowData));
		        try {
		        FileReader fileReader = new FileReader(csvFilePath);
	            List<String[]> csvData=new ArrayList<String[]>();
	            try (CSVReader cvReader = new CSVReaderBuilder(fileReader).build()) {
	                csvData = cvReader.readAll();
	            }
	            
	            System.out.println("Csv Data : " + csvData);
	            // Append new data to the CSV data
	            csvData.add(newRowData);
	            System.out.println("Csv Data After add : " + csvData);
	            // Write updated data back to the CSV file
	            FileWriter fileWriter = new FileWriter(csvFilePath);
	            try (CSVWriter csvWriter = new CSVWriter(fileWriter)) {
	                csvWriter.writeAll(csvData);
	            }

	            System.out.println("New row added successfully.");

	        } catch (IOException | CsvValidationException e) {
	            System.out.println("ERror : "+e.getMessage());
	        }

	        if (!skills.isEmpty()) {
	        	post.setTitle(post.getTitle() + "{ " + skills.stream().collect(Collectors.joining(", "))+" }");
                postServiceI.update(post);
	        }
	        
	     	        postFieldServiceI.insert(postField);
	       
	        return post;
	    } catch (Exception e) {
	        System.out.println("Exception from createAPost method: " + e);
	        return null;
	    }
	}
//	@Override
//	@Transactional
//	public Post createAPost(Employer user, postDTO postDTO) {
//	    try {
//	    	 Long companyAdminId = user.getCompanyAdmin().getId();
//	         
//	         // Fetch the company profile ID based on the company admin ID
//	         Long profileId = companyProfileService.findByCompanyAdmin(companyAdminId).getId();
//	         
//	         // Set profileId and employerId in postDTO
//	         postDTO.setProfileId(profileId);
//	         postDTO.setEmployerId(user.getId());
//	         
//	         // Convert postDTO to Post entity and insert into database
//	         Post post = convertDTOTOPOST(postDTO);
//	         post.setEmployer(user); // Set the employer directly
//	         
//	         // Save the post entity to get its ID
//	         Post insertedPost = postServiceI.insert(post);
//	         
//	         // Prepare and insert postField related to the inserted post
//	         postField postField = postDTO.getPostField2();
//	         
//	         System.out.println("The Employer Field Id is : "+postField.getEmployerField().getId());
//	         // Retrieve employerField based on field ID from postDTO
//	         employerField employerField = employerFieldServiceI.findById(postDTO.getField());
//	         
//	         if (employerField == null) {
//	             throw new IllegalArgumentException("Employer field with ID " + postDTO.getField() + " not found");
//	         }
//	         
//	         // Populate skills and qualifications based on employerField data
//	         if (postField.getSkills().isEmpty()) {
//	             employerField.getCompanyField().getCompanyFieldSkills().forEach(skill -> postField.getSkills().add(skill.getCompanyFieldSkill().getSkillName()));
//	         }
//	         
//	         if (postField.getQualifications().isEmpty()) {
//	             employerField.getCompanyField().getCompanyFieldQualifications().forEach(qual -> postField.getQualifications().add(qual.getQualification().getQualificationName()));
//	         }
//	         
//	         // Set postField attributes and associate with post
//	         postField.setEmployerField(employerField);
//	         postField.setPost(insertedPost);
//	         
//	         // Update post title with skills (if any)
//	         if (!postField.getSkills().isEmpty()) {
//	             post.setTitle(post.getTitle() + ", " + String.join(", ", postField.getSkills()));
//	         }
//	         
//	         // Insert postField into database
//	         postFieldServiceI.insert(postField);
//	        return post;
//	    } catch (DataIntegrityViolationException e) {
//	        // Handle data integrity violation (e.g., foreign key constraint failure)
//	        // Log the error and provide meaningful feedback
//	        System.out.println("Exception from createAPost method: " + e.getMessage());
//	        throw new IllegalArgumentException("Failed to create post due to data integrity violation");
//	    } catch (Exception e) {
//	        // Handle other unexpected exceptions
//	        System.out.println("Exception from createAPost method: " + e.getMessage());
//	        throw new IllegalArgumentException("Failed to create post: " + e.getMessage());
//	    }
//	}	
	public class CsvHandler {

	    private static final String CSV_FILE_PATH = "output.csv";
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

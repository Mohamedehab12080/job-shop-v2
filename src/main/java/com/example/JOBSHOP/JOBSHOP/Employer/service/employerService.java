package com.example.JOBSHOP.JOBSHOP.Employer.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
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
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service.companyProfileService;

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
	public Employer update(Long id,Employer t)
	{
		Employer oldEmployer=findById(id);
		if(oldEmployer!=null)
		{
			if(t.getAddress()!=null)
			{
				oldEmployer.setAddress(t.getAddress());
			}
			if(t.getContacts()!=null)
			{
				oldEmployer.setContacts(t.getContacts());
			}
			if(t.getEmail()!=null)
			{
				oldEmployer.setEmail(t.getEmail());
			}
			if(t.getEmployerFields()!=null)
			{
				oldEmployer.setEmployerFields(t.getEmployerFields());
			}
			if(t.getPicture()!=null)
			{
				oldEmployer.setPicture(t.getPicture());
			}
			if(t.getUserName()!=null)
			{
				oldEmployer.setUserName(t.getUserName());
			}
			return employerRepository.save(oldEmployer);
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

	 */
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
	            
	        if (!skills.isEmpty()) {
	        	post.setTitle(post.getTitle() + ", " + skills.stream().collect(Collectors.joining(", ")));
                postServiceI.update(post);
	        }
	        
	        postFieldServiceI.insert(postField);
	        return post;
	    } catch (Exception e) {
	        System.out.println("Exception from createAPost method: " + e);
	        return null;
	    }
	}
	
	private class CsvHandler{
		
		   private static final String CSV_FILE_PATH = "data.csv";
		    private static final String CSV_SEPARATOR = ",";

		    // Read CSV File
		    public List<Map<String, String>> readCSV() {
		        List<Map<String, String>> data = new ArrayList<>();
		        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
		            String line;
		            String[] headers = br.readLine().split(CSV_SEPARATOR); // Assuming first line contains headers
		            while ((line = br.readLine()) != null) {
		                String[] values = line.split(CSV_SEPARATOR);
		                Map<String, String> row = new LinkedHashMap<>();
		                for (int i = 0; i < headers.length; i++) {
		                    row.put(headers[i], values[i]);
		                }
		                data.add(row);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        return data;
		    }

		    // Write to CSV File
		    public void writeCSV(List<Map<String, String>> data) {
		        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
		            if (!data.isEmpty()) {
		                // Write headers
		                bw.write(String.join(CSV_SEPARATOR, data.get(0).keySet()));
		                bw.newLine();
		                // Write data
		                for (Map<String, String> row : data) {
		                    bw.write(String.join(CSV_SEPARATOR, row.values()));
		                    bw.newLine();
		                }
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

		    // Insert Data
		    public void insertData(String postId, String jobTitle, String skills, String rowType) {
		        List<Map<String, String>> data = readCSV();
		        Map<String, String> newRow = new LinkedHashMap<>();
		        newRow.put("postId", postId);
		        newRow.put("Job Title", jobTitle);
		        newRow.put("skills", skills);
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
	 public Employer insertPicture(Long id,byte[] picture)
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

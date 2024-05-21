package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.service.employerFieldServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.service.employerFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Employer.service.employerServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;
import com.example.JOBSHOP.JOBSHOP.Post.postField.service.postFieldService;
import com.example.JOBSHOP.JOBSHOP.Post.postField.service.postFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.service.postServiceInterface;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.service.companyFieldQualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.service.companyFieldSkillsServiceInterface;
import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;
import com.example.JOBSHOP.JOBSHOP.degrees.service.qualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.fields.Field;
import com.example.JOBSHOP.JOBSHOP.fields.service.fieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;
import com.example.JOBSHOP.JOBSHOP.skills.service.skillServiceInterface;

import jakarta.transaction.Transactional;

@Service
public class companyFieldService implements companyFieldServiceInterface{
	 
	@Autowired 
	 private companyFieldRepository companyFieldRepository;
	 
	 @Autowired
	 private skillServiceInterface skillServiceI;
	 
	 @Autowired
	 private companyFieldSkillsServiceInterface companyFieldSkillI;
	 @Autowired
	 private postFieldService postFieldService;
	 
	 @Autowired
	 private companyFieldQualificationServiceInterface companyFieldQualificationI;
	 
	 @Autowired
	 private qualificationServiceInterface qualificationServiceI;
	 
	 @Autowired
	 private employerFieldServiceInterface employerFieldServiceI;
	 
	 @Autowired
	 private postFieldServiceInterface postFieldServiceI;
	 
	 @Autowired
	 private postServiceInterface postServiceI;
	 
	 @Autowired
	 private fieldServiceInterface fieldServiceI;
//	 public postField findPostFieldWithFieldName(String fieldName)
//	 {
//		 return postFieldService.findByEmployerField(companyFieldRepository.findIdByFieldName(fieldName));
//	 }
	     
	 	@Override
	    public companyField getReferenceById(Long id)
		{
			return companyFieldRepository.getReferenceById(id);
		}
	 	@Override
		public List<companyField> findAll()
		{
			return companyFieldRepository.findAll();
		}
		@Override
		public companyField insert(companyField t)
		{
			return companyFieldRepository.save(t);
		}
		@Override
		public companyField findById(Long id)
		{
			Optional<companyField> finded=companyFieldRepository.findById(id);
			if(finded.isPresent())
			{
				return finded.get();
			}else 
			{
				return null;
			}
			
		}
		
//		public void updateEntityStatus(Application t)
//		{
//			applicationRepository.updateEntity(t.getId(),t.getStatusCode()); 
//		}
		
		@Override
		public List<companyField> insertAll(List<companyField> entity)
		{
			return companyFieldRepository.saveAll(entity);
		}
		
		@Transactional
		@Override
		public String deleteById(Long id)
		{
			companyField t=findById(id);
			if(t!=null)
			{
				List<employerField> employerFieldList=employerFieldServiceI.findAllEmployersFieldsByCompanyFieldId(id);
				if(!employerFieldList.isEmpty())
				{
					for(employerField empField:employerFieldList)
					{
						employerFieldServiceI.deleteById(empField.getId());
					}
				}
				companyFieldRepository.deleteById(id);
				return "deleted";
			}else 
			{
				return "field not found!";
			}
		}
		
		
		
	/**
	 * @author BOBO
	 * @implNote insert companyField and skills and qualifications that doesn't exists before and these skills or qualifications will appear to all the users for use again
	 */
		@Transactional
		@Override
		public companyField insertCompanyFieldAndSkillsAndQualifications(Long companyAdminId,
				companyFieldDTO dto) {
		    // Create companyAdmin 
		    companyAdministrator companyAdmin = new companyAdministrator();
		    companyAdmin.setId(companyAdminId);
		    
		    //companyField object
		    companyField com = new companyField();
		    Field field=fieldServiceI.findByName(dto.getFieldName());
		    if(field!=null)
		    {
		    	com.setField(field);
		    }else 
		    {
		    	Field field2=new Field();
		    	field2.setFieldName(dto.getFieldName());
		    	Field insertedField=fieldServiceI.insertForCompanyOperation(field2);
		    	field=insertedField;
		    	com.setField(insertedField);
		    } 
		    com.setCompanyAdministrator(companyAdmin);
//		    com.setRequiredQualifications(dto.getRequiredQualifications());
		   if(findByFieldIdAndCompanyId(field.getId(),companyAdmin.getId())==null)
		   {
			   companyField returnedcompanyField=companyFieldRepository.save(com); //insert the companyField containing fieldName and companyAdmin.   
			// Batch insert new skills
			    Set<String> newSkills = new HashSet<>(dto.getSkills());//Set of new skills that will be inserted 
			    
			    Map<String, Skill> existingSkills = new HashMap<>(); //Map that conatains existing skills the skill name and skill object

			    if(!dto.getQualifications().isEmpty())
			    {
			    	Set<String> newQuals = new HashSet<>(dto.getQualifications());//Set of new skills that will be inserted 
				    
				    Map<String, Qualification> existingQuals = new HashMap<>(); //Map that conatains existing qual the Qual name and qual object
				   
				    List<Qualification> qualificationsToInsert = new ArrayList<>();
				    
				    for(String newQual:newQuals)
				    {
				    	Qualification qualiciationSearch=qualificationServiceI.findByName(newQual); //Search if the qualification exists
				    	if(qualiciationSearch!=null) 
				    	{
				    		existingQuals.put(qualiciationSearch.getQualificationName(),qualiciationSearch);
				    	}
				    	
				    	if (!existingQuals.containsKey(newQual)) {
				        	Qualification newQuall = new Qualification();
				        	newQuall.setQualificationName(newQual);
				            qualificationsToInsert.add(newQuall);
				        }
				    }
				    
				    
//				    for (String qualName : newQuals) 
//				    {
//				        if (!existingQuals.containsKey(newQual)) {
		//		        	qualification newQuall = new qualification();
		//		        	newQuall.setQualificationName(newQual);
		//		            qualificationsToInsert.add(newQuall);
//		        		}
//				    }
				    
				    qualificationServiceI.insertAll(qualificationsToInsert);
				    
				    // Retrieve inserted skills
				    List<Qualification> allQualifications = new ArrayList<>(existingQuals.values());
				    allQualifications.addAll(qualificationsToInsert);

				    // Insert companyFieldSkills
				    List<companyFieldQualification> comapanyFieldQuals = new ArrayList<companyFieldQualification>();
				    
				    for (Qualification qual : allQualifications) {
				        companyFieldQualification companyFieldQual = new companyFieldQualification();
				        companyFieldQual.setQualification(qual);
				        companyFieldQual.setCompanyField(com);
				        comapanyFieldQuals.add(companyFieldQual);
				    }
				    companyFieldQualificationI.insertAll(comapanyFieldQuals);

			    } // The end of check if user insert qualification or not 
			    List<Skill> skillsToInsert = new ArrayList<>();
			    if(!dto.getSkills().isEmpty()) // check fro comming skills
			    {
			    	   
				    for(String newSkill:newSkills) // Iterate over the newSkills.
				    {
				    	//find by skill name and the return value for the skillSearch
				    	Skill skillSearch= skillServiceI.findByName(newSkill);
				    	if(skillSearch!=null) //check if the skillSearch object !null
				    	{
				    		existingSkills.put(skillSearch.getSkillName(), skillSearch); //putting the skill name and skill object for the existing skills hashMap
				    	}
				    	
				    	if (!existingSkills.containsKey(newSkill)) {
				            Skill newSkillObj = new Skill();
				            newSkillObj.setSkillName(newSkill);
				            skillsToInsert.add(newSkillObj);
				        }
				    }
				    
//				    for (String skillName : newSkills) {
//				        if (!existingSkills.containsKey(skillName)) {
//				            Skill newSkill = new Skill();
//				            newSkill.setSkillName(skillName);
//				            skillsToInsert.add(newSkill);
//				        }
//				    }
				    
				    
				    skillServiceI.insertAll(skillsToInsert); // insert all new skills into the public skills table

				    // Getting The inserted skills from the existingSkills Map<skillName,skillObject>
				    List<Skill> allSkills = new ArrayList<>(existingSkills.values());
				    allSkills.addAll(skillsToInsert);// adding the inserted skills and the existing skills into the same list

				    // Insert companyFieldSkills from the allSkills
				    List<companyFieldSkill> companyFieldSkills = new ArrayList<>();
				    for (Skill skill : allSkills) {
				        companyFieldSkill companyFieldSkill = new companyFieldSkill();
				        companyFieldSkill.setCompanyFieldSkill(skill);
				        companyFieldSkill.setCompanyField(com);
				        companyFieldSkills.add(companyFieldSkill);
				    }
				    
				    companyFieldSkillI.insertAll(companyFieldSkills);
				    

			    }
			 		    // Return the inserted companyField object
			    return returnedcompanyField;

		   }else 
		   {
			   
			    return null;
		   }
		    		}
		
//		/**
//		 * 
//		 * @param companyFieldDTO 
//		 * @implNote insert companyField and companyFieldSkill and skill if not exist at skill table  
//		 */
//		public companyField insertCompanyFieldAndSkills(companyFieldDTO dto)
//		{
//			companyAdministrator companyAdmin=new companyAdministrator();
//			companyAdmin.setId(dto.getCompanyAdministratorId());
//			
//			companyField com=new companyField();
//			companyField com1=new companyField();
//			com.setFieldName(dto.getFieldName());
//			com.setCompanyAdministrator(companyAdmin);
//			com.setRequiredQualifications(dto.getRequiredQualifications());
//			companyFieldRepository.save(com);
//			
//
//			Skill skillCheckObject=null;
//			Skill skillForInsert=new Skill();
//			Skill skillForInsertCompanyField=new Skill();
//			
//			companyFieldSkill companyFieldSkill=new companyFieldSkill();
//			
//			for (String skill:dto.getSkills()) {
//				skillForInsert.setSkillName(skill);
//				skillCheckObject=skillServiceI.findByName(skill);
//				if(skillCheckObject==null)
//				{
//					skillServiceI.insert(skillForInsert); // insert skill that not found at the skill entity 
//					
//					skillForInsertCompanyField=skillServiceI.findByName(skill); // getting the inserted skill and pass it to skillforInsert Object
//					
//					companyFieldSkill.setCompanyFieldSkill(skillForInsertCompanyField);//set the company field skill including companyField id
//					
//					com1.setId(companyFieldRepository.findByFieldName(com.getFieldName()));// set the id of the companyField 
//					
//					companyFieldSkill.setCompanyField(com1); //pass the object that contains the id of the companyField
//					
//					companyFieldSkillI.insert(companyFieldSkill); //insert the company fieldSkill with (companyField id and skillId)
//				}else  
//				{
//					/*
//					 * check if the companyFieldSkill is present at the companyFieldSkill Table 
//					 * if not then insert into the companyFieldSkill
//					 * else return The skill already Exist at the companyFieldSkill table.
//					 */
//					skillForInsertCompanyField=skillServiceI.findByName(skill); // getting the inserted skill and pass it to skillforInsert Object
//					if(companyFieldSkillI.findBySkillId(skillForInsertCompanyField.getId()).isPresent())
//					{
//						// return  "The Skill: "+skill+" is already exist";
//						return null;
//					}else 
//					{
//						companyFieldSkill.setCompanyFieldSkill(skillForInsertCompanyField);//set the company field skill including companyField id
//						
//						com1.setId(companyFieldRepository.findByFieldName(com.getFieldName()));// set the id of the companyField 
//						
//						companyFieldSkill.setCompanyField(com1); //pass the object that contains the id of the companyField
//						
//						companyFieldSkillI.insert(companyFieldSkill); //insert the company fieldSkill with (companyField id and skillId)
//					}
//				}
//			}
//			return companyFieldRepository.findById(com1.getId()).get();
//		}
//	 public companyField insertCompanyField (companyField companyField)
//	 {
//		 return companyFieldRepository.save(companyField);
//	 }
	 
	 /**
	  * 
	  * @author BOBO 
	  * @function update any thing in field except companyAdministrators
	  */
	 @Transactional
	 @Override
	 public String updateCompanyField(Long id, companyField newCompanyField)
	 {
		 companyField oldCompanyField=getReferenceById(id);
		 
		 if(newCompanyField.getField()!=null)
		 {
			 oldCompanyField.setField(newCompanyField.getField());
		 }
		  companyFieldRepository.save(oldCompanyField); 
		  return "updated";
	 }
	 /**
	  * 
	  * @author BOBO 
	  * @function find company Fields With companyAdmin Id .
	  */
	 @Override
	 public List<companyField> findCompanyFieldsWithAdminId(Long Id)
	 {
		 return companyFieldRepository.findByCompanyAdministratorIdOrderByCreatedDateDesc(Id);
	 }
	@Override
	public companyField findByFieldIdAndCompanyId(Long fieldId, Long companyId) {
		return companyFieldRepository.findByFieldIdAndCompanyAdministratorId(fieldId,companyId);
	}

//	@Override
//	public Long findIdByFieldName(String fieldName) {
//		
//		return companyFieldRepository.findIdByFieldName(fieldName);
//	}
//
//	@Override
//	public companyField findByFieldName(String fieldName) {
//		return companyFieldRepository.findByFieldName(fieldName);
//	}

//	@Override
//	public companyField insertCompanyFieldAndSkillsAndQualifications(companyFieldDTO dto) {
//		 // Create companyAdmin 
//	    companyAdministrator companyAdmin = new companyAdministrator();
//	    companyAdmin.setId(dto.getCompanyAdministratorId());
//	    
//	    //companyField object
//	    companyField com = new companyField();
//	    com.setFieldName(dto.getFieldName());
//	    com.setCompanyAdministrator(companyAdmin);
////	    com.setRequiredQualifications(dto.getRequiredQualifications());
//	   if(findByFieldName(dto.getFieldName())==null)
//	   {
//		   companyField returnedcompanyField=companyFieldRepository.save(com); //insert the companyField containing fieldName and companyAdmin.   
//		// Batch insert new skills
//		    Set<String> newSkills = new HashSet<>(dto.getSkills());//Set of new skills that will be inserted 
//		    
//		    Map<String, Skill> existingSkills = new HashMap<>(); //Map that conatains existing skills the skill name and skill object
//
//		    if(!dto.getQualifications().isEmpty())
//		    {
//		    	Set<String> newQuals = new HashSet<>(dto.getQualifications());//Set of new skills that will be inserted 
//			    
//			    Map<String, Qualification> existingQuals = new HashMap<>(); //Map that conatains existing qual the Qual name and qual object
//			   
//			    List<Qualification> qualificationsToInsert = new ArrayList<>();
//			    
//			    for(String newQual:newQuals)
//			    {
//			    	Qualification qualiciationSearch=qualificationServiceI.findByName(newQual); //Search if the qualification exists
//			    	if(qualiciationSearch!=null) 
//			    	{
//			    		existingQuals.put(qualiciationSearch.getQualificationName(),qualiciationSearch);
//			    	}
//			    	
//			    	if (!existingQuals.containsKey(newQual)) {
//			        	Qualification newQuall = new Qualification();
//			        	newQuall.setQualificationName(newQual);
//			            qualificationsToInsert.add(newQuall);
//			        }
//			    }
//			    
//			    
////			    for (String qualName : newQuals) 
////			    {
////			        if (!existingQuals.containsKey(newQual)) {
//	//		        	qualification newQuall = new qualification();
//	//		        	newQuall.setQualificationName(newQual);
//	//		            qualificationsToInsert.add(newQuall);
////	        		}
////			    }
//			    
//			    qualificationServiceI.insertAll(qualificationsToInsert);
//			    
//			    // Retrieve inserted skills
//			    List<Qualification> allQualifications = new ArrayList<>(existingQuals.values());
//			    allQualifications.addAll(qualificationsToInsert);
//
//			    // Insert companyFieldSkills
//			    List<companyFieldQualification> comapanyFieldQuals = new ArrayList<companyFieldQualification>();
//			    
//			    for (Qualification qual : allQualifications) {
//			        companyFieldQualification companyFieldQual = new companyFieldQualification();
//			        companyFieldQual.setQualification(qual);
//			        companyFieldQual.setCompanyField(com);
//			        comapanyFieldQuals.add(companyFieldQual);
//			    }
//			    companyFieldQualificationI.insertAll(comapanyFieldQuals);
//
//		    } // The end of check if user insert qualification or not 
//		    List<Skill> skillsToInsert = new ArrayList<>();
//		    if(!dto.getSkills().isEmpty()) // check fro comming skills
//		    {
//		    	   
//			    for(String newSkill:newSkills) // Iterate over the newSkills.
//			    {
//			    	//find by skill name and the return value for the skillSearch
//			    	Skill skillSearch= skillServiceI.findByName(newSkill);
//			    	if(skillSearch!=null) //check if the skillSearch object !null
//			    	{
//			    		existingSkills.put(skillSearch.getSkillName(), skillSearch); //putting the skill name and skill object for the existing skills hashMap
//			    	}
//			    	
//			    	if (!existingSkills.containsKey(newSkill)) {
//			            Skill newSkillObj = new Skill();
//			            newSkillObj.setSkillName(newSkill);
//			            skillsToInsert.add(newSkillObj);
//			        }
//			    }
//			    
////			    for (String skillName : newSkills) {
////			        if (!existingSkills.containsKey(skillName)) {
////			            Skill newSkill = new Skill();
////			            newSkill.setSkillName(skillName);
////			            skillsToInsert.add(newSkill);
////			        }
////			    }
//			    
//			    
//			    skillServiceI.insertAll(skillsToInsert); // insert all new skills into the public skills table
//
//			    // Getting The inserted skills from the existingSkills Map<skillName,skillObject>
//			    List<Skill> allSkills = new ArrayList<>(existingSkills.values());
//			    allSkills.addAll(skillsToInsert);// adding the inserted skills and the existing skills into the same list
//
//			    // Insert companyFieldSkills from the allSkills
//			    List<companyFieldSkill> companyFieldSkills = new ArrayList<>();
//			    for (Skill skill : allSkills) {
//			        companyFieldSkill companyFieldSkill = new companyFieldSkill();
//			        companyFieldSkill.setCompanyFieldSkill(skill);
//			        companyFieldSkill.setCompanyField(com);
//			        companyFieldSkills.add(companyFieldSkill);
//			    }
//			    
//			    companyFieldSkillI.insertAll(companyFieldSkills);
//			    
//
//		    }
//		 		    // Return the inserted companyField object
//		    return returnedcompanyField;
//
//	   }else 
//	   {
//		   
//		    return null;
//	   }
//
//	}
}

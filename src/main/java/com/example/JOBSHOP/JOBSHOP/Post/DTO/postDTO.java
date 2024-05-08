package com.example.JOBSHOP.JOBSHOP.Post.DTO;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;
import com.example.JOBSHOP.JOBSHOP.Post.postField.DTO.postFieldDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class postDTO extends baseEntityDTO<Long>{

	private String Title;
	private List <String> remainedSkills=new ArrayList<String>();
	private List<String> remainedQualifications=new ArrayList<String>();
	private List <String> matchedQulifications=new ArrayList<String>();
	private List<String> matchedSkills=new ArrayList<String>();
	private int state;
	private String description;
	private String jobRequirments;
	private String location;
	private String employmentType;
	private String employerpicture;
	private String employerUserName;
	public String format;
//	private companyProfile companyProfile;
//	
	private String companyName;
	private Long profileId;
	private String adminUserName;
	
	private Long employerId;
	private String postImage;
	//	private Employer employer;
//	
	private postField postField;
	private String fieldName;
	private Long field;
	private List<String> skills;
	private List<String> qualifications;
	private String experience;
//	private List<postField> postFields=new ArrayList<postField>();
	
	
	

    public String getFieldName() {
		return fieldName;
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}


	public String getPostImage() {
		return postImage;
	}


	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	private Long fieldCount;
   
//	private List<String> additionalSkills;
	
	@JsonIgnore
	private List<Application> applications=new ArrayList<Application>();
	
	private Long applicationCount;
	
//	private List<postFieldDTO> postFieldsDto=new ArrayList<postFieldDTO>();
	
	
	
	public List<String> getSkills() {
		return skills;
	}
	
	
	public List<String> getMatchedQulifications() {
		return matchedQulifications;
	}


	public void setMatchedQulifications(List<String> matchedQulifications) {
		this.matchedQulifications = matchedQulifications;
	}


	public List<String> getMatchedSkills() {
		return matchedSkills;
	}


	public void setMatchedSkills(List<String> matchedSkills) {
		this.matchedSkills = matchedSkills;
	}


	public List<String> getRemainedSkills() {
		return remainedSkills;
	}


	public void setRemainedSkills(List<String> remainedSkills) {
		this.remainedSkills = remainedSkills;
	}


	public List<String> getRemainedQualifications() {
		return remainedQualifications;
	}


	public void setRemainedQualifications(List<String> remainedQualifications) {
		this.remainedQualifications = remainedQualifications;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public Long getField() {
		return field;
	}


	public void setField(Long field) {
		this.field = field;
	}

	
	public postField getPostField2()
	{
		postField postField=new postField();
		postField.setSkills(skills);
		postField.setQualifications(qualifications);
		employerField empF=new employerField();
		empF.setId(field);
		postField.setEmployerField(empF);
		return postField;
	}
	
	public postField getPostField()
	{
		return postField;
	}
	public void setPostField(postField postField) {
		this.postField = postField;
	}
	
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public List<String> getQualifications() {
		return qualifications;
	}
	public void setQualifications(List<String> qualifications) {
		this.qualifications = qualifications;
	}
	public Long getEmployerFieldId() {
		return field;
	}
	public void setEmployerFieldId(Long employerFieldId) {
		this.field = employerFieldId;
	}
//	public List<postField> getPostFields() {
//		return postFields;
//	}
//	public void setPostFields(List<postField> postFields) {
//		this.postFields = postFields;
//	}
//	public List<postFieldDTO> getPostFieldsDto() {
//		return postFieldsDto;
//	}
//	public void setPostFieldsDto(List<postFieldDTO> postFieldsDto) {
//		this.postFieldsDto = postFieldsDto;
//	}
//	
	public Long getEmployerId() {
		return employerId;
	}
	
	public void setEmployerId(Long employerId) {
		this.employerId = employerId;
	}
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
//	public String getEmployerpicture() {
//		String base64="";
//		if(employerpicture!=null)
//		{
//		 base64=Base64.getEncoder().encodeToString(employerpicture);
//			this.format=detectImageExtension(employerpicture);
//		}
//		return base64;
//	}
//	private String getImageFormat(byte[] imageData) throws IOException
//	{
//	ByteArrayInputStream input=new ByteArrayInputStream(imageData);
//		String format=detectImageExtension(imageData);/*ImageIO.getImageReadersBySuffix(null).next().getFormatName();*/
//	input.close();
//		return format;
//	}
//	 public static String detectImageExtension(byte[] imageData) {
//	        if (imageData == null || imageData.length < 4) {
//	            return null; // Insufficient data to determine the extension
//	        }
//
//	        // JPEG magic number: FF D8 FF
//	        if (imageData[0] == (byte) 0xFF && imageData[1] == (byte) 0xD8 && imageData[2] == (byte) 0xFF) {
//	            return "jpg";
//	        }
//
//	        // PNG magic number: 89 50 4E 47
//	        if (imageData[0] == (byte) 0x89 && imageData[1] == (byte) 0x50 && imageData[2] == (byte) 0x4E && imageData[3] == (byte) 0x47) {
//	            return "png";
//	        }
//
//	        // GIF magic number: 47 49 46 38
//	        if (imageData[0] == (byte) 0x47 && imageData[1] == (byte) 0x49 && imageData[2] == (byte) 0x46 && imageData[3] == (byte) 0x38) {
//	            return "gif";
//	        }
//
//	        // Add more checks for other image formats as needed...
//
//	        return null; // Unknown image format
//	    }


//	public void setEmployerpicture(byte[] employerpicture) {
//		this.employerpicture = employerpicture;
//	}
	
	
	public String getEmployerUserName() {
		return employerUserName;
	}
	public String getEmployerpicture() {
		return employerpicture;
	}


	public void setEmployerpicture(String employerpicture) {
		this.employerpicture = employerpicture;
	}


	public void setEmployerUserName(String employerUserName) {
		this.employerUserName = employerUserName;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJobRequirments() {
		return jobRequirments;
	}
	public void setJobRequirments(String jobRequirments) {
		this.jobRequirments = jobRequirments;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
//	public companyProfile getCompanyProfile() {
//		return companyProfile;
//	}
//	public void setCompanyProfile(companyProfile companyProfile) {
//		this.companyProfile = companyProfile;
//	}
//	public Employer getEmployer() {
//		return employer;
//	}
//	public void setEmployer(Employer employer) {
//		this.employer = employer;
//	}
//	public List<postField> getPostFields() {
//		return postFields;
//	}
//	public void setPostFields(List<postField> postFields) {
//		this.postFields = postFields;
//	}
	public Long getFieldCount() {
		return fieldCount;
	}
	public void setFieldCount(Long fieldCount) {
		this.fieldCount = fieldCount;
	}
//	public List<String> getAdditionalSkills() {
//		return additionalSkills;
//	}
//	public void setAdditionalSkills(List<String> additionalSkills) {
//		this.additionalSkills = additionalSkills;
//	}
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	public Long getApplicationCount() {
		return applicationCount;
	}
	public void setApplicationCount(Long applicationCount) {
		this.applicationCount = applicationCount;
	}
	
	public void insertIntoDataSet()
	{
		
	}
}

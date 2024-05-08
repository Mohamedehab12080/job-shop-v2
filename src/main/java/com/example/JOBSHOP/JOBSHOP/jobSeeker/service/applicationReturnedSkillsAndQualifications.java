package com.example.JOBSHOP.JOBSHOP.jobSeeker.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class applicationReturnedSkillsAndQualifications {

	private List<String> remainedSkills=new ArrayList<String>();
	private List<String> remainedQualifications=new ArrayList<String>();
	private Long postId;
	private boolean isMatched;
	
	
	public boolean isMatched() {
		return isMatched;
	}
	public void setMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public List<String> getRemainedSkills() {
		// Create a HashSet to store unique skills
        Set<String> uniqueSkills = new HashSet<>(remainedSkills);

        // Create a new ArrayList to store unique skills in the original order
        List<String> uniqueSkillsList = new ArrayList<>(uniqueSkills);

        // Return the list of unique skills
        return uniqueSkillsList;
	}
	public void setRemainedSkills(List<String> remainedSkills) {
		this.remainedSkills = remainedSkills;
	}
	public List<String> getRemainedQualifications() {
		Set<String> uniqueQualifications=new HashSet<String>(remainedQualifications);
		List<String> uniqueList=new ArrayList<String>(uniqueQualifications);
		return uniqueList;
	}
	public void setRemainedQualifications(List<String> remainedQualifications) {
		this.remainedQualifications = remainedQualifications;
	}
	
}

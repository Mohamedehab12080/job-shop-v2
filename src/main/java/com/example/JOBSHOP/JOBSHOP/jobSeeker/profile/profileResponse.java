package com.example.JOBSHOP.JOBSHOP.jobSeeker.profile;

public class profileResponse {

	private jobSeekerProfile profile;
	
	private boolean isRequestUser;

	public profileResponse() {}
	public profileResponse(jobSeekerProfile profile, boolean isRequestUser) {
		super();
		this.profile = profile;
		this.isRequestUser = isRequestUser;
	}

	public jobSeekerProfile getProfile() {
		return profile;
	}

	public void setProfile(jobSeekerProfile profile) {
		this.profile = profile;
	}

	public boolean isRequestUser() {
		return isRequestUser;
	}

	public void setRequestUser(boolean isRequestUser) {
		this.isRequestUser = isRequestUser;
	}
	
	
}

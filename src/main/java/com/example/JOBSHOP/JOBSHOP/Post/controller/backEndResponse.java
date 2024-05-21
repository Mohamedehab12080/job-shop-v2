package com.example.JOBSHOP.JOBSHOP.Post.controller;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;

public class backEndResponse {

	private List<pythonApiResponse> pythonResponses;
	private List<postDTO> postDtosResponse;
	
	public List<pythonApiResponse> getPythonResponses() {
		return pythonResponses;
	}
	public void setPythonResponses(List<pythonApiResponse> pythonResponses) {
		this.pythonResponses = pythonResponses;
	}
	public List<postDTO> getPostDtosResponse() {
		return postDtosResponse;
	}
	public void setPostDtosResponse(List<postDTO> postDtosResponse) {
		this.postDtosResponse = postDtosResponse;
	}
	
	
}

package com.example.JOBSHOP.JOBSHOP.Post.postField.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;

public interface postFieldServiceInterface {

	postField findByEmployerField(Long id);

	postField getReferenceById(Long id);

	List<postField> findAll();

	postField insert(postField t);

	postField findById(Long id);

	postField update(postField t);

	List<postField> insertAll(List<postField> entity);

	void deleteById(Long id);

}

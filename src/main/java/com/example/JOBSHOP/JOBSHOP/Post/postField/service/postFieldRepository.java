package com.example.JOBSHOP.JOBSHOP.Post.postField.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;

@Repository
public interface postFieldRepository extends JpaRepository<postField,Long>{

	postField findByEmployerFieldId(Long id);
}

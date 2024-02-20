package com.example.JOBSHOP.JOBSHOP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.models.postField;

@Repository
public interface postFieldRepository extends JpaRepository<postField,Long>{

}

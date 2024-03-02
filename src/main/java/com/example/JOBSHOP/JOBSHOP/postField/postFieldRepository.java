package com.example.JOBSHOP.JOBSHOP.postField;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface postFieldRepository extends JpaRepository<postField,Long>{

	postField findByEmployerFieldId(Long id);
}

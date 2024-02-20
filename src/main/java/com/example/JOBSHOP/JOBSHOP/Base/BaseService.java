package com.example.JOBSHOP.JOBSHOP.Base;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseService <T extends baseEntity<ID>,ID extends Number>{
 
//
//    @Autowired 
//    private baseRepo<T, ID> baseRepos;
//    
//    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    public T getReferenceById(ID id)
//	{
//		return baseRepos.getReferenceById(id);
//	}
//   
//	public List<T> findAll()
//	{
//		return baseRepos.findAll();
//	}
//	
//	public T insert(T t)
//	{
//		return baseRepos.save(t);
//	}
//	
//	public T findById(ID id)
//	{
//		Optional<T> finded=baseRepos.findById(id);
//		if(finded.isPresent())
//		{
//			return finded.get();
//		}else 
//		{
//			return null;
//		}
//		
//	}
//	
//	public void updateEntityStatus(T t)
//	{
//		baseRepos.updateEntity(t.getId(),t.getStatusCode()); 
//	}
//	
//	public T update(T t)
//	{
//		if(getReferenceById(t.getId())!=null)
//		{
//			logInfo("Employer Updated Successfully");
//			return baseRepos.save(t);
//		}else 
//		{
//			logError("EmployerNotFound");
//			return null;
//			
//		}
//	}
//	public List<T> insertAll(List<T> entity)
//	{
//		return baseRepos.saveAll(entity);
//	}
//	
//	public void deleteById(ID id)
//	{
//		T t=getReferenceById(id);
//		if(t!=null)
//		{
//			baseRepos.deleteById(id);
//		}else 
//		{
//			logError("Entity Not found !!");
//		}
//	}
//    protected void handleException(Exception e) {
//        logger.error("An error occurred: {}", e.getMessage(), e);
//        // You can choose to throw a custom exception, log the error, etc.
//    }
//    
//    protected void logInfo(String message) {
//        logger.info(message);
//    }
//    
//    protected void logDebug(String message) {
//        logger.debug(message);
//    }
//    
//    protected void logError(String message) {
//        logger.error(message);
//    }
}


package com.example.JOBSHOP.JOBSHOP.services;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import com.example.JOBSHOP.JOBSHOP.models.User;


public abstract class BaseService{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Add common methods here
    // For example, error handling, logging, etc.
    
    protected void handleException(Exception e) {
        logger.error("An error occurred: {}", e.getMessage(), e);
        // You can choose to throw a custom exception, log the error, etc.
    }
    
    protected void logInfo(String message) {
        logger.info(message);
    }
    
    protected void logDebug(String message) {
        logger.debug(message);
    }
    
    protected void logError(String message) {
        logger.error(message);
    }
}


package com.example.JOBSHOP.JOBSHOP.courses;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

public class courses {

	 public static void scrapeCourses() {
	        try {
	            // Connect to the Coursera website and fetch HTML content
	            Document doc = Jsoup.connect("https://www.coursera.org/courses").get();
	            
	            // Extract course information from HTML elements
	            Elements courseElements = doc.select(".ais-InfiniteHits-item");
	            for (Element element : courseElements) {
	                String name = element.select(".color-primary-text.card-title").text();
	                String description = element.select(".partner-name").text() + ": " +
	                                     element.select(".partner-name").next().text();
	                
	                
	                System.out.println("Course name : "+name+" :Course Description: "+description);
//	                // Create Course object and save to database
//	                Course course = new Course();
//	                course.setName(name);
//	                course.setDescription(description);
//	                courseRepository.save(course);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle error (e.g., log error, notify admin)
	        }
	    }
	 
	 public static void main(String []args)
	 {
		 scrapeCourses();
	 }
	
}

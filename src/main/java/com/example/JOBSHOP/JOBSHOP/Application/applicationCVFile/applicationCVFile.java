	package com.example.JOBSHOP.JOBSHOP.Application.applicationCVFile;
	
	import com.example.JOBSHOP.JOBSHOP.Application.Application;
	import com.example.JOBSHOP.JOBSHOP.jobSeeker.CV.CVFile;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.OneToOne;
	
	@Entity
	public class applicationCVFile {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@OneToOne
		@JoinColumn(name="Application_id")
		private Application application;
		
		@OneToOne
		@JoinColumn(name="CVFile_id")
		private CVFile cvFile;
	
		public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public Application getApplication() {
			return application;
		}
	
		public void setApplication(Application application) {
			this.application = application;
		}
	
		public CVFile getCvFile() {
			return cvFile;
		}
	
		public void setCvFile(CVFile cvFile) {
			this.cvFile = cvFile;
		}
		
		
	}

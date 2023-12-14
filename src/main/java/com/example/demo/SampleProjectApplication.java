package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@ComponentScan("com.example.demo")
@EnableJpaRepositories(basePackages = "com.example.demo.repo")
//@EnableWebMvc
public class SampleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleProjectApplication.class, args);
		System.out.println("Application started");
	}
	

//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
//	    MultipartConfigFactory factory = new MultipartConfigFactory();
//	    
//	    // Set maximum file size
//	    factory.setMaxFileSize(DataSize.ofMegabytes(5));
//	    
//	    // Set maximum request size
//	    factory.setMaxRequestSize(DataSize.ofMegabytes(5));
//	    
//	    return factory.createMultipartConfig();
//	}
}

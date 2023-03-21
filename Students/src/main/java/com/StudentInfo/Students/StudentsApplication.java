package com.StudentInfo.Students;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
	}
//	MODEL mapper class 
//	@Bean for Configurating and springcontainer will create object of it
	
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();//to start mapping engine
	}
}

package com.StudentInfo.Students.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "students")
public class Students {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "abc")
	@GenericGenerator(name = "abc", strategy = "uuid2")
	private String id;
	private String Name;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate DOB;
	private  Boolean pass;
	private int Marks;
	private String Mbnumber;
	private String password;

//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")


}

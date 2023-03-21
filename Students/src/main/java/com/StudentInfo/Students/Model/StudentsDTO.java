package com.StudentInfo.Students.Model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentsDTO {

	private String id;

	private String Name;

	private LocalDate DOB;

	private String Year;

	private Double Marks;

//	private Long Mbnumber;

}

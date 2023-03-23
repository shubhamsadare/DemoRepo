package com.StudentInfo.Students.Model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

	@NotNull
	@Size(min=4,message = "name should more than size 4")
	private String Name;
	private LocalDate DOB;
	@NotNull
	private String Year;
//	@Size(max = 3,message = " Marks range less than 100")
	private int Marks;
	@Size(min = 8,max=10,message = "Number not be less than 8")
	private String Mbnumber;
	@Pattern(regexp = "")
	private String password;

}

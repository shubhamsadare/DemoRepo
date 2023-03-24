package com.StudentInfo.Students.Model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentsDTO {

	private String id;

	@NotNull
//	@Size(min=4,message = "name should more than size 4")
	private String Name;
	@Email(message = "Format should be in email")
	private String email;
	private LocalDate DOB;
	@NotNull
	private Boolean pass;
	@Min(60)
	@Max(100)
	private int Marks;
//	@Size(min = 8,message = "Number not be less than 8")
	private String Mbnumber;
//	@Pattern(regexp = "^[a-zA-Z0-9]{5,}$",message="Invalid password pattern")
	private String password;

}

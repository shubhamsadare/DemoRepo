package com.StudentInfo.Students.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentInfo.Students.Model.Students;
import com.StudentInfo.Students.Model.StudentsDTO;
import com.StudentInfo.Students.Repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;

	@Autowired
	private ModelMapper modelmapper;

	public List<Students> Getall() {
		return repo.findAll();
	}

	public Optional<Students> AddStudents(Students s) {
		
		
		return repo.findById(s.getId());

	}

	public StudentsDTO studentbyid(String id) {
		
		Optional<Students> findById = repo.findById(id);
		StudentsDTO entitytoDto = entitytoDto(findById.get());
		return entitytoDto;
	}

//  update marks,year,dob,name
	public Students UpdateStudents(StudentsDTO sdto, String id) {
		
		Students students = repo.findById(id).get();
		// update by using setter
//		 	students.setMbnumber(sdto.getMbnumber());
//		 	students.setMarks(sdto.getMarks());

		// update by using builder
		Students build = students.toBuilder().Year(sdto.getYear()).DOB(sdto.getDOB()).Name(sdto.getName())
				.Marks(sdto.getMarks()).build();

		return repo.save(build);

	}

//update marks and year
	public StudentsDTO SpecificUpdate(StudentsDTO ss, String id) {
		
		Students students = repo.findById(id).get();
		Students build = students.toBuilder().Marks(ss.getMarks()).Year(ss.getYear()).build();
		repo.save(build);
		return entitytoDto(build);

	}

	public Optional<Students> DeleteStudent(String id) {
		
		return repo.findById(id);
	}

	// ==============
	public List<String> GetName() {
		return repo.findAll().stream().map(i -> i.getName()).collect(Collectors.toList());

	}

	// modelmapper
	public StudentsDTO entitytoDto(Students students) {
		return modelmapper.map(students, StudentsDTO.class);
	}

	public Students Dtotoentity(StudentsDTO studentsdto) {
		return modelmapper.map(studentsdto, Students.class);
	}

}

package com.StudentInfo.Students.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentInfo.Students.Model.Students;
import com.StudentInfo.Students.Model.StudentsDTO;
import com.StudentInfo.Students.Repository.StudentRepository;

import Com.StudentInfo.Students.ExceptionHandler.ResourceAlreadyPresent;
import Com.StudentInfo.Students.ExceptionHandler.StudentNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;

	@Autowired
	private ModelMapper modelmapper;

	public List<StudentsDTO> Getall() {
		List<Students> li = repo.findAll();
		return li.stream().map(this::entitytoDto).collect(Collectors.toList());

	}

	public StudentsDTO AddStudents(StudentsDTO s) {

		Students entity = Dtotoentity(s);
		List<String> collect = repo.findAll().stream().map(ex -> ex.getId()).collect(Collectors.toList());
		if (collect.contains(entity.getId())) {
			throw new ResourceAlreadyPresent("record alredy present!!!!!!");
		} else
			repo.save(entity);

		return entitytoDto(entity);
	}

//============
	public Students studentbyid(String id)throws StudentNotFoundException {

		
		Students findById = repo.findByid(id);

		if (findById != null) {
			return findById;
		} else {
			System.out.println("=========================exc");
			throw new StudentNotFoundException("not found to Exception class");
		}
	}

//  update marks,year,dob,name
	public Students UpdateStudents(StudentsDTO sdto, String id) {

		Students students = repo.findById(id).get();

		Students build = students.toBuilder().DOB(sdto.getDOB()).Name(sdto.getName()).pass(sdto.getPass())
				.Marks(sdto.getMarks()).build();

		return repo.save(build);

	}

//update marks and year
	public StudentsDTO SpecificUpdate(StudentsDTO ss, String id) {

		Students students = repo.findByid(id);
		Students build = students.toBuilder().Marks(ss.getMarks()).pass(ss.getPass()).build();
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

	public List<StudentsDTO> Listofentity(List<Students> students) {
		return Arrays.asList(modelmapper.map(students, StudentsDTO.class));
	}

}

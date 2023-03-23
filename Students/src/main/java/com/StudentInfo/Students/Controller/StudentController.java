package com.StudentInfo.Students.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.StudentInfo.Students.Exception.ResourceAlreadyPresent;
import com.StudentInfo.Students.Exception.StudentNotFoundException;
import com.StudentInfo.Students.Model.Students;
import com.StudentInfo.Students.Model.StudentsDTO;
import com.StudentInfo.Students.Repository.StudentRepository;
import com.StudentInfo.Students.Service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;

	@Autowired
	private StudentRepository repo;

	@PostMapping("/students")
	public  ResponseEntity<StudentsDTO> AddStudents(@Valid @RequestBody StudentsDTO s)throws ResourceAlreadyPresent {
		
		StudentsDTO dto =service.AddStudents(s);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}

	@GetMapping("/students")
	public ResponseEntity<Object> Getall() {

		List<Students> li = service.Getall();
		// entity list to dto list
		List<StudentsDTO> lidto = li.stream().map(service::entitytoDto).collect(Collectors.toList());
		if (lidto.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(lidto));// if values present in dto
	}
//================correct
	@GetMapping("/students/{id}")
	public ResponseEntity<StudentsDTO> studentbyid(@Valid @PathVariable String id) throws StudentNotFoundException {
		return ResponseEntity.ok(service.studentbyid(id)); 
	}

	@PutMapping("/students/{id}")
	public StudentsDTO UpdateStudents(@Valid @RequestBody StudentsDTO s, @PathVariable("id") String id) {
		Students entity = service.UpdateStudents(s, id);
		return service.entitytoDto(entity);
	}

	@PatchMapping("/students/{id}")
	public ResponseEntity<Optional<Students>> SpecificUpdate(@Valid @RequestBody StudentsDTO ss,
			@PathVariable("id") String id) throws StudentNotFoundException {
		try {

			StudentsDTO stud = service.SpecificUpdate(ss, id);
			Optional<StudentsDTO> ofNullable = Optional.ofNullable(stud);
			

			if (ofNullable.isPresent()) {
				return ResponseEntity.accepted().build();

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@DeleteMapping("/students")
	public ResponseEntity<Object> DeleteStudent(@RequestParam("id") String id) throws StudentNotFoundException {
		Optional<Students> deleteStudent = service.DeleteStudent(id);

		if (deleteStudent.isPresent()) {
			try {
				
				repo.deleteById(id);
				return ResponseEntity.status(HttpStatus.PROCESSING).build();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
			}
		} else {
			throw new StudentNotFoundException("Cannot Delete because Id doesn't exists " + id);
		}

	}

//	RequestMapping
	@RequestMapping(value = "/students/allname", method = RequestMethod.GET)
	public List<String> GetName() {
		return service.GetName();
	}

//	RequestHeader
	@GetMapping("/students/gretting")
	public ResponseEntity<String> HttpGretting(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lang) {

		return new ResponseEntity<String>(lang, HttpStatus.OK);

	}

}

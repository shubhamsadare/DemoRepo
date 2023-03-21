package com.StudentInfo.Students.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.StudentInfo.Students.Model.Students;
import com.StudentInfo.Students.Model.StudentsDTO;
import com.StudentInfo.Students.Repository.StudentRepository;
import com.StudentInfo.Students.Service.StudentService;

import Com.StudentInfo.Students.Exception.ResourceAlreadyPresent;
import Com.StudentInfo.Students.Exception.StudentNotFoundException;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;

	@Autowired
	private StudentRepository repo;

	@PostMapping("/students")
	public StudentsDTO AddStudents(@RequestBody Students s) {
		Optional<Students> add = service.AddStudents(s);

		if (add.isPresent()) {
			throw new ResourceAlreadyPresent("Record Already Present with Same id " + s.getId());
		} else {

			repo.save(s);// save
			StudentsDTO entitytoDto = service.entitytoDto(s);// entity to dto
			return entitytoDto;
		}
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

	@GetMapping("/students/{id}")
	public ResponseEntity<Optional<StudentsDTO>> studentbyid(@PathVariable String id) throws StudentNotFoundException {
		StudentsDTO studentbyid = service.studentbyid(id);
		Optional<StudentsDTO> sid = Optional.ofNullable(studentbyid);

		if (sid.isPresent()) {
			return ResponseEntity.ok().body(sid);// return object +status

		} else {
			throw new StudentNotFoundException("Student not found with id " + id);
		}
	}

	@PutMapping("/students/update/{id}")
	public StudentsDTO UpdateStudents(@RequestBody StudentsDTO s, @PathVariable("id") String id) {
		Students entity = service.UpdateStudents(s, id);
		return service.entitytoDto(entity);
	}

	@PatchMapping("/students/patch/{id}")
	public ResponseEntity<Optional<Students>> SpecificUpdate(@RequestBody StudentsDTO ss,
			@PathVariable("id") String id) throws StudentNotFoundException {
		try {

			StudentsDTO stud = service.SpecificUpdate(ss, id);
			Optional<StudentsDTO> ofNullable = Optional.ofNullable(stud);
			ofNullable.orElseThrow(StudentNotFoundException::new);

			if (ofNullable.isPresent()) {
				return ResponseEntity.accepted().build();

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (StudentNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@DeleteMapping("/students/delete")
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

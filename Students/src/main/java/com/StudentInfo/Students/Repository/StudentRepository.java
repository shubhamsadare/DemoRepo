package com.StudentInfo.Students.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentInfo.Students.Model.Students;
//DAO layer
@Repository
public interface StudentRepository extends JpaRepository<Students, String> {

}

package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
//    Student findByNombresAndApellidos(String nombres, String apellidos);
}

package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Student;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.StudentDTO;

import java.util.List;

public interface StudentServiceInterface {

    public List<StudentDTO> listStudents();
    public StudentDTO createStudent(StudentDTO studentDTO) throws ItemAlreadyInUseException;
    public StudentDTO editStudent(Integer studentId, StudentDTO studentDTO) throws ItemNotFoundException;
    public void deleteStudent(int id) throws ItemNotFoundException;
    public Student findByIdStudent(int id) throws ItemNotFoundException;
    public StudentDTO findByName(String fName, String lName) throws ItemNotFoundException;
}

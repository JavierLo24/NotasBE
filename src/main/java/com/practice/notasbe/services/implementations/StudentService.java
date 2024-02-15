package com.practice.notasbe.services.implementations;


import com.practice.notasbe.entities.Student;
import com.practice.notasbe.entities.UserE;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.repositories.StudentRepository;
import com.practice.notasbe.repositories.UserRepository;
import com.practice.notasbe.services.interfaces.StudentServiceInterface;
import com.practice.notasbe.shared.dto.StudentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("studentService")
public class StudentService implements StudentServiceInterface {

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    private StudentDTO convertToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(student, studentDTO);
        return studentDTO;
    }

    @Override
    public List<StudentDTO> listStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO){
        Student student1 = new Student();
        BeanUtils.copyProperties(studentDTO, student1);
        Student nuevoStudent = studentRepository.save(student1);
        return convertToStudentDTO(nuevoStudent);
    }

    @Override
    public StudentDTO editStudent(Integer alumnoID, StudentDTO studentDTO) throws ItemNotFoundException{
        Student student = studentRepository.findById(alumnoID).orElse(null);
        if (student == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "STUDENT").toUpperCase());
        BeanUtils.copyProperties(studentDTO, student);
        Student updatedStudent = studentRepository.save(student);
        return convertToStudentDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(int id) throws ItemNotFoundException {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "STUDENT").toUpperCase());
        studentRepository.deleteById(id);
    }

    @Override
    public Student findByIdStudent(int id) throws ItemNotFoundException {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "STUDENT").toUpperCase());
        return student;
    }

    @Override
    public StudentDTO findByName(String fName, String lName) throws ItemNotFoundException{
        UserE userE = userRepository.findByFirstNameAndLastName(fName, lName);
        if (userE == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "USER").toUpperCase());
        if (userE.getRolId().getDescription() == "STUDENT") throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "STUDENT").toUpperCase());
        Student student = studentRepository.findByUserId(userE);
        return convertToStudentDTO(student);
    }

}

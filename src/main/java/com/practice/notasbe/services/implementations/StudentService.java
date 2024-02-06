package com.practice.notasbe.services.implementations;


import com.practice.notasbe.entities.Student;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.repositories.StudentRepository;
import com.practice.notasbe.services.interfaces.StudentServiceInterface;
import com.practice.notasbe.shared.dto.StudentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("alumnoService")
public class StudentService implements StudentServiceInterface {

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

    @Autowired
    StudentRepository studentRepository;

    private StudentDTO convertToAlumnoDTO(Student student) {
        StudentDTO alumnoDTO = new StudentDTO();
        BeanUtils.copyProperties(student, alumnoDTO);
        return alumnoDTO;
    }

    @Override
    public List<StudentDTO> listadoDeAlumnos() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToAlumnoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO crearAlumno (StudentDTO alumnodto){
        Student student1 = new Student();
        BeanUtils.copyProperties(alumnodto, student1);
        Student nuevoStudent = studentRepository.save(student1);
        return convertToAlumnoDTO(nuevoStudent);
    }

    @Override
    public StudentDTO editAlumno(Integer alumnoID, StudentDTO alumnoDto) throws ItemNotFoundException{
        Student student = studentRepository.findById(alumnoID).orElse(null);
        if (student == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
        BeanUtils.copyProperties(alumnoDto, student);
        Student updatedStudent = studentRepository.save(student);
        return convertToAlumnoDTO(updatedStudent);
    }

    @Override
    public void eliminarAlumno(int id) throws ItemNotFoundException {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
        studentRepository.deleteById(id);
    }

    @Override
    public Student buscarAlumnoID(int id) throws ItemNotFoundException {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
        return student;
    }

//    @Override
//    public Student buscarAlumnoName(String nombre, String apellidos) throws ItemNotFoundException{
//        Student student = studentRepository.findByNombresAndApellidos(nombre, apellidos);
//        if (student == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
//        return studentRepository.findByNombresAndApellidos(nombre, apellidos);
//    }

}

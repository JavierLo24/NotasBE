package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Student;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.StudentDTO;

import java.util.List;

public interface StudentServiceInterface {

    public List<StudentDTO> listadoDeAlumnos();
    public StudentDTO crearAlumno(StudentDTO alumnodto) throws ItemAlreadyInUseException;
    public StudentDTO editAlumno(Integer alumnoID, StudentDTO alumnoDto) throws ItemNotFoundException;
    public void eliminarAlumno(int id) throws ItemNotFoundException;
    public Student buscarAlumnoID(int id) throws ItemNotFoundException;
//    public Student buscarAlumnoName(String nombres, String apellidos) throws ItemNotFoundException;
}

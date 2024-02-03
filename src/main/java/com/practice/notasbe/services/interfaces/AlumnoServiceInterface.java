package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.AlumnoDTO;

import java.util.List;
import java.util.Optional;

public interface AlumnoServiceInterface {

    public List<AlumnoDTO> listadoDeAlumnos();
    public AlumnoDTO crearAlumno(AlumnoDTO alumnodto) throws ItemAlreadyInUseException;
    public AlumnoDTO editAlumno(Integer alumnoID, AlumnoDTO alumnoDto) throws ItemNotFoundException;
    public void eliminarAlumno(int id) throws ItemNotFoundException;
    public Alumno buscarAlumnoID(int id) throws ItemNotFoundException;
    public Alumno buscarAlumnoName(String nombres, String apellidos) throws ItemNotFoundException;
}

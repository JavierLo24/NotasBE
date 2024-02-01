package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.shared.dto.AlumnoDTO;

import java.util.List;
import java.util.Optional;

public interface AlumnoServiceInterface {

    public List<AlumnoDTO>listadoDeAlumnos();
    public AlumnoDTO crearAlumno(AlumnoDTO alumnodto);
    public AlumnoDTO editAlumno(Integer alumnoID, AlumnoDTO alumnoDto);
    public void eliminarAlumno(int id);
    public Optional<Alumno> buscarAlumnoID(int id);
}

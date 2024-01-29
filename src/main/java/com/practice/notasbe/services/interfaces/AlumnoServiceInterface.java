package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.shared.dto.AlumnoDTO;

import java.util.List;
import java.util.Optional;

public interface AlumnoServiceInterface {

    public List<Alumno>listadoDeAlumnos();
    public AlumnoDTO crearAlumno(AlumnoDTO alumno);
    public AlumnoDTO editAlumno(AlumnoDTO alumno, Integer alumnoID);
    public void eliminarAlumno(int id);
    public Optional<Alumno> buscarAlumnoID(int id);
}

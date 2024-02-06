package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Course;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.CourseDTO;

import java.util.List;

public interface CourseServiceInterface {

    public List<CourseDTO> listadoDeCursos();
    public CourseDTO crearCurso(CourseDTO curso) throws ItemAlreadyInUseException;
    public CourseDTO editCurso(Integer cursoID, CourseDTO curso) throws ItemNotFoundException;
    public void eliminarCurso(int id)  throws ItemNotFoundException;
    public Course buscarCursoID(int id)  throws ItemNotFoundException;

}

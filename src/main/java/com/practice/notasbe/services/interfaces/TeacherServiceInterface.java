package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Teacher;
import com.practice.notasbe.shared.dto.TeacherDTO;

import java.util.List;
import java.util.Optional;

public interface TeacherServiceInterface {

    public List<TeacherDTO> listadoDeProfes();
    public TeacherDTO crearProfesor(TeacherDTO profesor);
    public TeacherDTO editProfesor(Integer profeID, TeacherDTO profesor);
    public void eliminarProfe(int id);
    public Optional<Teacher> buscarProfeID(int id);
}

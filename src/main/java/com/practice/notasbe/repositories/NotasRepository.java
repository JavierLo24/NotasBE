package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.entities.Curso;
import com.practice.notasbe.entities.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotasRepository  extends JpaRepository<Notas, Integer>{
    Notas findByIdAlumno (Alumno IdAlumno);
    List<Notas> findByIdCurso (Curso curso);
}

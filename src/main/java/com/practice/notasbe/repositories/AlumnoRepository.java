package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
    Alumno findByNombresAndApellidos(String nombres, String apellidos);
}

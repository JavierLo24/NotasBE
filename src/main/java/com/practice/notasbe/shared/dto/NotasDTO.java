package com.practice.notasbe.shared.dto;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.entities.Curso;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class NotasDTO {
    private Alumno idAlumno;
    private Curso idCurso;
    private double nota;
    private String descNota;
    private double promedio;
}

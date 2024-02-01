package com.practice.notasbe.shared.dto;


import com.practice.notasbe.entities.Profesor;
import lombok.Data;

@Data
public class CursoDTO {
    private String nombre;
    private Profesor idProfesor;
}

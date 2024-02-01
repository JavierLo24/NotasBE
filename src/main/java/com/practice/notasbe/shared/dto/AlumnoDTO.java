package com.practice.notasbe.shared.dto;


import lombok.Data;

import java.util.Date;

@Data
public class AlumnoDTO {
    private String nombres;
    private String apellidos;
    private Date fechaNac;
    private String grado;
    private String seccion;
    private String direccion;
    private String telefono;
    private String email;
    private String password;
}

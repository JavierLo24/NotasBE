package com.practice.notasbe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notas")
public class Notas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = true)
    private Alumno idAlumno;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = true)
    private Curso idCurso;

    private double nota;

    @Column(name = "desc_nota")
    private String descNota;

    private double promedio;

}

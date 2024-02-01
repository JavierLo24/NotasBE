package com.practice.notasbe.controller;


import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.entities.Notas;
import com.practice.notasbe.services.implementations.NotasService;
import com.practice.notasbe.shared.dto.NotasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
public class NotasController {

    @Autowired
    NotasService notasService;

//    @GetMapping
//    public ResponseEntity<List<CursoDTO>> listarCursos(){
//        return new ResponseEntity<>(cursoService.listadoDeCursos(), HttpStatus.OK);
//    }

    @GetMapping("/{alumnoName}")
    public ResponseEntity<NotasDTO> buscarPorId(@PathVariable String alumnoName){
        NotasDTO notas = notasService.notasPorAlumno(alumnoName);
//      Curso curso = cursoPodId.orElseThrow(() -> new NoSuchElementException("Curso no encontrado"));
        return new ResponseEntity<>(notas,  HttpStatus.OK);
    }

    @GetMapping("/curso/{cursoName}")
    public ResponseEntity<List<NotasDTO>> buscarPorCurso(@PathVariable String cursoName){
        List<NotasDTO> notas = notasService.notasPorCurso(cursoName);
//      Curso curso = cursoPodId.orElseThrow(() -> new NoSuchElementException("Curso no encontrado"));
        return new ResponseEntity<>(notas,  HttpStatus.OK);
    }

}

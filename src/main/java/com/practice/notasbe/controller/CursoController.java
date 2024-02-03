package com.practice.notasbe.controller;

import com.practice.notasbe.entities.Curso;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.services.implementations.CursoService;
import com.practice.notasbe.shared.dto.CursoDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos(){
        return new ResponseEntity<>(cursoService.listadoDeCursos(), HttpStatus.OK);
    }

    @GetMapping("/{cursoId}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Integer cursoId) throws ItemNotFoundException{
        Curso curso = cursoService.buscarCursoID(cursoId);
        return new ResponseEntity<>(curso,  HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createAlumno(@RequestBody CursoDTO cursoDTO) throws ItemAlreadyInUseException {
        cursoService.crearCurso(cursoDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Curso created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{cursoId}")
    public ResponseEntity<HttpResponse> updateAlumno(@PathVariable Integer cursoId, @RequestBody CursoDTO cursoDTO) throws ItemNotFoundException {
        cursoService.editCurso(cursoId, cursoDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Curso updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{cursoId}")
    public ResponseEntity<HttpResponse> deleteClient(@PathVariable Integer cursoId) throws ItemNotFoundException{
        cursoService.eliminarCurso(cursoId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Curso deleted successfully"),
                HttpStatus.OK
        );
    }

}

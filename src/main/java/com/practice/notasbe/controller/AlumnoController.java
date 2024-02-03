package com.practice.notasbe.controller;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.services.implementations.AlumnoService;
import com.practice.notasbe.shared.dto.AlumnoDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> listarAlumnos(){
        return new ResponseEntity<>(alumnoService.listadoDeAlumnos(), HttpStatus.OK);
    }

    @GetMapping("/nombre={name}-apellido={app}")
    public ResponseEntity<Alumno> buscarname (@PathVariable String name, @PathVariable String app) throws ItemNotFoundException {
        Alumno alumno = alumnoService.buscarAlumnoName(name, app);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

    @GetMapping("/id={alumnoId}")
    public ResponseEntity<Alumno> buscarPorId(@PathVariable Integer alumnoId) throws ItemNotFoundException {
        Alumno alumno = alumnoService.buscarAlumnoID(alumnoId);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createAlumno(@RequestBody AlumnoDTO alumnoDTO) throws ItemAlreadyInUseException {
        alumnoService.crearAlumno(alumnoDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Alumno created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{alumnoId}")
    public ResponseEntity<HttpResponse> updateAlumno(@PathVariable Integer alumnoId, @RequestBody AlumnoDTO alumnoDTO) throws ItemNotFoundException{
        alumnoService.editAlumno(alumnoId, alumnoDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Alumno updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{alumnoId}")
    public ResponseEntity<HttpResponse> deleteClient(@PathVariable Integer alumnoId) throws ItemNotFoundException {
        alumnoService.eliminarAlumno(alumnoId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Alumno deleted successfully"),
                HttpStatus.OK
        );
    }
}

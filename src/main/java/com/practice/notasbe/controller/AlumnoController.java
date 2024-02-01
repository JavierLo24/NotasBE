package com.practice.notasbe.controller;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.services.implementations.AlumnoService;
import com.practice.notasbe.shared.dto.AlumnoDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{name}")
    public ResponseEntity<Alumno> buscarname (@PathVariable String name){
        Alumno alumno = alumnoService.buscarALumno(name);
        return new ResponseEntity<>(alumno,  HttpStatus.OK);
    }


//    @GetMapping("/{alumnoId}")
//    public ResponseEntity<Alumno> buscarPorId(@PathVariable Integer alumnoId){
//        Optional<Alumno> alumnoPodId = alumnoService.buscarAlumnoID(alumnoId);
//        Alumno alumno = alumnoPodId.orElseThrow(() -> new NoSuchElementException("Alumno no encontrado"));
//        return new ResponseEntity<>(alumno,  HttpStatus.OK);
//    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        alumnoService.crearAlumno(alumnoDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Alumno created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{alumnoId}")
    public ResponseEntity<HttpResponse> updateAlumno(@PathVariable Integer alumnoId, @RequestBody AlumnoDTO alumnoDTO) {
        alumnoService.editAlumno(alumnoId, alumnoDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Alumno updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{alumnoId}")
    public ResponseEntity<HttpResponse> deleteClient(@PathVariable Integer alumnoId) {
        alumnoService.eliminarAlumno(alumnoId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Alumno deleted successfully"),
                HttpStatus.OK
        );
    }
}

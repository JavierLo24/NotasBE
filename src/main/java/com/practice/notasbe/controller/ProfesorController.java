package com.practice.notasbe.controller;


import com.practice.notasbe.entities.Profesor;
import com.practice.notasbe.services.implementations.ProfesorService;
import com.practice.notasbe.shared.dto.ProfesorDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/profe")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> listarProfes(){
        return new ResponseEntity<>(profesorService.listadoDeProfes(), HttpStatus.OK);
    }

    @GetMapping("/{profesorId}")
    public ResponseEntity<Profesor> buscarPorId(@PathVariable Integer profesorId){
        Optional<Profesor> profePodId = profesorService.buscarProfeID(profesorId);
        Profesor profesor = profePodId.orElseThrow(() -> new NoSuchElementException("Profesor no encontrado"));
        return new ResponseEntity<>(profesor,  HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createProfe(@RequestBody ProfesorDTO profesorDTO) {
        profesorService.crearProfesor(profesorDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Profesor created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{profesorId}")
    public ResponseEntity<HttpResponse> updateProfe(@PathVariable Integer profesorId, @RequestBody ProfesorDTO profesorDTO) {
        profesorService.editProfesor(profesorId, profesorDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Profesor updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{profesorId}")
    public ResponseEntity<HttpResponse> deleteProfe(@PathVariable Integer profesorId) {
        profesorService.eliminarProfe(profesorId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Profesor deleted successfully"),
                HttpStatus.OK
        );
    }

}

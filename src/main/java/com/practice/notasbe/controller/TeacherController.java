package com.practice.notasbe.controller;


import com.practice.notasbe.entities.Teacher;
import com.practice.notasbe.services.implementations.TeacherService;
import com.practice.notasbe.shared.dto.TeacherDTO;
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
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> listarProfes(){
        return new ResponseEntity<>(teacherService.listadoDeProfes(), HttpStatus.OK);
    }

    @GetMapping("/{profesorId}")
    public ResponseEntity<Teacher> buscarPorId(@PathVariable Integer profesorId){
        Optional<Teacher> profePodId = teacherService.buscarProfeID(profesorId);
        Teacher teacher = profePodId.orElseThrow(() -> new NoSuchElementException("Profesor no encontrado"));
        return new ResponseEntity<>(teacher,  HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createProfe(@RequestBody TeacherDTO teacherDTO) {
        teacherService.crearProfesor(teacherDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Profesor created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{profesorId}")
    public ResponseEntity<HttpResponse> updateProfe(@PathVariable Integer profesorId, @RequestBody TeacherDTO teacherDTO) {
        teacherService.editProfesor(profesorId, teacherDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Profesor updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{profesorId}")
    public ResponseEntity<HttpResponse> deleteProfe(@PathVariable Integer profesorId) {
        teacherService.eliminarProfe(profesorId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Profesor deleted successfully"),
                HttpStatus.OK
        );
    }

}

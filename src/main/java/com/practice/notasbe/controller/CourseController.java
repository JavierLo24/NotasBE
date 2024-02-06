package com.practice.notasbe.controller;

import com.practice.notasbe.entities.Course;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.services.implementations.CourseService;
import com.practice.notasbe.shared.dto.CourseDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> listarCursos(){
        return new ResponseEntity<>(courseService.listadoDeCursos(), HttpStatus.OK);
    }

    @GetMapping("/{cursoId}")
    public ResponseEntity<Course> buscarPorId(@PathVariable Integer cursoId) throws ItemNotFoundException{
        Course course = courseService.buscarCursoID(cursoId);
        return new ResponseEntity<>(course,  HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createAlumno(@RequestBody CourseDTO courseDTO) throws ItemAlreadyInUseException {
        courseService.crearCurso(courseDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Curso created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{cursoId}")
    public ResponseEntity<HttpResponse> updateAlumno(@PathVariable Integer cursoId, @RequestBody CourseDTO courseDTO) throws ItemNotFoundException {
        courseService.editCurso(cursoId, courseDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Curso updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{cursoId}")
    public ResponseEntity<HttpResponse> deleteClient(@PathVariable Integer cursoId) throws ItemNotFoundException{
        courseService.eliminarCurso(cursoId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Curso deleted successfully"),
                HttpStatus.OK
        );
    }

}

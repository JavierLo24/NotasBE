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

    //Buscar todos los cursos
    @GetMapping
    public ResponseEntity<List<CourseDTO>> listCourses(){
        return new ResponseEntity<>(courseService.listCourse(), HttpStatus.OK);
    }

    //Buscar curso por ID
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> findById(@PathVariable Integer courseId) throws ItemNotFoundException{
        Course course = courseService.findByIdCourse(courseId);
        return new ResponseEntity<>(course,  HttpStatus.OK);
    }

    //Crear o registrar un curso
    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createCourse(@RequestBody CourseDTO courseDTO) throws ItemAlreadyInUseException {
        courseService.createCourse(courseDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Course created successfully"),
                HttpStatus.CREATED);
    }

    //Actualizar un curso
    @PutMapping("/update/{courseId}")
    public ResponseEntity<HttpResponse> updateCourse(@PathVariable Integer courseId, @RequestBody CourseDTO courseDTO) throws ItemNotFoundException {
        courseService.editCourse(courseId, courseDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Course updated successfully"),
                HttpStatus.OK
        );
    }

    //Eliminar un curso
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<HttpResponse> deleteCourse(@PathVariable Integer courseId) throws ItemNotFoundException{
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Course deleted successfully"),
                HttpStatus.OK
        );
    }

}

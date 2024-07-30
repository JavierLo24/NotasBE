package com.practice.notasbe.controller;


import com.practice.notasbe.entities.Teacher;
import com.practice.notasbe.exceptions.ItemNotFoundException;
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
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //Buscar todos los profesores
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> listTeachers(){
        return new ResponseEntity<>(teacherService.listTeachers(), HttpStatus.OK);
    }

    //Buscar profesor por ID
    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> findById(@PathVariable Integer teacherId)throws ItemNotFoundException {
        Teacher teacher = teacherService.findByIdTeacher(teacherId);
        return new ResponseEntity<>(teacher,  HttpStatus.OK);
    }

    //Crear o registrar un profesor
    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createTeacher(@RequestBody TeacherDTO teacherDTO){
        teacherService.createTeacher(teacherDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Teacer created successfully"),
                HttpStatus.CREATED);
    }

    //Actualizar un profesor
    @PutMapping("/update/{teacherId}")
    public ResponseEntity<HttpResponse> updateTeacher(@PathVariable Integer teacherId, @RequestBody TeacherDTO teacherDTO) throws ItemNotFoundException{
        teacherService.editTeacher(teacherId, teacherDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Teacher updated successfully"),
                HttpStatus.OK
        );
    }

    //Eliminar un profesor
    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<HttpResponse> deleteTeacher(@PathVariable Integer teacherId) throws ItemNotFoundException{
        teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Teacher deleted successfully"),
                HttpStatus.OK
        );
    }

}

package com.practice.notasbe.controller;

import com.practice.notasbe.entities.Student;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.services.implementations.StudentService;
import com.practice.notasbe.shared.dto.StudentDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> listarAlumnos(){
        return new ResponseEntity<>(studentService.listadoDeAlumnos(), HttpStatus.OK);
    }

//    @GetMapping("/nombre={name}-apellido={app}")
//    public ResponseEntity<Student> buscarname (@PathVariable String name, @PathVariable String app) throws ItemNotFoundException {
//        Student student = studentService.buscarAlumnoName(name, app);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }

    @GetMapping("/id={alumnoId}")
    public ResponseEntity<Student> buscarPorId(@PathVariable Integer alumnoId) throws ItemNotFoundException {
        Student student = studentService.buscarAlumnoID(alumnoId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createAlumno(@RequestBody StudentDTO alumnoDTO) throws ItemAlreadyInUseException {
        studentService.crearAlumno(alumnoDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Alumno created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{alumnoId}")
    public ResponseEntity<HttpResponse> updateAlumno(@PathVariable Integer alumnoId, @RequestBody StudentDTO alumnoDTO) throws ItemNotFoundException{
        studentService.editAlumno(alumnoId, alumnoDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Alumno updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{alumnoId}")
    public ResponseEntity<HttpResponse> deleteClient(@PathVariable Integer alumnoId) throws ItemNotFoundException {
        studentService.eliminarAlumno(alumnoId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Alumno deleted successfully"),
                HttpStatus.OK
        );
    }
}

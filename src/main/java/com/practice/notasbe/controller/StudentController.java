package com.practice.notasbe.controller;

import com.practice.notasbe.entities.Student;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.models.request.UsuarioRequest;
import com.practice.notasbe.services.implementations.StudentService;
import com.practice.notasbe.services.implementations.UserService;
import com.practice.notasbe.shared.dto.StudentDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> listStudents(){
        return new ResponseEntity<>(studentService.listStudents(), HttpStatus.OK);
    }

    @GetMapping("/findname")
    public ResponseEntity<StudentDTO> findByName (@RequestBody UsuarioRequest usuarioRequest) throws ItemNotFoundException {
        StudentDTO studentDTO = studentService.findByName(usuarioRequest.getNombre(), usuarioRequest.getApellido());
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> buscarPorId(@PathVariable Integer studentId) throws ItemNotFoundException {
        Student student = studentService.findByIdStudent(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createAlumno(@RequestBody StudentDTO studentDTO) throws ItemAlreadyInUseException {
        studentService.createStudent(studentDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Student created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<HttpResponse> updateAlumno(@PathVariable Integer studentId, @RequestBody StudentDTO alumnoDTO) throws ItemNotFoundException{
        studentService.editStudent(studentId, alumnoDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Student updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<HttpResponse> deleteClient(@PathVariable Integer studentId) throws ItemNotFoundException {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Student deleted successfully"),
                HttpStatus.OK
        );
    }
}

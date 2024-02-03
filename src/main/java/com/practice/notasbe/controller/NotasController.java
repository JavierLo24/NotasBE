package com.practice.notasbe.controller;


import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.entities.Notas;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.services.implementations.NotasService;
import com.practice.notasbe.shared.dto.AlumnoDTO;
import com.practice.notasbe.shared.dto.NotasDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
public class NotasController {

    @Autowired
    NotasService notasService;

    @GetMapping("/nombre={nombre}-apellido={apellido}")
    public ResponseEntity<NotasDTO> buscarPorAlumno(@PathVariable String nombre, @PathVariable String apellido) throws ItemNotFoundException{
        NotasDTO notas = notasService.notasPorAlumno(nombre, apellido);
        return new ResponseEntity<>(notas,  HttpStatus.OK);
    }

    @GetMapping("/curso/{cursoName}")
    public ResponseEntity<List<NotasDTO>> buscarPorCurso(@PathVariable String cursoName) throws ItemNotFoundException{
        List<NotasDTO> notas = notasService.notasPorCurso(cursoName);
        return new ResponseEntity<>(notas,  HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createAlumno(@RequestBody NotasDTO notaDTO) {
        notasService.crearNota(notaDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Nota created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{notaID}")
    public ResponseEntity<HttpResponse> updateAlumno(@PathVariable Integer notaID, @RequestBody NotasDTO notaDTO) throws ItemNotFoundException{
        notasService.editNota(notaID, notaDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Nota updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{notaID}")
    public ResponseEntity<HttpResponse> deleteClient(@PathVariable Integer notaID) throws ItemNotFoundException {
        notasService.eliminarNota(notaID);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Nota deleted successfully"),
                HttpStatus.OK
        );
    }

}

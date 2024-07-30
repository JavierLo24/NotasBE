package com.practice.notasbe.controller;


import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.services.implementations.GradesService;
import com.practice.notasbe.shared.dto.GradesDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class GradesController {

    @Autowired
    GradesService gradesService;

//    @GetMapping("/nombre={nombre}-apellido={apellido}")
//    public ResponseEntity<GradesDTO> buscarPorAlumno(@PathVariable String nombre, @PathVariable String apellido) throws ItemNotFoundException{
//        GradesDTO notas = gradesService.notasPorAlumno(nombre, apellido);
//        return new ResponseEntity<>(notas,  HttpStatus.OK);
//    }

//    @GetMapping("/curso/{cursoName}")
//    public ResponseEntity<List<GradesDTO>> buscarPorCurso(@PathVariable String cursoName) throws ItemNotFoundException{
//        List<GradesDTO> notas = gradesService.notasPorCurso(cursoName);
//        return new ResponseEntity<>(notas,  HttpStatus.OK);
//    }

    //Crear nota con actividad
    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createAlumno(@RequestBody GradesDTO notaDTO) {
        gradesService.crearNota(notaDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "Nota created successfully"),
                HttpStatus.CREATED);
    }

    //Actualizar nota 
    @PutMapping("/update/{notaID}")
    public ResponseEntity<HttpResponse> updateAlumno(@PathVariable Integer notaID, @RequestBody GradesDTO notaDTO) throws ItemNotFoundException{
        gradesService.editNota(notaID, notaDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Nota updated successfully"),
                HttpStatus.OK
        );
    }

    //Eliminar nota
    @DeleteMapping("/delete/{notaID}")
    public ResponseEntity<HttpResponse> deleteClient(@PathVariable Integer notaID) throws ItemNotFoundException {
        gradesService.eliminarNota(notaID);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "Nota deleted successfully"),
                HttpStatus.OK
        );
    }

}

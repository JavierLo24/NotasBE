package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.entities.Curso;
import com.practice.notasbe.entities.Notas;
import com.practice.notasbe.repositories.AlumnoRepository;
import com.practice.notasbe.repositories.CursoRepository;
import com.practice.notasbe.repositories.NotasRepository;
import com.practice.notasbe.services.interfaces.NotasServiceInterface;
import com.practice.notasbe.shared.dto.NotasDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("notasService")
public class NotasService implements NotasServiceInterface {

    @Autowired
    NotasRepository notasRepository;
    @Autowired
    AlumnoRepository alumnoRepository;
    @Autowired
    CursoRepository cursoRepository;

    private NotasDTO convertToNotasDTO(Notas notas) {
        NotasDTO notasDTO = new NotasDTO();
        BeanUtils.copyProperties(notas, notasDTO);
        return notasDTO;
    }

    @Override
    public NotasDTO notasPorAlumno(String alumnoName) {
        Alumno alumno = alumnoRepository.findByNombres(alumnoName);
        Notas notas = notasRepository.findByIdAlumno(alumno);
        NotasDTO notasDTO = this.convertToNotasDTO(notas);
        return notasDTO;
    }

    @Override
    public List<NotasDTO> notasPorCurso(String cursoName) {
        Curso curso = cursoRepository.findByNombre(cursoName);
        List<Notas> notas = notasRepository.findByIdCurso(curso);
        return notas.stream()
                .map(this::convertToNotasDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotasDTO crearNota(NotasDTO nota) {
        return null;
    }

    @Override
    public NotasDTO editNota(Integer cursoID, NotasDTO nota) {
        return null;
    }
}

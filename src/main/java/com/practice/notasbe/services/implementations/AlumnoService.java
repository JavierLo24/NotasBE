package com.practice.notasbe.services.implementations;


import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.repositories.AlumnoRepository;
import com.practice.notasbe.services.interfaces.AlumnoServiceInterface;
import com.practice.notasbe.shared.dto.AlumnoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("alumnoService")
public class AlumnoService implements AlumnoServiceInterface {

    @Autowired
    AlumnoRepository alumnoRepository;

    private AlumnoDTO convertToAlumnoDTO(Alumno alumno) {
        AlumnoDTO alumnoDTO = new AlumnoDTO();
        BeanUtils.copyProperties(alumno, alumnoDTO);
        return alumnoDTO;
    }

    @Override
    public List<AlumnoDTO> listadoDeAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream()
                .map(this::convertToAlumnoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlumnoDTO crearAlumno (AlumnoDTO alumnodto){
        Alumno alumno = new Alumno();
        BeanUtils.copyProperties(alumnodto, alumno);
        Alumno nuevoAlumno = alumnoRepository.save(alumno);
        AlumnoDTO newAlumnDto = convertToAlumnoDTO(nuevoAlumno);
//        BeanUtils.copyProperties(nuevoAlumno, newAlumnDto);
        return newAlumnDto;
    }

    @Override
    public AlumnoDTO editAlumno(Integer alumnoID, AlumnoDTO alumnoDto){
        Alumno alumno = alumnoRepository.findById(alumnoID).orElse(null);
        BeanUtils.copyProperties(alumnoDto, alumno);
        Alumno updatedAlumno = alumnoRepository.save(alumno);
        AlumnoDTO updatedAlumnoDTO = convertToAlumnoDTO(updatedAlumno);
//        BeanUtils.copyProperties(updatedAlumno, updatedAlumnoDTO);
        return updatedAlumnoDTO;
    }


    @Override
    public void eliminarAlumno(int id){
        alumnoRepository.deleteById(id);
    }

    @Override
    public Optional<Alumno> buscarAlumnoID(int id){
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        return alumno;
    }

    public Alumno buscarALumno(String name){
        Alumno alumno = alumnoRepository.findByNombres(name);
        return alumno;
    }


}

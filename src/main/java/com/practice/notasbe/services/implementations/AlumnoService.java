package com.practice.notasbe.services.implementations;


import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
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

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

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
    public AlumnoDTO crearAlumno (AlumnoDTO alumnodto) throws ItemAlreadyInUseException{
        Alumno alumno = alumnoRepository.findByNombresAndApellidos(alumnodto.getNombres(), alumnodto.getApellidos());
        if (alumno != null) throw new ItemAlreadyInUseException(String.format(IS_ALREADY_USE, "ALUMNO").toUpperCase());
        Alumno alumno1 = new Alumno();
        BeanUtils.copyProperties(alumnodto, alumno1);
        Alumno nuevoAlumno = alumnoRepository.save(alumno1);
        return convertToAlumnoDTO(nuevoAlumno);
    }

    @Override
    public AlumnoDTO editAlumno(Integer alumnoID, AlumnoDTO alumnoDto) throws ItemNotFoundException{
        Alumno alumno = alumnoRepository.findById(alumnoID).orElse(null);
        if (alumno == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
        BeanUtils.copyProperties(alumnoDto, alumno);
        Alumno updatedAlumno = alumnoRepository.save(alumno);
        return convertToAlumnoDTO(updatedAlumno);
    }

    @Override
    public void eliminarAlumno(int id) throws ItemNotFoundException {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
        alumnoRepository.deleteById(id);
    }

    @Override
    public Alumno buscarAlumnoID(int id) throws ItemNotFoundException {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
        return alumno;
    }

    @Override
    public Alumno buscarAlumnoName(String nombre, String apellidos) throws ItemNotFoundException{
        Alumno alumno = alumnoRepository.findByNombresAndApellidos(nombre, apellidos);
        if (alumno == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
        return alumnoRepository.findByNombresAndApellidos(nombre, apellidos);
    }

}

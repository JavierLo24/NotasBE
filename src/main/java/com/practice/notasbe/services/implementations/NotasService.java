package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.entities.Curso;
import com.practice.notasbe.entities.Notas;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
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

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

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
    public NotasDTO notasPorAlumno(String nombre, String apellido) throws ItemNotFoundException{
        Alumno alumno = alumnoRepository.findByNombresAndApellidos(nombre, apellido);
        if (alumno == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "ALUMNO").toUpperCase());
        Notas notas = notasRepository.findByIdAlumno(alumno);
        return this.convertToNotasDTO(notas);
    }

    @Override
    public List<NotasDTO> notasPorCurso(String cursoName) throws ItemNotFoundException{
        Curso curso = cursoRepository.findByNombre(cursoName);
        if (curso == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "CURSO").toUpperCase());
        List<Notas> notas = notasRepository.findByIdCurso(curso);
        return notas.stream()
                .map(this::convertToNotasDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotasDTO crearNota(NotasDTO notaDTO) {
        Notas nota = new Notas();
        BeanUtils.copyProperties(notaDTO, nota);
        Notas nuevaNota = notasRepository.save(nota);
        return convertToNotasDTO(nuevaNota);
    }

    @Override
    public NotasDTO editNota(Integer notaID, NotasDTO notaDTO) throws ItemNotFoundException{
        Notas nota = notasRepository.findById(notaID).orElse(null);
        if (nota == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "NOTA").toUpperCase());
        BeanUtils.copyProperties(notaDTO, nota);
        Notas updatedNota = notasRepository.save(nota);
        return convertToNotasDTO(updatedNota);
    }

    @Override
    public void eliminarNota(int id) throws ItemNotFoundException{
        Notas nota = notasRepository.findById(id).orElse(null);
        if (nota == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "NOTA").toUpperCase());
        notasRepository.deleteById(id);
    }
}

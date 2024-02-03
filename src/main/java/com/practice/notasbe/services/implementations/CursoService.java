package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Alumno;
import com.practice.notasbe.entities.Curso;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.repositories.CursoRepository;
import com.practice.notasbe.services.interfaces.CursoServiceInterface;
import com.practice.notasbe.shared.dto.CursoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("cursoService")
public class CursoService implements CursoServiceInterface {

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

    @Autowired
    CursoRepository cursoRepository;

    private CursoDTO convertToCursoDTO(Curso curso) {
        CursoDTO cursoDTO = new CursoDTO();
        BeanUtils.copyProperties(curso, cursoDTO);
        return cursoDTO;
    }

    @Override
    public List<CursoDTO> listadoDeCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream()
                .map(this::convertToCursoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CursoDTO crearCurso(CursoDTO cursoDto) throws ItemAlreadyInUseException {
        curseNameInUse(cursoDto.getNombre());
        Curso curso = new Curso();
        BeanUtils.copyProperties(cursoDto, curso);
        Curso nuevoCurso = cursoRepository.save(curso);
        return convertToCursoDTO(nuevoCurso);
    }

    @Override
    public CursoDTO editCurso(Integer cursoID, CursoDTO cursoDto)  throws ItemNotFoundException{
        Curso curso = cursoRepository.findById(cursoID).orElse(null);
        if (curso == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "CURSO").toUpperCase());
        BeanUtils.copyProperties(cursoDto, curso);
        Curso updatedCurso = cursoRepository.save(curso);
        return convertToCursoDTO(updatedCurso);
    }


    @Override
    public void eliminarCurso(int id) throws ItemNotFoundException {
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "CURSO").toUpperCase());
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso buscarCursoID(int id) throws ItemNotFoundException {
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "CURSO").toUpperCase());
        return curso;
    }

    private void curseNameInUse (String name) throws ItemAlreadyInUseException {
        Curso curso = cursoRepository.findByNombre(name);
        if (curso == null) return;
        if (curso.getNombre().equalsIgnoreCase(name))
            throw new ItemAlreadyInUseException(String.format(IS_ALREADY_USE, "NOMBRE DE CURSO").toUpperCase());
    }

}

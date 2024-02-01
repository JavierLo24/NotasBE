package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Curso;
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
    public CursoDTO crearCurso(CursoDTO cursoDto){
        Curso curso = new Curso();
        BeanUtils.copyProperties(cursoDto, curso);
        Curso nuevoCurso = cursoRepository.save(curso);
        CursoDTO newCursoDto = new CursoDTO();
        BeanUtils.copyProperties(nuevoCurso, newCursoDto);
        return newCursoDto;
    }

    @Override
    public CursoDTO editCurso(Integer cursoID, CursoDTO cursoDto){
        Curso curso = cursoRepository.findById(cursoID).orElse(null);
        BeanUtils.copyProperties(cursoDto, curso);
        Curso updatedCurso = cursoRepository.save(curso);
        CursoDTO updatedCursoDTO = new CursoDTO();
        BeanUtils.copyProperties(updatedCurso, updatedCursoDTO);
        return updatedCursoDTO;
    }


    @Override
    public void eliminarCurso(int id){cursoRepository.deleteById(id);
    }

    @Override
    public Optional<Curso> buscarCursoID(int id){
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso;
    }

}

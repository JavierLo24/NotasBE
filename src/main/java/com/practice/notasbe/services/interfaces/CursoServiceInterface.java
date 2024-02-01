package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Curso;
import com.practice.notasbe.shared.dto.CursoDTO;

import java.util.List;
import java.util.Optional;

public interface CursoServiceInterface {

    public List<CursoDTO> listadoDeCursos();
    public CursoDTO crearCurso(CursoDTO curso);
    public CursoDTO editCurso(Integer cursoID, CursoDTO curso);
    public void eliminarCurso(int id);
    public Optional<Curso> buscarCursoID(int id);

}

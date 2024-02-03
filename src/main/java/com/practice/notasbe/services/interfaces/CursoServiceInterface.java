package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Curso;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.CursoDTO;

import java.util.List;
import java.util.Optional;

public interface CursoServiceInterface {

    public List<CursoDTO> listadoDeCursos();
    public CursoDTO crearCurso(CursoDTO curso) throws ItemAlreadyInUseException;
    public CursoDTO editCurso(Integer cursoID, CursoDTO curso) throws ItemNotFoundException;
    public void eliminarCurso(int id)  throws ItemNotFoundException;
    public Curso buscarCursoID(int id)  throws ItemNotFoundException;

}

package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.shared.dto.NotasDTO;

import java.util.List;

public interface NotasServiceInterface {

    public NotasDTO notasPorAlumno(String alumnoName);
    public List<NotasDTO> notasPorCurso (String curso);
    public NotasDTO crearNota(NotasDTO nota);
    public NotasDTO editNota(Integer cursoID, NotasDTO nota);
}

package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Notas;
import com.practice.notasbe.shared.dto.NotasDTO;

import java.util.List;
import java.util.Optional;

public interface NotasServiceInterface {

    public List<Notas> listadoDeNotas();
    public NotasDTO crearNota(NotasDTO nota, int id_alumno);
}

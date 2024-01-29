package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Profesor;
import com.practice.notasbe.shared.dto.ProfesorDTO;

import java.util.List;
import java.util.Optional;

public interface ProfesorServiceInterface {

    public List<Profesor> listadoDeProfes();
    public ProfesorDTO crearProfesor(ProfesorDTO profesor);
    public ProfesorDTO editProfesor(ProfesorDTO profesor, Integer profeID);
    public void eliminarProfe(int id);
    public Optional<Profesor> buscarProfeID(int id);
}

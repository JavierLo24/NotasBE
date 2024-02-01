package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Profesor;
import com.practice.notasbe.shared.dto.ProfesorDTO;

import java.util.List;
import java.util.Optional;

public interface ProfesorServiceInterface {

    public List<ProfesorDTO> listadoDeProfes();
    public ProfesorDTO crearProfesor(ProfesorDTO profesor);
    public ProfesorDTO editProfesor(Integer profeID, ProfesorDTO profesor);
    public void eliminarProfe(int id);
    public Optional<Profesor> buscarProfeID(int id);
}

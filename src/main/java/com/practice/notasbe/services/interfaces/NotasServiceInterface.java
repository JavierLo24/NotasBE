package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.NotasDTO;

import java.util.List;

public interface NotasServiceInterface {

    public NotasDTO notasPorAlumno(String nombre, String apellido) throws ItemNotFoundException;
    public List<NotasDTO> notasPorCurso (String curso) throws ItemNotFoundException;
    public NotasDTO crearNota(NotasDTO notaDTO);
    public NotasDTO editNota(Integer notaID, NotasDTO notaDTO) throws ItemNotFoundException;
    public void eliminarNota(int id) throws ItemNotFoundException;
}

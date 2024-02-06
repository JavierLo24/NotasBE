package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.GradesDTO;

import java.util.List;

public interface GradesServiceInterface {

//    public GradesDTO notasPorAlumno(String nombre, String apellido) throws ItemNotFoundException;
//    public List<GradesDTO> notasPorCurso (String curso) throws ItemNotFoundException;
    public GradesDTO crearNota(GradesDTO notaDTO);
    public GradesDTO editNota(Integer notaID, GradesDTO notaDTO) throws ItemNotFoundException;
    public void eliminarNota(int id) throws ItemNotFoundException;
}

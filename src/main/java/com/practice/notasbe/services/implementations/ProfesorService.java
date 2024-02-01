package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Profesor;
import com.practice.notasbe.repositories.ProfesorRepository;
import com.practice.notasbe.services.interfaces.ProfesorServiceInterface;
import com.practice.notasbe.shared.dto.ProfesorDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("profesorService")
public class ProfesorService implements ProfesorServiceInterface {

    @Autowired
    ProfesorRepository profesorRepository;

    private ProfesorDTO convertToProfesorDTO(Profesor profesor) {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        BeanUtils.copyProperties(profesor, profesorDTO);
        return profesorDTO;
    }

    @Override
    public List<ProfesorDTO> listadoDeProfes() {
        List<Profesor> profes = profesorRepository.findAll();
        return profes.stream()
                .map(this::convertToProfesorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProfesorDTO crearProfesor(ProfesorDTO profesorDTO){
        Profesor profesor = new Profesor();
        BeanUtils.copyProperties(profesorDTO, profesor);
        Profesor nuevoProfe = profesorRepository.save(profesor);
        ProfesorDTO nuevoProfeDto = new ProfesorDTO();
        BeanUtils.copyProperties(nuevoProfe, nuevoProfeDto);
        return nuevoProfeDto;
    }

    @Override
    public ProfesorDTO editProfesor(Integer profeID, ProfesorDTO profesorDTO){
        Profesor profesor = profesorRepository.findById(profeID).orElse(null);
        BeanUtils.copyProperties(profesorDTO, profesor);
        Profesor updatedProfe = profesorRepository.save(profesor);
        ProfesorDTO updatedProfeDTO = new ProfesorDTO();
        BeanUtils.copyProperties(updatedProfe, updatedProfeDTO);
        return updatedProfeDTO;
    }


    @Override
    public void eliminarProfe(int id){
        profesorRepository.deleteById(id);
    }

    @Override
    public Optional<Profesor> buscarProfeID(int id){
        Optional<Profesor> profesor = profesorRepository.findById(id);
        return profesor;
    }




}

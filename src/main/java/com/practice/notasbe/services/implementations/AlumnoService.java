package com.practice.notasbe.services.implementations;


import com.practice.notasbe.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("alumnoService")
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;






}

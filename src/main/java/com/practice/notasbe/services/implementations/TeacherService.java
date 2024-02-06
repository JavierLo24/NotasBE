package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Teacher;
import com.practice.notasbe.repositories.TeacherRepository;
import com.practice.notasbe.services.interfaces.TeacherServiceInterface;
import com.practice.notasbe.shared.dto.TeacherDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("profesorService")
public class TeacherService implements TeacherServiceInterface {

    @Autowired
    TeacherRepository teacherRepository;

    private TeacherDTO convertToProfesorDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(teacher, teacherDTO);
        return teacherDTO;
    }

    @Override
    public List<TeacherDTO> listadoDeProfes() {
        List<Teacher> profes = teacherRepository.findAll();
        return profes.stream()
                .map(this::convertToProfesorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO crearProfesor(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);
        Teacher nuevoProfe = teacherRepository.save(teacher);
        return convertToProfesorDTO(nuevoProfe);
    }

    @Override
    public TeacherDTO editProfesor(Integer profeID, TeacherDTO teacherDTO){
        Teacher teacher = teacherRepository.findById(profeID).orElse(null);
        assert teacher != null;
        BeanUtils.copyProperties(teacherDTO, teacher);
        Teacher updatedProfe = teacherRepository.save(teacher);
        return convertToProfesorDTO(updatedProfe);
    }

    @Override
    public void eliminarProfe(int id){
        teacherRepository.deleteById(id);
    }

    @Override
    public Optional<Teacher> buscarProfeID(int id){
        return teacherRepository.findById(id);
    }

}

package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Course;
import com.practice.notasbe.entities.Teacher;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.repositories.TeacherRepository;
import com.practice.notasbe.services.interfaces.TeacherServiceInterface;
import com.practice.notasbe.shared.dto.TeacherDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("teacherService")
public class TeacherService implements TeacherServiceInterface {

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

    @Autowired
    TeacherRepository teacherRepository;

    private TeacherDTO convertToTeacherDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(teacher, teacherDTO);
        return teacherDTO;
    }

    @Override
    public List<TeacherDTO> listTeachers() {
        List<Teacher> teacher = teacherRepository.findAll();
        return teacher.stream()
                .map(this::convertToTeacherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);
        Teacher newTeacher = teacherRepository.save(teacher);
        return convertToTeacherDTO(newTeacher);
    }

    @Override
    public TeacherDTO editTeacher(Integer teacherID, TeacherDTO teacherDTO) throws ItemNotFoundException{
        Teacher teacher = teacherRepository.findById(teacherID).orElse(null);
        if (teacher == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "TEACHER").toUpperCase());
        BeanUtils.copyProperties(teacherDTO, teacher);
        Teacher upTeacher = teacherRepository.save(teacher);
        return convertToTeacherDTO(upTeacher);
    }

    @Override
    public void deleteTeacher(int id) throws ItemNotFoundException{
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "TEACHER").toUpperCase());
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher findByIdTeacher(int id) throws ItemNotFoundException{
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "TEACHER").toUpperCase());
        return teacher;
    }

}

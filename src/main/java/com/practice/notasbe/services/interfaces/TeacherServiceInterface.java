package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Teacher;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.TeacherDTO;

import java.util.List;
import java.util.Optional;

public interface TeacherServiceInterface {

    public List<TeacherDTO> listTeachers();
    public TeacherDTO createTeacher(TeacherDTO teacherDTO);
    public TeacherDTO editTeacher(Integer teacherId, TeacherDTO teacherDTO) throws ItemNotFoundException;
    public void deleteTeacher(int id) throws ItemNotFoundException;
    public Teacher findByIdTeacher(int id) throws ItemNotFoundException;
}

package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Course;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.repositories.CourseRepository;
import com.practice.notasbe.services.interfaces.CourseServiceInterface;
import com.practice.notasbe.shared.dto.CourseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("cursoService")
public class CourseService implements CourseServiceInterface {

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

    @Autowired
    CourseRepository courseRepository;

    private CourseDTO convertToCursoDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        return courseDTO;
    }

    @Override
    public List<CourseDTO> listadoDeCursos() {
        List<Course> course = courseRepository.findAll();
        return course.stream()
                .map(this::convertToCursoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO crearCurso(CourseDTO courseDto) throws ItemAlreadyInUseException {
        curseNameInUse(courseDto.getName());
        Course course = new Course();
        BeanUtils.copyProperties(courseDto, course);
        Course nuevoCourse = courseRepository.save(course);
        return convertToCursoDTO(nuevoCourse);
    }

    @Override
    public CourseDTO editCurso(Integer cursoID, CourseDTO courseDto)  throws ItemNotFoundException{
        Course course = courseRepository.findById(cursoID).orElse(null);
        if (course == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "CURSO").toUpperCase());
        BeanUtils.copyProperties(courseDto, course);
        Course updatedCourse = courseRepository.save(course);
        return convertToCursoDTO(updatedCourse);
    }


    @Override
    public void eliminarCurso(int id) throws ItemNotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "CURSO").toUpperCase());
        courseRepository.deleteById(id);
    }

    @Override
    public Course buscarCursoID(int id) throws ItemNotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "CURSO").toUpperCase());
        return course;
    }

    private void curseNameInUse (String name) throws ItemAlreadyInUseException {
        Course course = courseRepository.findByName(name);
        if (course == null) return;
        if (course.getName().equalsIgnoreCase(name))
            throw new ItemAlreadyInUseException(String.format(IS_ALREADY_USE, "NOMBRE DE CURSO").toUpperCase());
    }

}

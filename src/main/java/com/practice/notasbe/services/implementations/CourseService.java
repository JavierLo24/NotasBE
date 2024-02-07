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

@Service("courseService")
public class CourseService implements CourseServiceInterface {

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

    @Autowired
    CourseRepository courseRepository;

    private CourseDTO convertToCourseDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        return courseDTO;
    }

    @Override
    public List<CourseDTO> listCourse() {
        List<Course> course = courseRepository.findAll();
        return course.stream()
                .map(this::convertToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) throws ItemAlreadyInUseException {
        curseNameInUse(courseDTO.getName());
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        Course nuevoCourse = courseRepository.save(course);
        return convertToCourseDTO(nuevoCourse);
    }

    @Override
    public CourseDTO editCourse(Integer courseID, CourseDTO courseDTO)  throws ItemNotFoundException{
        Course course = courseRepository.findById(courseID).orElse(null);
        if (course == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "COURSE").toUpperCase());
        BeanUtils.copyProperties(courseDTO, course);
        Course updatedCourse = courseRepository.save(course);
        return convertToCourseDTO(updatedCourse);
    }


    @Override
    public void deleteCourse(int id) throws ItemNotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "COURSE").toUpperCase());
        courseRepository.deleteById(id);
    }

    @Override
    public Course findByIdCourse(int id) throws ItemNotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "COURSE").toUpperCase());
        return course;
    }

    private void curseNameInUse(String name) throws ItemAlreadyInUseException {
        Course course = courseRepository.findByName(name);
        if (course == null) return;
        if (course.getName().equalsIgnoreCase(name))
            throw new ItemAlreadyInUseException(String.format(IS_ALREADY_USE, "COURSE'S NAME").toUpperCase());
    }

}

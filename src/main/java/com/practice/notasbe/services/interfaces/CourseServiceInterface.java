package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.Course;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.CourseDTO;

import java.util.List;

public interface CourseServiceInterface {

    public List<CourseDTO> listCourse();
    public CourseDTO createCourse(CourseDTO course) throws ItemAlreadyInUseException;
    public CourseDTO editCourse(Integer courseID, CourseDTO course) throws ItemNotFoundException;
    public void deleteCourse(int id)  throws ItemNotFoundException;
    public Course findByIdCourse(int id)  throws ItemNotFoundException;

}

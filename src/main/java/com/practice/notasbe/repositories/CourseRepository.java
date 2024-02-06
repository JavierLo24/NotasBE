package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
    Course findByName (String name);
}

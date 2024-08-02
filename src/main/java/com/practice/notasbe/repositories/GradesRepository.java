package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.Grades;
import com.practice.notasbe.entities.Student;
import com.practice.notasbe.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Integer>{
    List<Grades> findByStudentId(Student studentId);
    List<Grades> findByCourseId(Course courseId);
}

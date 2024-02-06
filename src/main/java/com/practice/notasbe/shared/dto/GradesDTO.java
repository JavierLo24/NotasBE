package com.practice.notasbe.shared.dto;

import com.practice.notasbe.entities.Student;
import com.practice.notasbe.entities.Course;
import lombok.Data;

@Data
public class GradesDTO {
    private Student student_id;

    private Course course_id;

    private double nota;

    private String desc_nota;
}

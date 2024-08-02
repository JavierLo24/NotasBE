package com.practice.notasbe.shared.dto;

import com.practice.notasbe.entities.Student;
import com.practice.notasbe.entities.Course;
import lombok.Data;

@Data
public class GradesDTO {
    private String studentName;

    private String studentLastName;

    private String courseTeacher;

    private String courseName;

    private double grade;

    private String descGrade;
}

package com.practice.notasbe.shared.dto;

import com.practice.notasbe.entities.Student;
import com.practice.notasbe.entities.Course;
import lombok.Data;

@Data
public class GradesDTO {
    private Student studentId;

    private Course courseId;

    private double nota;

    private String descNota;
}

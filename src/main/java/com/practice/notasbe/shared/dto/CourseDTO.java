package com.practice.notasbe.shared.dto;


import com.practice.notasbe.entities.Teacher;
import lombok.Data;

@Data
public class CourseDTO {
    private String name;
    private Teacher teacher_id;
}

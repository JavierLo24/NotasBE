package com.practice.notasbe.shared.dto;


import com.practice.notasbe.entities.User;
import lombok.Data;

import java.util.Date;

@Data
public class StudentDTO {
    private Date fechaNac;

    private String school_grade;

    private User user_id;
}

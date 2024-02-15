package com.practice.notasbe.shared.dto;


import com.practice.notasbe.entities.UserE;
import lombok.Data;

import java.util.Date;

@Data
public class StudentDTO {
    private Date date_born;

    private String school_grade;

    private UserE userEId;
}

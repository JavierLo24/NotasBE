package com.practice.notasbe.shared.dto;

import com.practice.notasbe.entities.UserE;
import lombok.Data;

@Data
public class TeacherDTO {

    private String knowledge;

    private String assignment;

    private UserE userEId;

}

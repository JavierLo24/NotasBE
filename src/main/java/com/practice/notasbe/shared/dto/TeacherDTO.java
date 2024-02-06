package com.practice.notasbe.shared.dto;

import com.practice.notasbe.entities.User;
import lombok.Data;

@Data
public class TeacherDTO {

    private String knowledge;

    private String assignment;

    private User user_id;

}

package com.practice.notasbe.shared.dto;

import com.practice.notasbe.entities.Rol;
import lombok.Data;

@Data
public class UserDTO {

    private String email;

    private Rol rolId;

    private String firstName;

    private String lastName;

    private String address;

    private String cellphone;

    private String dni;


}

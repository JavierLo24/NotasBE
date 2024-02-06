package com.practice.notasbe.services.interfaces;

import com.practice.notasbe.entities.User;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.shared.dto.UserDTO;

import java.util.List;

public interface UserServiceInterface {

    public List<UserDTO> listUsers();
    public UserDTO createUser(UserDTO userDTO) throws ItemAlreadyInUseException;
    public UserDTO editUser(Integer userId, UserDTO UserDTO) throws ItemNotFoundException;
    public void deleteUser(int id) throws ItemNotFoundException;
    public User findByIDUser(int id) throws ItemNotFoundException;

}

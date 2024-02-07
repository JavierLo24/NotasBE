package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Student;
import com.practice.notasbe.entities.User;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.repositories.UserRepository;
import com.practice.notasbe.services.interfaces.UserServiceInterface;
import com.practice.notasbe.shared.dto.StudentDTO;
import com.practice.notasbe.shared.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserService implements UserServiceInterface {

    public static final String IS_ALREADY_USE = "The %s is already use";
    public static final String IS_NOT_FOUND = "The %s is not found";

    @Autowired
    UserRepository userRepository;

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public List<UserDTO> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws ItemAlreadyInUseException {
        User user = userRepository.findByFirstNameAndLastName(userDTO.getFirstName(), userDTO.getLastName());
        if (user == null) throw new ItemAlreadyInUseException(String.format(IS_ALREADY_USE, "USER").toUpperCase());
        BeanUtils.copyProperties(userDTO, user);
        User newUser = userRepository.save(user);
        return convertToUserDTO(newUser);
    }

    @Override
    public UserDTO editUser(Integer userId, UserDTO userDTO) throws ItemNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "USER").toUpperCase());
        BeanUtils.copyProperties(userDTO, user);
        User updatedUser = userRepository.save(user);
        return convertToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(int id) throws ItemNotFoundException {
        User user = findByIDUser(id);
        if (user == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "USER").toUpperCase());
        userRepository.deleteById(id);
    }

    @Override
    public User findByIDUser(int id) throws ItemNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "USER").toUpperCase());
        return user;
    }
}

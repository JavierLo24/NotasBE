package com.practice.notasbe.services.implementations;

import com.practice.notasbe.entities.Rol;
import com.practice.notasbe.entities.UserE;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.repositories.RolRepository;
import com.practice.notasbe.repositories.UserRepository;
import com.practice.notasbe.services.interfaces.UserServiceInterface;
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

    @Autowired
    RolRepository rolRepository;

    private UserDTO convertToUserDTO(UserE userE) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userE, userDTO);
        return userDTO;
    }

    @Override
    public List<UserDTO> listUsers() {
        List<UserE> userES = userRepository.findAll();
        return userES.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws ItemAlreadyInUseException {
        UserE userE = userRepository.findByFirstNameAndLastName(userDTO.getFirstName(), userDTO.getLastName());
        if (userE == null) throw new ItemAlreadyInUseException(String.format(IS_ALREADY_USE, "USER").toUpperCase());
        BeanUtils.copyProperties(userDTO, userE);
        UserE newUserE = userRepository.save(userE);
        return convertToUserDTO(newUserE);
    }

    @Override
    public UserDTO editUser(Integer userId, UserDTO userDTO) throws ItemNotFoundException {
        UserE userE = userRepository.findById(userId).orElse(null);
        if (userE == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "USER").toUpperCase());
        BeanUtils.copyProperties(userDTO, userE);
        UserE updatedUserE = userRepository.save(userE);
        return convertToUserDTO(updatedUserE);
    }

    @Override
    public void deleteUser(int id) throws ItemNotFoundException {
        UserE userE = findByIDUser(id);
        if (userE == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "USER").toUpperCase());
        userRepository.deleteById(id);
    }

    @Override
    public UserE findByIDUser(int id) throws ItemNotFoundException {
        UserE userE = userRepository.findById(id).orElse(null);
        if (userE == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "USER").toUpperCase());
        return userE;
    }

    @Override
    public List<UserDTO> listUsersByRol(String rolDesc){
        Rol rol = rolRepository.findByDescription(rolDesc);
        List<UserE> userES = userRepository.findByRolId(rol);
        return userES.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByNames(String fName, String lName) throws ItemNotFoundException{
        UserE userE = userRepository.findByFirstNameAndLastName(fName, lName);
        if(userE == null) throw new ItemNotFoundException(String.format(IS_NOT_FOUND, "USER").toUpperCase());
        return convertToUserDTO(userE);
    }

}

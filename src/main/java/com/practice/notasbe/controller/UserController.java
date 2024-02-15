package com.practice.notasbe.controller;


import com.practice.notasbe.entities.UserE;
import com.practice.notasbe.exceptions.ItemAlreadyInUseException;
import com.practice.notasbe.exceptions.ItemNotFoundException;
import com.practice.notasbe.services.implementations.UserService;
import com.practice.notasbe.shared.dto.UserDTO;
import com.practice.notasbe.shared.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> listUsers(){
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserE> findById(@PathVariable Integer userId) throws ItemNotFoundException {
        UserE userE = userService.findByIDUser(userId);
        return new ResponseEntity<>(userE,  HttpStatus.OK);
    }

    @GetMapping("/rol/{userRol}")
    public ResponseEntity<List<UserDTO>> findByRol(@PathVariable String userRol) throws ItemNotFoundException {
        return new ResponseEntity<>(userService.listUsersByRol(userRol),  HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createAlumno(@RequestBody UserDTO userDTO) throws ItemAlreadyInUseException {
        userService.createUser(userDTO);
        return new ResponseEntity<>(new HttpResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), "UserE created successfully"),
                HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<HttpResponse> updateAlumno(@PathVariable Integer userId, @RequestBody UserDTO userDTO) throws ItemNotFoundException {
        userService.editUser(userId, userDTO);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "UserE updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<HttpResponse> deleteClient(@PathVariable Integer userId) throws ItemNotFoundException{
        userService.deleteUser(userId);
        return new ResponseEntity<>(
                new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), "UserE deleted successfully"),
                HttpStatus.OK
        );
    }


}

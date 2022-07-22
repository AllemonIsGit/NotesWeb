package com.allemon.notesweb.controllers;

import com.allemon.notesweb.domain.dto.CreateUserRequest;
import com.allemon.notesweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping
//    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
//        userService.create(createUserRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
}

package com.allemon.notesweb.controller;

import com.allemon.notesweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

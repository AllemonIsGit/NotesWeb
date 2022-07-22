package com.allemon.notesweb.domain.mapper;

import com.allemon.notesweb.domain.dto.CreateUserRequest;
import com.allemon.notesweb.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User mapToUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .email(createUserRequest.getEmail())
                .dateCreated(LocalDateTime.now())
                .build();
    }
}

package com.allemon.notesweb.domain.mapper;

import com.allemon.notesweb.domain.dto.CreateUserRequest;
import com.allemon.notesweb.domain.dto.UserResponse;
import com.allemon.notesweb.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User mapToUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .email(createUserRequest.getEmail())
                .dateCreated(LocalDateTime.now())
                .build();
    }

    public UserResponse mapToUserResponse (User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}

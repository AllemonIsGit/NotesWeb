package com.allemon.notesweb.service;

import com.allemon.notesweb.domain.dto.request.CreateUserRequest;
import com.allemon.notesweb.domain.exception.EmailAlreadyExistsException;
import com.allemon.notesweb.domain.exception.PasswordsDoesNotMatchException;
import com.allemon.notesweb.domain.exception.UserAlreadyExistsException;
import com.allemon.notesweb.domain.mapper.UserMapper;
import com.allemon.notesweb.domain.model.User;
import com.allemon.notesweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public void register(CreateUserRequest createUserRequest) {
        validate(createUserRequest);
        userRepository.save(userMapper.mapToUser(createUserRequest));
    }

    private void validate(CreateUserRequest createUserRequest) {

        if (!doesPasswordMatch(createUserRequest.getPassword(), createUserRequest.getRePassword())) {
            throw new PasswordsDoesNotMatchException("Passwords entered do not match.");
        }
        if (doesEmailExists(createUserRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }
        if (doesUsernameExists(createUserRequest.getUsername())) {
            throw new UserAlreadyExistsException("User already exists.");
        }
    }

    private Boolean doesPasswordMatch(String password, String rePassword) {
        return Objects.equals(password, rePassword);
    }

    private Boolean doesUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    private Boolean doesEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getLoggedOnUser() {
        return userRepository
                .findByUsername((String) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                .orElseThrow(() -> new UsernameNotFoundException("Username not registered."));
    }


}

package com.allemon.notesweb.service;
import com.allemon.notesweb.domain.dto.CreateUserRequest;
import com.allemon.notesweb.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void create(CreateUserRequest createUserRequest);
    User getUser(String username);
}

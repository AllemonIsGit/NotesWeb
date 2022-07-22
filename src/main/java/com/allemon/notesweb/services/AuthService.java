package com.allemon.notesweb.services;

import com.allemon.notesweb.domain.dto.CreateUserRequest;

public interface AuthService {
    void register(CreateUserRequest createUserRequest);
    void login();
}

package com.allemon.notesweb.services;

import com.allemon.notesweb.domain.dto.CreateUserRequest;
import com.allemon.notesweb.domain.model.User;

public interface AuthService {
    void register(CreateUserRequest createUserRequest);
    User getLoggedOnUser();
}

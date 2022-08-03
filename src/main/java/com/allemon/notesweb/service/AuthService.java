package com.allemon.notesweb.service;

import com.allemon.notesweb.domain.dto.request.CreateUserRequest;
import com.allemon.notesweb.domain.model.User;

public interface AuthService {
    void register(CreateUserRequest createUserRequest);
    User getLoggedOnUser();
}

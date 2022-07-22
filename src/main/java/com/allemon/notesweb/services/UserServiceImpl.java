package com.allemon.notesweb.services;

import com.allemon.notesweb.domain.dto.CreateUserRequest;
import com.allemon.notesweb.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    @Override
    public void create(CreateUserRequest createUserRequest) {

    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

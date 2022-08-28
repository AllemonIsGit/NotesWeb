package com.allemon.notesweb.filter;


import com.allemon.notesweb.domain.dto.response.AuthenticationResponse;
import com.allemon.notesweb.domain.dto.request.LoginRequest;
import com.allemon.notesweb.domain.mapper.UserMapper;
import com.allemon.notesweb.domain.model.User;
import com.allemon.notesweb.token.JWTTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = null;
        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Authentication attempt failed.");
        }
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User authenticatedUser = (User)authResult.getPrincipal();
        String jwtToken = jwtTokenUtil.createToken(authenticatedUser.getUsername());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtToken, userMapper.mapToUserResponse(authenticatedUser));
        objectMapper.writeValue(response.getOutputStream(), authenticationResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        objectMapper.writeValue(response.getOutputStream(), "Wrong login credentials.");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        super.unsuccessfulAuthentication(request, response, failed);
    }
}

package com.allemon.notesweb.config;

import com.allemon.notesweb.domain.mapper.UserMapper;
import com.allemon.notesweb.filter.CustomAuthFilter;
import com.allemon.notesweb.filter.CustomAuthorizationFilter;
import com.allemon.notesweb.service.UserService;
import com.allemon.notesweb.token.JWTTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final JWTTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api/v1/auth/*")
                    .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(getAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderBean() {
         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
         authenticationProvider.setUserDetailsService(userService);
         authenticationProvider.setPasswordEncoder(passwordEncoder);
         return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProviderBean());
    }

    private CustomAuthFilter getAuthenticationFilter() throws Exception {
        CustomAuthFilter filter = new CustomAuthFilter(objectMapper, authenticationManagerBean(),jwtTokenUtil, userMapper);
        filter.setFilterProcessesUrl("/api/v1/auth/login");
        return filter;
    }

    private CustomAuthorizationFilter getAuthorizationFilter() {
        return new CustomAuthorizationFilter(jwtTokenUtil, userService);
    }
}

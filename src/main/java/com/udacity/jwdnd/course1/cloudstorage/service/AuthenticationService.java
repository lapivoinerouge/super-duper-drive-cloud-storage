package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private UserMapper mapper;
    private HashService hashService;

    public AuthenticationService(UserMapper mapper, HashService hashService) {
        this.mapper = mapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // credentials from login page
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Users users = mapper.getUser(username);

        // check if user exists and compare passwords
        if (users != null) {
            String encodedSalt = users.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if (users.getPassword().equals(hashedPassword)) {
                // last argument represents list of permission user was granted
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        } return null;
    }

    // info for Spring which authorization schemes this class can handle
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

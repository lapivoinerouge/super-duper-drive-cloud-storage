package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private final UserMapper mapper;
    private final HashService hashService;

    public UserService(UserMapper mapper, HashService hashService) {
        this.mapper = mapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return mapper.getUser(username) == null;
    }

    public int createUser(Users users) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(users.getPassword(), encodedSalt);
        return mapper.insertUser(new Users(null, users.getUsername(), users.getPassword(), encodedSalt, users.getFirstName(), users.getLastName()));
    }

    public Users getUser(String username) {
        return mapper.getUser(username);
    }

    public void updateUser(Users users) {
        mapper.updateUser(users);
    }

    public void deleteUser(Integer userId) {
        mapper.deleteUser(userId);
    }
}

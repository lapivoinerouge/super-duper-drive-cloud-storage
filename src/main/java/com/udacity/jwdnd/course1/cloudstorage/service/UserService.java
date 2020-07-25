package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
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

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return mapper.insertUser(new User(null, user.getUsername(), user.getPassword(), encodedSalt, user.getFirstName(), user.getLastName()));
    }

    public User getUser(String username) {
        return mapper.getUser(username);
    }

    public void updateUser(User user) {
        mapper.updateUser(user);
    }

    public void deleteUser(Integer userId) {
        mapper.deleteUser(userId);
    }
}

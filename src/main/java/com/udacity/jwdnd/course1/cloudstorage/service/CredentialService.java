package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper mapper;
    private final EncryptionService encryptionService;
    private final HashService hashService;
    private final UserService userService;

    public CredentialService(CredentialMapper mapper, EncryptionService encryptionService, HashService hashService, UserService userService) {
        this.mapper = mapper;
        this.encryptionService = encryptionService;
        this.hashService = hashService;
        this.userService = userService;
    }

    public List<Credentials> getUserCredentials(Integer userId) {
        return mapper.getUserCredentials(userId);
    }

    public Credentials getCredentialsById(Integer credentialId) {
        return mapper.getCredentialsById(credentialId);
    }

    public Integer saveCredentials(Credentials credentials) {
        String key = RandomStringUtils.random(16, true, true);
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), key);
        credentials.setKey(key);
        credentials.setPassword(encryptedPassword);
        return mapper.insertCredentials(credentials);
    }

    public void editCredentials(Credentials credentials) {
        mapper.updateCredentials(credentials);
    }

    public void deleteCredentials(Integer credentialId) {
        mapper.deleteCredentials(credentialId);
    }

    public String decryptCredentials(Integer credentialId) {
        Credentials credentials = mapper.getCredentialsById(credentialId);
        String decryptedPassword = encryptionService.decryptValue(credentials.getPassword(), credentials.getKey());
        return decryptedPassword;
    }
}

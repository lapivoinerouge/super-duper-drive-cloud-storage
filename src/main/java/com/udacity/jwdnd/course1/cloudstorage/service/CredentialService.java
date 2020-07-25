package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
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

    public List<Credential> getUserCredentials(Integer userId) {
        return mapper.getUserCredentials(userId);
    }

    public Credential getCredentialsById(Integer credentialId) {
        return mapper.getCredentialsById(credentialId);
    }

    public Integer saveCredentials(Credential credential) {
        String key = RandomStringUtils.random(16, true, true);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), key);
        credential.setKey(key);
        credential.setPassword(encryptedPassword);
        return mapper.insertCredentials(credential);
    }

    public void editCredentials(Credential credential) {
        mapper.updateCredentials(credential);
    }

    public void deleteCredentials(Integer credentialId) {
        mapper.deleteCredentials(credentialId);
    }

    public String decryptCredentials(Integer credentialId) {
        Credential credential = mapper.getCredentialsById(credentialId);
        String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        return decryptedPassword;
    }
}

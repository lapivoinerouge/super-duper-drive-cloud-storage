package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/credentials")
public class CredentialController {
    @Autowired
    private CredentialService credentialService;

    @GetMapping("/decrypted/{credentialId}")
    public String getDecryptedCredentials(@PathVariable Integer credentialId) {
        return credentialService.decryptCredentials(credentialId);
    }

    @GetMapping("/encrypted/{credentialId}")
    public List<Credentials> getEncryptedCredentials(@PathVariable Integer userId) {
        return credentialService.getUserCredentials(userId);
    }

    @PostMapping("/")
    public Integer saveCredentials(@RequestBody Credentials credentials) {
        return credentialService.saveCredentials(credentials);
    }

    @PutMapping("/")
    public void editCredentials(@RequestBody Credentials credentials) {
        credentialService.editCredentials(credentials);
    }

    @DeleteMapping("/{credentialId}")
    public void deleteUser(@PathVariable Integer credentialId) {
        credentialService.deleteCredentials(credentialId);
    }
}

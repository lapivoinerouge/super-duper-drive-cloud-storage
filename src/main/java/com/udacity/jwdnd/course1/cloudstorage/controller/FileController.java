package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Files;
import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public List<Files> getUserFiles(@PathVariable Integer userId) {
        return fileService.getUserFiles(userId);
    }

    @GetMapping("/{fileId}")
    public Files getNote(@PathVariable Integer fileId) {
        return fileService.getFileById(fileId);
    }

    @PostMapping("/")
    public Integer uploadFile(@ModelAttribute("SpringWeb")MultipartFile multipartFile, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        return fileService.uploadFile(multipartFile, user.getUserId());
    }

    @PutMapping("/")
    public void editFileName(@RequestBody Files file) {
        fileService.editFileName(file);
    }

    @DeleteMapping("/{fileId}")
    public void deleteFile(@PathVariable Integer fileId) {
        fileService.deleteFile(fileId);
    }
}

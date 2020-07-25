package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Files;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private final FileMapper mapper;

    public FileService(FileMapper mapper) {
        this.mapper = mapper;
    }

    public List<Files> getUserFiles(Integer userId) {
        return mapper.getFiles(userId);
    }

    public Files getFileById(Integer fileId) {
        return mapper.getFileById(fileId);
    }

    public Integer uploadFile(MultipartFile multipartFile, Integer userId) {
        Files files = new Files();
        try {
            files.setFileName(multipartFile.getOriginalFilename());
            files.setContentType(multipartFile.getContentType());
            files.setFileSize(multipartFile.getSize());
            files.setUserId(userId);
            files.setFileData(multipartFile.getBytes());
        } catch (IOException e) {
        }
        return mapper.insertFile(files);
    }

    public void editFileName(Files files) {
        mapper.updateFile(files);
    }

    public void deleteFile(Integer fileId) {
        mapper.deleteFile(fileId);
    }
}

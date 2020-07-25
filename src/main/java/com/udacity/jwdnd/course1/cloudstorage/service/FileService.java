package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
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

    public List<File> getUserFiles(Integer userId) {
        return mapper.getFiles(userId);
    }

    public File getFileById(Integer fileId) {
        return mapper.getFileById(fileId);
    }

    public Integer uploadFile(MultipartFile multipartFile, Integer userId) {
        File file = new File();
        try {
            file.setFileName(multipartFile.getOriginalFilename());
            file.setContentType(multipartFile.getContentType());
            file.setFileSize(multipartFile.getSize());
            file.setUserId(userId);
            file.setFileData(multipartFile.getBytes());
        } catch (IOException e) {
        }
        return mapper.insertFile(file);
    }

    public void editFileName(File file) {
        mapper.updateFile(file);
    }

    public void deleteFile(Integer fileId) {
        mapper.deleteFile(fileId);
    }
}

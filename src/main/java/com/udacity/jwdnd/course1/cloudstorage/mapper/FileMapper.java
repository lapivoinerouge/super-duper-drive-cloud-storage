package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userId=#{userId}")
    List<Files> getFiles(int userId);

    @Select("SELECT * FROM FILES WHERE fileId=#{fileId}")
    Files getFileById(int fileId);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(Files files);

    @Update("UPDATE FILES SET fileName=#{fileName} WHERE fileId=#{fileId}")
    void updateFile(Files files);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId}")
    void deleteFile(int fileId);
}

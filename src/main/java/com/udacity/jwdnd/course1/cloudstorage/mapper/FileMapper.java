package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userId=#{userId}")
    List<File> getFiles(int userId);

    @Select("SELECT * FROM FILES WHERE fileId=#{fileId}")
    File getFileById(int fileId);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Update("UPDATE FILES SET fileName=#{fileName} WHERE fileId=#{fileId}")
    void updateFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId}")
    void deleteFile(int fileId);
}

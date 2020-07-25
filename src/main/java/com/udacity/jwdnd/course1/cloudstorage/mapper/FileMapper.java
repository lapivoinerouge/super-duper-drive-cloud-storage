package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES")
    List<File> getFiles();

    @Select("SELECT * FROM FILES WHERE fileId=#{fileId}")
    File getFile(int fileId);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Update("UPDATE FILES SET fileName=#{fileName}, contentType=#{contentType}, fileSize=#{fileSize}, fileData=#{fileData} WHERE fileId=#{fileId}")
    void updateFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId}")
    void deleteFile(int fileId);
}

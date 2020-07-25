package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userId=#{userId}")
    List<Credential> getUserCredentials(int userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId=#{credentialId}")
    Credential getCredentialsById(int credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredentials(Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, key=#{key}, password=#{password} WHERE credentialId=#{credentialId}")
    void updateCredentials(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId=#{credentialId}")
    void deleteCredentials(int credentialId);
}

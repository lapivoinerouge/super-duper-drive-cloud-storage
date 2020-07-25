package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS")
    List<Users> getUsers();

    @Select("SELECT * FROM USERS WHERE username=#{username}")
    Users getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(Users users);

    @Update("UPDATE USERS SET username=#{username}, firstname=#{firstName}, lastname=#{lastName} WHERE userId=#{userId}")
    void updateUser(Users users);

    @Delete("DELETE FROM USERS WHERE userId=#{userId}")
    void deleteUser(int userId);
}

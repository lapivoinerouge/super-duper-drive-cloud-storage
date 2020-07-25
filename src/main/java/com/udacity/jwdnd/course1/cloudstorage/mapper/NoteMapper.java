package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userId=#{userId}")
    List<Note> getUserNotes(int userId);

    @Select("SELECT * FROM NOTES WHERE noteId=#{noteId}")
    Note getNoteById(int noteId);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Update("UPDATE NOTES SET noteTitle=#{noteTitle}, noteDescription=#{noteDescription} WHERE noteId=#{noteId}")
    void updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteId=#{noteId}")
    void deleteNote(int noteId);
}
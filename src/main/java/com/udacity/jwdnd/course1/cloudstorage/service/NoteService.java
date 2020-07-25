package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper mapper;

    public NoteService(NoteMapper mapper) {
        this.mapper = mapper;
    }

    public List<Note> getUserNotes(Integer userId) {
        return mapper.getUserNotes(userId);
    }

    public Note getNoteById(Integer noteId) {
        return mapper.getNoteById(noteId);
    }

    public Integer saveNote(Note note) {
        return mapper.insertNote(note);
    }

    public void editNote(Note note) {
        mapper.updateNote(note);
    }

    public void deleteNote(Integer noteId) {
        mapper.deleteNote(noteId);
    }
}

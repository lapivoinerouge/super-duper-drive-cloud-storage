package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper mapper;

    public NoteService(NoteMapper mapper) {
        this.mapper = mapper;
    }

    public List<Notes> getUserNotes(Integer userId) {
        return mapper.getUserNotes(userId);
    }

    public Notes getNoteById(Integer noteId) {
        return mapper.getNoteById(noteId);
    }

    public Integer saveNote(Notes notes) {
        return mapper.insertNote(notes);
    }

    public void editNote(Notes notes) {
        mapper.updateNote(notes);
    }

    public void deleteNote(Integer noteId) {
        mapper.deleteNote(noteId);
    }
}

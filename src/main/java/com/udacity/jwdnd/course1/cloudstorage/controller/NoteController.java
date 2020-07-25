package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/")
    public List<Notes> getUserNotes(@PathVariable Integer userId) {
        return noteService.getUserNotes(userId);
    }

    @GetMapping("/{noteId}")
    public Notes getNote(@PathVariable Integer noteId) {
        return noteService.getNoteById(noteId);
    }

    @PostMapping("/")
    public Integer saveNote(@RequestBody Notes note) {
        return noteService.saveNote(note);
    }

    @PutMapping("/")
    public void editNote(@RequestBody Notes note) {
        noteService.editNote(note);
    }

    @DeleteMapping("/{credentialId}")
    public void deleteNote(@PathVariable Integer noteId) {
        noteService.deleteNote(noteId);
    }
}

package com.allemon.notesweb.controllers;

import com.allemon.notesweb.domain.model.Note;
import com.allemon.notesweb.services.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/notes")

public class NotesController {
    private final NoteServiceImpl noteService;

    @Autowired
    public NotesController(NoteServiceImpl noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public Note getNote() {
        return noteService.getById(1);
    }

    @PostMapping
    public void postNote(@RequestBody Note note) {
        noteService.save(note);
    }



}

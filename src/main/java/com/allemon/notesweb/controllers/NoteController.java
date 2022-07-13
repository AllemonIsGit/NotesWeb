package com.allemon.notesweb.controllers;

import com.allemon.notesweb.domain.dto.NoteDTO;
import com.allemon.notesweb.domain.model.Note;
import com.allemon.notesweb.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/notes")

public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable Integer id) {
        return noteService.getById(id);
    }

    @PostMapping
    public void createNote(@RequestBody @Valid NoteDTO noteDTO) {
        noteService.save(noteDTO);
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAll();
    }



}

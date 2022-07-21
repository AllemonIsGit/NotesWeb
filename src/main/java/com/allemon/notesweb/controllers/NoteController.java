package com.allemon.notesweb.controllers;

import com.allemon.notesweb.domain.dto.CreateNoteRequest;
import com.allemon.notesweb.domain.model.Note;
import com.allemon.notesweb.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createNote(@RequestBody @Valid CreateNoteRequest createNoteRequest) {
        noteService.save(createNoteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> patchNote(@PathVariable Integer id, @RequestBody @Valid CreateNoteRequest createNoteRequest) {
        noteService.update(id, createNoteRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}

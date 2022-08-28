package com.allemon.notesweb.controller;

import com.allemon.notesweb.domain.dto.request.CreateNoteRequest;
import com.allemon.notesweb.domain.dto.response.NoteResponse;
import com.allemon.notesweb.domain.model.Note;
import com.allemon.notesweb.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<NoteResponse>> getAllNotes(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> patchNote(@PathVariable Integer id, @RequestBody @Valid CreateNoteRequest createNoteRequest) {
        noteService.update(id, createNoteRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer id) {
        noteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}

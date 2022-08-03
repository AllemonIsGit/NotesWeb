package com.allemon.notesweb.service;


import com.allemon.notesweb.domain.dto.request.CreateNoteRequest;
import com.allemon.notesweb.domain.model.Note;

import java.util.List;

public interface NoteService {
    void save(CreateNoteRequest createNoteRequest);
    Note getById(int id);
    List<Note> getAll();
    void deleteById(int id);
    void update(Integer oldId, CreateNoteRequest newCreateNoteRequest);
}

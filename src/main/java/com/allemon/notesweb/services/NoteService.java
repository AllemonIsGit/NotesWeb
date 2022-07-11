package com.allemon.notesweb.services;


import com.allemon.notesweb.domain.model.Note;

import java.util.List;

public interface NoteService {
    void create(String title, String content);
    void save(Note note);
    Note getById(int id);
    List<Note> getAll();
    void deleteById(int id);
    void update(int oldId, Note newNote);
}

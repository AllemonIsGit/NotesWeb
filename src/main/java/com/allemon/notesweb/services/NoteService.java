package com.allemon.notesweb.services;


import com.allemon.notesweb.domain.dto.NoteDTO;
import com.allemon.notesweb.domain.model.Note;

import java.util.List;

public interface NoteService {
    void create(String title, String content);
    void save(NoteDTO noteDTO);
    Note getById(int id);
    List<Note> getAll();
    void deleteById(int id);
    void update(Integer oldId, NoteDTO newNoteDTO);
}

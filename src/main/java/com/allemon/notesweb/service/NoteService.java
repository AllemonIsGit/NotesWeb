package com.allemon.notesweb.service;


import com.allemon.notesweb.domain.dto.request.CreateNoteRequest;
import com.allemon.notesweb.domain.dto.response.NoteResponse;
import com.allemon.notesweb.domain.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteService {
    void save(CreateNoteRequest createNoteRequest);
    Note getById(int id);
    Page<NoteResponse> getAll(Pageable pageable);
    void deleteById(int id);
    void update(Integer oldId, CreateNoteRequest newCreateNoteRequest);
}

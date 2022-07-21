package com.allemon.notesweb.domain.mapper;

import com.allemon.notesweb.domain.dto.CreateNoteRequest;
import com.allemon.notesweb.domain.model.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public Note mapToNote(CreateNoteRequest createNoteRequest) {
        return new Note(createNoteRequest.getTitle(), createNoteRequest.getContent());
    }
}

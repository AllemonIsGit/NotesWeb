package com.allemon.notesweb.domain.mapper;

import com.allemon.notesweb.domain.dto.NoteDTO;
import com.allemon.notesweb.domain.model.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public Note mapToNote(NoteDTO noteDTO) {
        return new Note(noteDTO.getTitle(), noteDTO.getContent());
    }
}

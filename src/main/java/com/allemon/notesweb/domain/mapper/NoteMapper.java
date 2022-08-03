package com.allemon.notesweb.domain.mapper;

import com.allemon.notesweb.domain.dto.CreateNoteRequest;
import com.allemon.notesweb.domain.model.Note;
import com.allemon.notesweb.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NoteMapper {

    public Note mapToNote(CreateNoteRequest createNoteRequest, User user) {
        return Note.builder()
                .title(createNoteRequest.getTitle())
                .content(createNoteRequest.getContent())
                .dateCreated(LocalDateTime.now())
                .user(user)
                .build();
    }
}

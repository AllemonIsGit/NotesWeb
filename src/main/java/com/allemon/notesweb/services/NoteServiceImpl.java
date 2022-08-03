package com.allemon.notesweb.services;

import com.allemon.notesweb.domain.dto.CreateNoteRequest;
import com.allemon.notesweb.domain.exceptions.AccessForbiddenException;
import com.allemon.notesweb.domain.exceptions.NoteNotFoundException;
import com.allemon.notesweb.domain.mapper.NoteMapper;
import com.allemon.notesweb.domain.model.Note;
import com.allemon.notesweb.domain.model.User;
import com.allemon.notesweb.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private static final String NOTE_NOT_FOUND_MESSAGE = "Note was not found in Database.";
    private static final String FORBIDDEN_ACCESS_MESSAGE = "You can't access this content.";

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final AuthService authService;

    @Override
    public void save(CreateNoteRequest createNoteRequest) {
        User user = getLoggedOnUser();
        noteRepository.save(noteMapper.mapToNote(createNoteRequest, user));
    }

    @Override
    public Note getById(int id) {
        User user = getLoggedOnUser();

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(NOTE_NOT_FOUND_MESSAGE));
        if (!note.getUser().equals(user)) {
            throw new AccessForbiddenException(FORBIDDEN_ACCESS_MESSAGE);
        }
        return note;
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAllByUser(getLoggedOnUser());
    }

    @Override
    //TODO authorization
    public void deleteById(int id) {
        noteRepository.deleteById(id);
    }

    @Override
    //TODO authorization
    public void update(Integer oldId, CreateNoteRequest newCreateNoteRequest) {
        Note updatedNote = noteRepository.findById(oldId).
                orElseThrow(() -> new NoteNotFoundException(NOTE_NOT_FOUND_MESSAGE));
        if (newCreateNoteRequest.getTitle() != null) {
            updatedNote.setTitle(newCreateNoteRequest.getTitle());
        }
        if (newCreateNoteRequest.getContent() != null) {
            updatedNote.setContent(newCreateNoteRequest.getContent());
        }
        noteRepository.save(updatedNote);
    }

    private User getLoggedOnUser(){
        return authService.getLoggedOnUser();
    }


}

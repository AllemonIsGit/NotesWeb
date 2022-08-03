package com.allemon.notesweb.service;

import com.allemon.notesweb.domain.dto.request.CreateNoteRequest;
import com.allemon.notesweb.domain.exception.AccessForbiddenException;
import com.allemon.notesweb.domain.exception.NoteNotFoundException;
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
    private static final String FORBIDDEN_ACCESS_MESSAGE = "Request rejected.";

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
    public void deleteById(int id) {
        if (!noteRepository.existsByIdAndUser(id, getLoggedOnUser())) {
            throw new AccessForbiddenException(FORBIDDEN_ACCESS_MESSAGE);
        }
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Integer oldId, CreateNoteRequest newCreateNoteRequest) {
        Note updatedNote = noteRepository.findById(oldId).
                orElseThrow(() -> new NoteNotFoundException(NOTE_NOT_FOUND_MESSAGE));
        if (!updatedNote.getUser().equals(getLoggedOnUser())) {
            throw new AccessForbiddenException(FORBIDDEN_ACCESS_MESSAGE);
        }
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

package com.allemon.notesweb.services;

import com.allemon.notesweb.domain.exceptions.NoteNotFoundException;
import com.allemon.notesweb.domain.model.Note;
import com.allemon.notesweb.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private static final String NOTE_NOT_FOUND_MESSAGE = "Note was not found in Database.";
    private final NoteRepository noteRepository;

    @Override
    public void create(String title, String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteRepository.save(note);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public Note getById(int id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(NOTE_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void update(int oldId, Note newNote) {
        Note oldNote = noteRepository.findById(oldId).
                orElseThrow(() -> new NoteNotFoundException(NOTE_NOT_FOUND_MESSAGE));

        oldNote.setTitle(newNote.getTitle());
        oldNote.setContent(newNote.getContent());

        noteRepository.save(newNote);
    }
}

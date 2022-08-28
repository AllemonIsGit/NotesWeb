package com.allemon.notesweb.repository;

import com.allemon.notesweb.domain.model.Note;
import com.allemon.notesweb.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    Page<Note> findAllByUser(User user, Pageable pageable);
    boolean existsByIdAndUser(int id, User user);
}

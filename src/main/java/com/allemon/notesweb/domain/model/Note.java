package com.allemon.notesweb.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private int id;
    @Column(nullable = false)
    private String title;
    @Lob
    private String content;
    private String username;
    private LocalDateTime dateCreated;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;

    }
    
}

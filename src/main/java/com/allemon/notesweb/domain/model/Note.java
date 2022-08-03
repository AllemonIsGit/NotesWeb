package com.allemon.notesweb.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "notes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false, nullable = false)
    private int id;
    @Column(nullable = false)
    private String title;
    @Lob
    private String content;
    @ManyToOne
    private User user;
//    @Column(updatable = false, nullable = false)
//    private LocalDateTime dateCreated;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;

    }
    
}

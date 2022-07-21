package com.allemon.notesweb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoteRequest {
    @Size(min = 1, max = 45, message = "Title has to be 1 to 45 characters long.")
    private String title;
    @Size(max = 10000, message = "Note can't exceed 10000 characters")
    private String content;
}

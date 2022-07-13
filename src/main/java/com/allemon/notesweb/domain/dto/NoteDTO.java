package com.allemon.notesweb.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    @Min(value = 1, message = "Title needs at least one character.")
    @Max(value = 45, message = "Title can't exceed 45 characters.")
    private String title;
    @Max(value = 10000, message = "Note can't be longer than 10000 characters.")
    private String content;
}

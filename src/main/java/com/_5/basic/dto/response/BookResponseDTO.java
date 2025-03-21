package com._5.basic.dto.response;

import com._5.basic.model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String name;
    private LocalDate publishDate;
    private Long authorId;
    private String authorName;
}

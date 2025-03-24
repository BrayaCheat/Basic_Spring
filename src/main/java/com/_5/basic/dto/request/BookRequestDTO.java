package com._5.basic.dto.request;

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
public class BookRequestDTO {
    private String name;
    private LocalDate publishDate;
}

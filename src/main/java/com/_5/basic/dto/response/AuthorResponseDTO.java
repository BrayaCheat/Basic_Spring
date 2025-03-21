package com._5.basic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private LocalDate birth;
    private LocalDate createdAt;
}

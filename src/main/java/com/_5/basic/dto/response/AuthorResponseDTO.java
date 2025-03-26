package com._5.basic.dto.response;

import com._5.basic.model.AuthorImage;
import com._5.basic.model.AuthorProfile;
import com._5.basic.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private LocalDate birth;
    private List<AuthorImage> images;
    private List<Book> books;
    private AuthorProfile authorProfile;
}

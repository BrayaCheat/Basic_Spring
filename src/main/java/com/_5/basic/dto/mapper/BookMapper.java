package com._5.basic.dto.mapper;

import com._5.basic.dto.request.BookRequestDTO;
import com._5.basic.dto.response.BookResponseDTO;
import com._5.basic.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // Map DTO -> Entity (Request)
    @Mapping(target = "id", ignore = true) // Ignore ID when creating
    @Mapping(target = "createdAt", ignore = true) // CreatedAt is auto-generated
    @Mapping(target = "updatedAt", ignore = true) // UpdatedAt is auto-generated
    Book toEntity(BookRequestDTO dto);

    // Map Entity -> DTO (Response)
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "by", source = "author.name")
    @Mapping(target = "id", source = "id")
    BookResponseDTO toDTO(Book book);
}

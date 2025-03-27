package com._5.basic.dto.mapper;

import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import com._5.basic.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    // Map DTO -> Entity (Request)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Author toEntity(AuthorRequestDTO dto);

    // Map Entity -> DTO (Response)
    @Mapping(target = "books", source = "books")
    @Mapping(target = "authorProfile", source = "authorProfile")
    AuthorResponseDTO toDTO(Author author);
}

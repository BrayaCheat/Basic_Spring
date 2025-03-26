package com._5.basic.dto.mapper;

import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import com._5.basic.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    // Map DTO -> Entity (Request)
    @Mapping(target = "id", ignore = true) // Ignore ID when creating
    @Mapping(target = "createdAt", ignore = true) // CreatedAt is auto-generated
    @Mapping(target = "updatedAt", ignore = true) // UpdatedAt is auto-generated
    Author toEntity(AuthorRequestDTO dto);

    // Map Entity -> DTO (Response)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "authorProfile", source = "authorProfile")
    AuthorResponseDTO toDTO(Author author);
}

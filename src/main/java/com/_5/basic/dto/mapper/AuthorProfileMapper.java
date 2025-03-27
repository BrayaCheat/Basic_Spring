package com._5.basic.dto.mapper;

import com._5.basic.dto.request.AuthorProfileRequestDTO;
import com._5.basic.dto.response.AuthorProfileResponseDTO;
import com._5.basic.model.AuthorProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorProfileMapper {

    // Map DTO -> Entity (Request)
    @Mapping(target = "id", ignore = true) // Ignore ID when creating
    @Mapping(target = "createdAt", ignore = true) // CreatedAt is auto-generated
    @Mapping(target = "updatedAt", ignore = true) // UpdatedAt is auto-generated
    AuthorProfile toEntity(AuthorProfileRequestDTO dto);

    // Map Entity -> DTO (Response)
    AuthorProfileResponseDTO toDTO(AuthorProfile authorProfile);
}

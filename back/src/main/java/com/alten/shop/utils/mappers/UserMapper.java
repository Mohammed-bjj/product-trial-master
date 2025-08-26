package com.alten.shop.utils.mappers;

import com.alten.shop.utils.dtos.user.input.RegisterRequestDTO;
import com.alten.shop.utils.dtos.user.output.ProfileResponseDTO;
import com.alten.shop.utils.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterRequestDTO dto);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ProfileResponseDTO toProfileDto(User user);
}
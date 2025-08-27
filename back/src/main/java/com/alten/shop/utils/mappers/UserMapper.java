package com.alten.shop.utils.mappers;

import com.alten.shop.utils.dtos.user.input.RegisterRequestDTO;
import com.alten.shop.utils.dtos.user.output.LoginResponseDTO;
import com.alten.shop.utils.dtos.user.output.ProfileResponseDTO;
import com.alten.shop.utils.entities.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity toEntity(RegisterRequestDTO dto);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ProfileResponseDTO toProfileDto(UserEntity user);
    LoginResponseDTO toLoginResponseDto(String access_token) ;
}
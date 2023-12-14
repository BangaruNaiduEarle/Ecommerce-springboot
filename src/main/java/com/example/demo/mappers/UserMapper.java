package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.UserModel;

@Mapper(componentModel = "spring")

public interface UserMapper {
	
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDto toUserDto(UserModel user);
	
    // Map signUpDto to user, ignoring the 'password' field
	@Mapping(target = "password", ignore = true)
	UserModel signUpToUser(SignUpDto signUpDto);
	
}
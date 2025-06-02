package com.fullStackHexagonal.fullstackHexagonal.Application.Mappers;

import com.fullStackHexagonal.fullstackHexagonal.Domain.User;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO.UserResponse;

public class UserResponseMapper {
	public static UserResponse toDto(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.name(user.getName())
				.apellido(user.getApellido())
				.email(user.getEmail())
				.build();
	}
}

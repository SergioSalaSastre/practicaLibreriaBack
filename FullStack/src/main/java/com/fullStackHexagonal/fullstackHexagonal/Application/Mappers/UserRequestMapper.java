package com.fullStackHexagonal.fullstackHexagonal.Application.Mappers;


import com.fullStackHexagonal.fullstackHexagonal.Domain.User;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO.UserRequest;

public class UserRequestMapper {
	public static User toEntity(UserRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setApellido(request.getApellido());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		return user;
	}
}

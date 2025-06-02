package com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	private String name;
	private String apellido;
	private String email;
	private String password;
}

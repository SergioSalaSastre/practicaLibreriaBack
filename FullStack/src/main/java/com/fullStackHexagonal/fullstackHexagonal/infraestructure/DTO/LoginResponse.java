package com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class LoginResponse {
	private int id;
    private String name;
    private String apellido;
    private String email;
}

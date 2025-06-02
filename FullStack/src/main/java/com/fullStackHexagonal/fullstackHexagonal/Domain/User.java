package com.fullStackHexagonal.fullstackHexagonal.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
	private int id;
	private String name;
	private String apellido;
	private String email;
	private String password;
}

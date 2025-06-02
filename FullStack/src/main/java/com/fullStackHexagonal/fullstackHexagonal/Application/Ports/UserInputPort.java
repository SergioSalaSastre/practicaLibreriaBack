package com.fullStackHexagonal.fullstackHexagonal.Application.Ports;

import java.util.Optional;

import com.fullStackHexagonal.fullstackHexagonal.Domain.User;

public interface UserInputPort {
	
	User getById(int id);
	User create (User user);
	User Modify (User user);
	Optional <User> login (String email, String password);

}

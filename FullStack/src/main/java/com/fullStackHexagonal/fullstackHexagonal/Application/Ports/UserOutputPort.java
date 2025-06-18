package com.fullStackHexagonal.fullstackHexagonal.Application.Ports;

import java.util.List;
import java.util.Optional;

import com.fullStackHexagonal.fullstackHexagonal.Domain.User;

public interface UserOutputPort {

	Optional <User> findById(int id);
	User save (User user);
	Optional<User> findByEmail(String email);

	List<User> findAll();
}

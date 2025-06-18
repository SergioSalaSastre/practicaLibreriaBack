package com.fullStackHexagonal.fullstackHexagonal.Application;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.UserInputPort;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.UserOutputPort;
import com.fullStackHexagonal.fullstackHexagonal.Domain.User;


public class UserService implements UserInputPort {
	
	
	private final UserOutputPort userOutputPort;
	
	public UserService (UserOutputPort userOutputPort) {
		this.userOutputPort = userOutputPort;
	}

	@Override
	public User getById(int id) {
		return userOutputPort.findById(id)
			.orElseThrow(() -> new RuntimeException("No se ha encontrado el usuario con ID" + id));
	}

	 @Override
	    public User create(User user) {
	        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	        user.setPassword(hashedPassword);
	        return userOutputPort.save(user);
	    }

	 @Override
	 public User Modify(User user) {
	     User existente = userOutputPort.findById(user.getId())
	         .orElseThrow(() -> new RuntimeException("No existe el usuario con ID: " + user.getId()));

	     if (user.getPassword() != null && !user.getPassword().isBlank()) {
	         String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	         user.setPassword(hashedPassword);
	     } else {
	         // Si no viene nuevo password, conserva el antiguo
	         user.setPassword(existente.getPassword());
	     }

	     return userOutputPort.save(user);
	 }
	 
	 @Override
	 public Optional<User> login(String email, String password) {
	     Optional<User> userOptional = userOutputPort.findByEmail(email);

	     if (userOptional.isPresent()) {
	         User user = userOptional.get();
	         if (BCrypt.checkpw(password, user.getPassword())) {
	             return Optional.of(user);
	         }
	     }

	     return Optional.empty();
	 }
	@Override
	public List<User> getAll() {
		return userOutputPort.findAll();  // Recuperar todos los usuarios desde el repositorio
	}

}

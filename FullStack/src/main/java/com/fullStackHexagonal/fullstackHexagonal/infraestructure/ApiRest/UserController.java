package com.fullStackHexagonal.fullstackHexagonal.infraestructure.ApiRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fullStackHexagonal.fullstackHexagonal.Application.Mappers.UserRequestMapper;
import com.fullStackHexagonal.fullstackHexagonal.Application.Mappers.UserResponseMapper;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.UserInputPort;
import com.fullStackHexagonal.fullstackHexagonal.Domain.User;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO.LoginRequest;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO.UserRequest;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins ="http://localhost:4200", maxAge=3600)
@RequestMapping("/user")
@Tag(name = "Usuarios",  description ="Operaciones para la creación y modificación de usuarios")

public class UserController {

	@Autowired
	private UserInputPort userInputPort;
	
	@Operation(
		    summary = "Obtener usuario por ID",
		    description = "Busca y devuelve un usuario por su identificador único",
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
		                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
		        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
		    }
		)
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getById(
	    @Parameter(description = "ID del libro a buscar", example = "1")
	    @PathVariable int id
	) {
	    User user = userInputPort.getById(id);
	    return ResponseEntity.ok(UserResponseMapper.toDto(user)); 
	}
	
	@Operation(
		    summary = "Crear un nuevo usuario",
		    description = "Crea un nuevo usuario con los datos proporcionados.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Datos del usuario a crear",
		        required = true,
		        content = @Content(schema = @Schema(implementation = UserRequest.class))
		    ),
		    responses = {
		        @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente",
		            content = @Content(schema = @Schema(implementation = UserResponse.class))),
		        @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
		    }
		)
		@PostMapping
		public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		    User nuevoUsuario = UserRequestMapper.toEntity(userRequest);
		    User created = userInputPort.create(nuevoUsuario);
		    return ResponseEntity.status(201).body(UserResponseMapper.toDto(created));
		}
	
	
	@Operation(
		    summary = "Actualizar un usuario existente",
		    description = "Actualiza los datos de un usuario registrado.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Usuario con los nuevos datos (debe incluir un ID existente)",
		        required = true,
		        content = @Content(schema = @Schema(implementation = UserRequest.class))
		    ),
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
		                     content = @Content(schema = @Schema(implementation = UserResponse.class))),
		        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
		    }
		)
	
		@PutMapping("/{id}")
		public ResponseEntity<UserResponse> updateUser(
		        @PathVariable int id,
		        @RequestBody UserRequest userRequest) {

		    User userToUpdate = UserRequestMapper.toEntity(userRequest);
		    userToUpdate.setId(id); // Aseguramos que el ID se establece desde la URL
		    User actualizado = userInputPort.Modify(userToUpdate);
		    return ResponseEntity.ok(UserResponseMapper.toDto(actualizado));
		}
	
	@Operation(
		    summary = "Iniciar sesión",
		    description = "Valida las credenciales de un usuario",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Credenciales del usuario",
		        required = true,
		        content = @Content(schema = @Schema(implementation = LoginRequest.class))
		    ),
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso",
		                     content = @Content(schema = @Schema(implementation = UserResponse.class))),
		        @ApiResponse(responseCode = "401", description = "Credenciales incorrectas", content = @Content)
		    }
		)
		@PostMapping("/login")
		public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
		    return userInputPort.login(loginRequest.getEmail(), loginRequest.getPassword())
		        .map(user -> ResponseEntity.ok(UserResponseMapper.toDto(user)))
		        .orElseGet(() -> ResponseEntity.status(401).build());
		}
}

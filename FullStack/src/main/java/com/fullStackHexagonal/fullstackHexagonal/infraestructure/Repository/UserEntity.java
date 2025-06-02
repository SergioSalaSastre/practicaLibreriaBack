package com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Entidad que representa un usuario en la base de datos")

public class UserEntity {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column
	 @Schema(description = "Identificador único del usuario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	 private int id;
	 
	 @Column
	 @Schema(description = "Nombre del usuario", example = "Pepito")
	 private String name;
	 
	 @Column
	 @Schema(description = "Apellido del usuario", example = "García")
	 private String apellido;
	 
	 @Column
	 @Schema(description = "Correo electrónico de la persona usuaria", example = "juan@gmail.com")
	 private String email;
	 
	 @Column
	 @Schema(description = "Contraseña cifrada de la persona usuaria")
	 private String password;
	 
	
}
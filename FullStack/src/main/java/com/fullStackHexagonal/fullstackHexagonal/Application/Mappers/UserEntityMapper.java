package com.fullStackHexagonal.fullstackHexagonal.Application.Mappers;
import com.fullStackHexagonal.fullstackHexagonal.Domain.User;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository.UserEntity;

public class UserEntityMapper {
    public static User toDomain(UserEntity entity) {
        return new User(
            entity.getId(),
            entity.getName(),
            entity.getApellido(),
            entity.getEmail(),
            entity.getPassword()
       
        );
    }

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
        	.id(user.getId())
        	.name(user.getName())
        	.apellido(user.getApellido())
        	.email(user.getEmail())
        	.password(user.getPassword())
            .build();
    }
}

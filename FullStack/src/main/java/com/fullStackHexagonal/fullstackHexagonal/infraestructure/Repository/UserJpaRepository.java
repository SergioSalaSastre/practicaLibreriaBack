package com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository <UserEntity, Integer >{

	Optional<UserEntity> findByEmail(String email);

	}
	

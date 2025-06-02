package com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.UserOutputPort;
import com.fullStackHexagonal.fullstackHexagonal.Domain.User;
import com.fullStackHexagonal.fullstackHexagonal.Application.Mappers.UserEntityMapper;

@Service
public class UserJpaRepositoryServiceImpl implements UserOutputPort {

    private final UserJpaRepository userJpaRepository;

    public UserJpaRepositoryServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findById(int id) {
        return userJpaRepository.findById(id).map(UserEntityMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity savedEntity = userJpaRepository.save(UserEntityMapper.toEntity(user));
        return UserEntityMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
            .map(UserEntityMapper::toDomain);
    }
}

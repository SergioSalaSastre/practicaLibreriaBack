package com.fullStackHexagonal.fullstackHexagonal.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fullStackHexagonal.fullstackHexagonal.Application.UserService;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.UserInputPort;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.UserOutputPort;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository.UserJpaRepository;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository.UserJpaRepositoryServiceImpl;

@Configuration
public class UserConfig {

    @Bean
    public UserOutputPort userOutputPort(UserJpaRepository userJpaRepository) {
        return new UserJpaRepositoryServiceImpl(userJpaRepository);
    }

    @Bean
    public UserInputPort userInputPort(UserOutputPort userOutputPort) {
        return new UserService(userOutputPort);
    }
}

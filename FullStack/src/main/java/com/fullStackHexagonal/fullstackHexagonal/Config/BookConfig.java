package com.fullStackHexagonal.fullstackHexagonal.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fullStackHexagonal.fullstackHexagonal.Application.BookService;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.BookInputPort;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.BookOutputPort;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository.BookJpaRepository;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository.BookJpaRepositoryServiceImpl;

@Configuration
public class BookConfig {

    @Bean
    public BookOutputPort bookOutputPort(BookJpaRepository bookJpaRepository) {
        return new BookJpaRepositoryServiceImpl(bookJpaRepository);
    }

    @Bean
    public BookInputPort bookInputPort(BookOutputPort bookOutputPort) {
        return new BookService(bookOutputPort);
    }
}

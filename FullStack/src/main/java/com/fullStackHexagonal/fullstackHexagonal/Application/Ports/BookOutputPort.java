package com.fullStackHexagonal.fullstackHexagonal.Application.Ports;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;


public interface BookOutputPort extends JpaRepository<Book, Integer> {}

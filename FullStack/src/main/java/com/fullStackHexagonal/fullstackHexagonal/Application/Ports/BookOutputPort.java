package com.fullStackHexagonal.fullstackHexagonal.Application.Ports;

import java.util.List;
import java.util.Optional;

import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;

public interface BookOutputPort {

    List<Book> findAll();

    Optional<Book> findById(int id);

    Book save(Book book);

    void deleteById(int id);

    List<Book> findByFilters(String title, String author, Integer publicationYear, String literaryGenre);
}
	
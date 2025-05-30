package com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository;

import java.util.List;
import java.util.Optional;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.BookOutputPort;
import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;

public class BookJpaRepositoryServiceImpl implements BookOutputPort {

    private final BookJpaRepository bookJpaRepository;

    public BookJpaRepositoryServiceImpl(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookJpaRepository.findAll();
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookJpaRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookJpaRepository.save(book);
    }

    @Override
    public void deleteById(int id) {
        bookJpaRepository.deleteById(id);
    }

    @Override
    public List<Book> findByFilters(String title, String author, Integer publicationYear, String literaryGenre) {
        return bookJpaRepository
            .findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCaseAndPublicationYearAndLiteraryGenreContainingIgnoreCase(
                title, author, publicationYear, literaryGenre);
    }
}
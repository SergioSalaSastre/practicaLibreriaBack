package com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.BookOutputPort;
import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.Mappers.BookEntityMapper;

public class BookJpaRepositoryServiceImpl implements BookOutputPort {

    private final BookJpaRepository bookJpaRepository;

    public BookJpaRepositoryServiceImpl(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookJpaRepository.findAll()
                .stream()
                .map(BookEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookJpaRepository.findById(id)
                .map(BookEntityMapper::toDomain);
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = BookEntityMapper.toEntity(book);
        BookEntity saved = bookJpaRepository.save(entity);
        return BookEntityMapper.toDomain(saved);
    }

    @Override
    public void deleteById(int id) {
        bookJpaRepository.deleteById(id);
    }
    

    @Override
    public List<Book> findByFilters(String title, String author, Integer publicationYear, String literaryGenre) {
        return bookJpaRepository
            .findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCaseAndPublicationYearAndLiteraryGenreContainingIgnoreCase(
                title, author, publicationYear, literaryGenre)
            .stream()
            .map(BookEntityMapper::toDomain)
            .collect(Collectors.toList());
    }
    
}
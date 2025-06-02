package com.fullStackHexagonal.fullstackHexagonal.Application.Mappers;

import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository.BookEntity;

public class BookEntityMapper {

    public static Book toDomain(BookEntity entity) {
        return new Book(
            entity.getId(),
            entity.getTitle(),
            entity.getAuthor(),
            entity.getPublicationYear(),
            entity.getLiteraryGenre(),
            entity.getCover(),
            entity.getDescription()
        );
    }

    public static BookEntity toEntity(Book book) {
        return BookEntity.builder()
            .id(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .publicationYear(book.getPublicationYear())
            .literaryGenre(book.getLiteraryGenre())
            .cover(book.getCover())
            .description(book.getDescription())
            .build();
    }
}
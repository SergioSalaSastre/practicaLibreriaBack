package com.fullStackHexagonal.fullstackHexagonal.infraestructure.Mappers;

import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO.BookResponse;

public class BookResponseMapper {
	public static BookResponse toDto (Book book) {
		return BookResponse.builder()
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

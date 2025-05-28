package com.fullStackHexagonal.fullstackHexagonal.Application.Mappers;

import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO.BookRequest;


public class BookRequestMapper {
	public static Book toEntity(BookRequest request) {
		Book book = new Book();
		book.setTitle(request.getTitle());
		book.setAuthor(request.getAuthor());
		book.setPublicationYear(request.getPublicationYear());
		book.setLiteraryGenre(request.getLiteraryGenre());
		book.setCover(request.getCover());
		book.setDescription(request.getDescription());
		return book;
		
	}
}

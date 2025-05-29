package com.fullStackHexagonal.fullstackHexagonal.Application.Ports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;


public interface BookOutputPort extends JpaRepository<Book, Integer> {
		
	List<Book> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCaseAndPublicationYearAndLiteraryGenreContainingIgnoreCase(
		    String title, String author, Integer publicationYear, String literaryGenre);
	
}
	
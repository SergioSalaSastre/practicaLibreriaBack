package com.fullStackHexagonal.fullstackHexagonal.Application.Ports;

import java.util.List;


import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;

public interface BookInputPort {
	
	List <Book> buscar(String title, String author, Integer publicationYear, String literaryGenre);
	Book encuentraById(int id);
	Book add(Book book);
	void borrar(int id);
	Book actualizar(Book book);
}

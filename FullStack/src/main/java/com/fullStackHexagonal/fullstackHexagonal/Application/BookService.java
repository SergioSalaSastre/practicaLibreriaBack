package com.fullStackHexagonal.fullstackHexagonal.Application;

import java.util.List;
import java.util.stream.Collectors;

import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.BookInputPort;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.BookOutputPort;
import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;

public class BookService implements BookInputPort {

    private final BookOutputPort bookOutputPort;

    public BookService(BookOutputPort bookOutputPort) {
        this.bookOutputPort = bookOutputPort;
    }

    @Override
    public List<Book> buscar(String title, String author, Integer publicationYear, String literaryGenre) {
        return bookOutputPort.findAll().stream()
            .filter(book -> title == null || book.getTitle().toLowerCase().contains(title.toLowerCase()))
            .filter(book -> author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase()))
            .filter(book -> publicationYear == null || book.getPublicationYear().equals(publicationYear))
            .filter(book -> literaryGenre == null || book.getLiteraryGenre().toLowerCase().contains(literaryGenre.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Override
    public Book encuentraById(int id) {
        return bookOutputPort.findById(id)
            .orElseThrow(() -> new RuntimeException("No se ha encontrado el libro con ID" + id));
    }

    @Override
    public Book add(Book book) {
        return bookOutputPort.save(book);
    }

    @Override
    public void borrar(int id) {
        if (bookOutputPort.findById(id).isPresent()) {
            bookOutputPort.deleteById(id);
        } else {
            throw new RuntimeException("No se puede borrar: no existe el libro con ID: " + id);
        }
    }

    @Override
    public Book actualizar(Book book) {
        // Verifico que existe
        Book existente = bookOutputPort.findById(book.getId())
            .orElseThrow(() -> new RuntimeException("No existe el libro con ID: " + book.getId()));

        // Actualizo todo el objeto para simplificar
        return bookOutputPort.save(book);
    }
}
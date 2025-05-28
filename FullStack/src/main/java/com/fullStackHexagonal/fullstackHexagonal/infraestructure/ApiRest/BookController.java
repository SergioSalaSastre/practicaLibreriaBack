package com.fullStackHexagonal.fullstackHexagonal.infraestructure.ApiRest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.BookInputPort;
import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;

@RestController
@CrossOrigin(origins="http://localhost:4200",maxAge=3600)
@RequestMapping("/book")     //http://localhost:8080/book
@Tag(name = "Libros", description = "Operaciones para la gestión de la biblioteca digital")
public class BookController {
	
	@Autowired
	BookInputPort bookInputPort;
	
	
	@Operation(
	        summary = "Listar libros",
	        description = "Devuelve una lista de todos los libros registrados en el sistema.",
	        responses = {
	            @ApiResponse(
	                responseCode = "200",
	                description = "Lista de libros encontrada",
	                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))
	            ),
	            @ApiResponse(
	                responseCode = "500",
	                description = "Error interno del servidor",
	                content = @Content
	            )
	        }
	    )
	
	@GetMapping
	public List<Book> listar() {
		return bookInputPort.listar();
	}
	
	
	@Operation(
		    summary = "Obtener libro por ID",
		    description = "Busca y devuelve un libro por su identificador único",
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Libro encontrado",
		                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
		        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content)
		    }
		)
		@GetMapping("/{id}")
		public Book getById(
		    @Parameter(description = "ID del libro a buscar", example = "1")
		    @PathVariable int id
		) {
		    return bookInputPort.encuentraById(id); 
		}
	
	@Operation(
		    summary = "Crear un nuevo libro",
		    description = "Crea un nuevo libro con los datos proporcionados.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Libro a crear",
		        required = true,
		        content = @Content(schema = @Schema(implementation = Book.class))
		    ),
		    responses = {
		        @ApiResponse(responseCode = "201", description = "Libro creado correctamente",
		                     content = @Content(schema = @Schema(implementation = Book.class))),
		        @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
		    }
		)
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
	    Book nuevoLibro = bookInputPort.add(book);
	    return ResponseEntity.status(201).body(nuevoLibro);
	}
}

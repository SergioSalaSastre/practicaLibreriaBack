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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.fullStackHexagonal.fullstackHexagonal.Application.Mappers.BookRequestMapper;
import com.fullStackHexagonal.fullstackHexagonal.Application.Mappers.BookResponseMapper;
import com.fullStackHexagonal.fullstackHexagonal.Application.Ports.BookInputPort;
import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO.BookRequest;
import com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO.BookResponse;


@RestController
@CrossOrigin(origins="http://localhost:4200",maxAge=3600) //puerto del front, tiempo máximo de espera en segundos
@RequestMapping("/book")     //http://localhost:8080/book
@Tag(name = "Libros", description = "Operaciones para la gestión de la biblioteca digital")
public class BookController {
	
	@Autowired
	BookInputPort bookInputPort;
	
	// He eliminado estas dos líneas porque no necesito inyectar los mappers,
	// ya que los métodos son estáticos y se usan directamente con la clase.
	// @Autowired
	// private BookRequestMapper bookRequestMapper;
	
	// @Autowired
	// private BookResponseMapper bookResponseMapper;
	
	
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
	public ResponseEntity<List<BookResponse>> listar() {
	    List<BookResponse> books = bookInputPort.listar()
	        .stream()
	        .map(BookResponseMapper::toDto) // Uso directo del método estático (sin instancia)
	        .toList();

	    return ResponseEntity.ok(books);
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
	public ResponseEntity<BookResponse> getById(
	    @Parameter(description = "ID del libro a buscar", example = "1")
	    @PathVariable int id
	) {
	    Book book = bookInputPort.encuentraById(id);
	    return ResponseEntity.ok(BookResponseMapper.toDto(book)); // Método estático usado directamente
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
	public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
	    Book nuevoLibro = BookRequestMapper.toEntity(bookRequest); // Uso método estático
	    Book created = bookInputPort.add(nuevoLibro);
	    return ResponseEntity.status(201).body(BookResponseMapper.toDto(created)); // Uso método estático
	}
	
	@Operation(
			summary = "Borrar un libro por ID",
			description = "Busca un libro por ID y en caso de encontrarlo, lo borra",
			responses = {
					 @ApiResponse(responseCode = "204", description = "Libro eliminado correctamente"),
				     @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content)
			}
		)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(
	    @Parameter(description = "ID del libro a eliminar", example = "1")
	    @PathVariable int id
	) {
	    bookInputPort.borrar(id); 
	    return ResponseEntity.noContent().build(); // 204 No Content
	}
	
	@Operation(
		    summary = "Actualizar un libro existente",
		    description = "Actualiza los datos de un libro registrado.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Libro con los nuevos datos (debe incluir un ID existente)",
		        required = true,
		        content = @Content(schema = @Schema(implementation = Book.class))
		    ),
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Libro actualizado correctamente",
		                     content = @Content(schema = @Schema(implementation = Book.class))),
		        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content)
		    }
		)
	@PutMapping("/{id}")  // ====== CAMBIO 2 ======= Agregado {id} en la ruta para que coincida con el parámetro PathVariable
	public ResponseEntity<BookResponse> updateBook(
	        @PathVariable int id, // ====== CAMBIO 3 ======= Se añadió @PathVariable para que Spring lo vincule a la ruta
	        @RequestBody BookRequest bookRequest) {
	    
	    Book bookActualizado = BookRequestMapper.toEntity(bookRequest);
	    bookActualizado.setId(id); // Seteamos el id que viene por path
	    Book actualizado = bookInputPort.actualizar(bookActualizado);
	    return ResponseEntity.ok(BookResponseMapper.toDto(actualizado));
	}
	
}

package com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un libro en la base de datos")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Schema(description = "Identificador único del libro", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Column
    @Schema(description = "Título del libro", example = "Cien años de soledad")
    private String title;

    @Column
    @Schema(description = "Autor del libro", example = "Gabriel García Márquez")
    private String author;

    @Column
    @Schema(description = "Año de publicación", example = "1967")
    private Integer publicationYear;

    @Column
    @Schema(description = "Género literario del libro", example = "Realismo mágico")
    private String literaryGenre;

    @Column
    @Schema(description = "URL de la portada del libro", example = "http://example.com/portada.jpg")
    private String cover;

    @Column
    @Schema(description = "Descripción o sinopsis del libro", example = "Una obra maestra que narra la historia de la familia Buendía...")
    private String description;
}


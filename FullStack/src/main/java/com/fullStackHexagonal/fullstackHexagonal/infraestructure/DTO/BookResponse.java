package com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class BookResponse {
	private int id;
    private String title;
    private String author;
    private Integer publicationYear;
    private String literaryGenre;
    private String cover;
    private String description;
}

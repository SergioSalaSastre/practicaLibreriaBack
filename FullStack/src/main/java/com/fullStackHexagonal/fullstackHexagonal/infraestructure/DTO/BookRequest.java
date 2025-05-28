package com.fullStackHexagonal.fullstackHexagonal.infraestructure.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
	private String title;
	private String author;
	private Integer publicationYear;
	private String literaryGenre;
	private String cover;
	private String description;
}

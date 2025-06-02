package com.fullStackHexagonal.fullstackHexagonal.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String title;
    private String author;
    private Integer publicationYear;
    private String literaryGenre;
    private String cover;
    private String description;

}
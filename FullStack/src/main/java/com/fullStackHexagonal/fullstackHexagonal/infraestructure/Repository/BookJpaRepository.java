package com.fullStackHexagonal.fullstackHexagonal.infraestructure.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fullStackHexagonal.fullstackHexagonal.Domain.Book;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCaseAndPublicationYearAndLiteraryGenreContainingIgnoreCase(
        String title, String author, Integer publicationYear, String literaryGenre);
}

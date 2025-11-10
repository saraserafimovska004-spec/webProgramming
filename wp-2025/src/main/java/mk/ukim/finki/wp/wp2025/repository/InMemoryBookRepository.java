package mk.ukim.finki.wp.wp2025.repository;

import mk.ukim.finki.wp.wp2025.bootstrap.DataHolder;

import java.util.List;
import java.util.stream.Collectors;

import mk.ukim.finki.wp.wp2025.bootstrap.DataHolder;
import mk.ukim.finki.wp.wp2025.model.Book;
import mk.ukim.finki.wp.wp2025.model.BookReservation;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(book ->
                        book.getTitle().toLowerCase().contains(text.toLowerCase()) &&
                                book.getAverageRating() >= rating).collect(Collectors.toList());
    }

}

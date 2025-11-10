package mk.ukim.finki.wp.wp2025.service;

import mk.ukim.finki.wp.wp2025.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text,Double rating);

}

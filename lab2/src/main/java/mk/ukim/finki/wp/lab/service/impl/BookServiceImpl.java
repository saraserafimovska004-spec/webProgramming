package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, double rating) {
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public Book findBook(Long id) {
        return bookRepository.findBook(id);
    }

    @Override
    public Book add(String title, String genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findById(authorId);
        return bookRepository.add(title, genre, averageRating, author);
    }

    @Override
    public Book update(Long id, String title, String genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findById(authorId);
        return bookRepository.update(id, title, genre, averageRating, author);
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }
}

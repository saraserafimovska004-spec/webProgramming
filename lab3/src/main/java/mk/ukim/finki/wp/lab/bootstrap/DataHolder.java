package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.Genre;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GenreRepository;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Genre> genres = new ArrayList<>();

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public DataHolder(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @PostConstruct
    public void init() {
        if(genreRepository.findAll().isEmpty()) {
            genres = new ArrayList<>();
            genres.add(new Genre("Novel", "Genre for novel books."));
            genres.add(new Genre("Science Fiction", "Genre for Sci-Fi books."));
            genres.add(new Genre("Dystopian", "Genre for dystopian books."));
            genres.add(new Genre("Fantasy", "Genre for fantasy books."));
            genres.add(new Genre("Gothic Novel", "Genre for gothic novel books."));
            genreRepository.saveAll(genres);
        }

        if(bookRepository.findAll().isEmpty()) {
            books = new ArrayList<>();
            Genre sciFi = genreRepository.findByName("Science Fiction");
            Genre fantasy = genreRepository.findByName("Fantasy");
            Genre dystopian = genreRepository.findByName("Dystopian");
            Genre gothicNovel = genreRepository.findByName("Gothic Novel");
            books.add(new Book("Harry Potter", fantasy, 5.0));
            books.add(new Book("Lord of the Rings", fantasy, 3.2));
            books.add(new Book("1984", dystopian, 4.5));
            books.add(new Book("Project Hail Mary", sciFi, 4.8));
            books.add(new Book("The Hitchhiker's Guide To The Galaxy", sciFi, 4.2));
            books.add(new Book("Brave New World", dystopian, 3.7));
            books.add(new Book("Frankenstein", gothicNovel, 4.6));
            books.add(new Book("Dune", sciFi, 4.3));
            books.add(new Book("The Hunger Games", dystopian, 4.9));
            books.add(new Book("2001: A Space Odyssey", sciFi, 4.5));
            bookRepository.saveAll(books);
        }
    }
}

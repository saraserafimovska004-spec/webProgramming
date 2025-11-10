package mk.ukim.finki.wp.wp2025.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.wp2025.model.Book;
import mk.ukim.finki.wp.wp2025.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<Book>(10);
    public static List<BookReservation> reservations=new ArrayList<>();
    @PostConstruct
    public void init() {
        books.add(new Book("Clean Code", "Programming", 4.8));
        books.add(new Book("The Pragmatic Programmer", "Software Engineering", 4.7));
        books.add(new Book("Effective Java", "Programming", 4.9));
        books.add(new Book("Design Patterns", "Architecture", 4.6));
        books.add(new Book("Refactoring", "Programming", 4.5));
        books.add(new Book("Code Complete", "Programming", 4.7));
        books.add(new Book("Head First Design Patterns", "Programming", 4.3));
        books.add(new Book("You Don’t Know JS", "JavaScript", 4.4));
        books.add(new Book("Spring in Action", "Frameworks", 4.5));
        books.add(new Book("Introduction to Algorithms", "Computer Science", 4.8));
    }
}

package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Genre;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping
    @SuppressWarnings("unchecked")
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String filterAuthorId,
                               @RequestParam(required = false) String filterGenreId,
                               Model model,
                               HttpSession session
    ) {
        if(error != null) {
            model.addAttribute("error", error);
        }

        List<Book> books;
        List<String> lastViewed = (List<String>) session.getAttribute("lastViewed");

        long authorId = -1L;
        long genreId = -1L;

        try {
            authorId = Long.parseLong(filterAuthorId);
            genreId = Long.parseLong(filterGenreId);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        if(authorId != -1 && genreId != -1) {
            books = bookService.findBooksByAuthorIdAndGenre(authorId, genreId);
        } else if(authorId != -1) {
            books = bookService.findBooksByAuthorId(authorId);
        } else if(genreId != -1) {
            books = bookService.findBooksByGenreId(genreId);
        }
        else {
            books = bookService.listAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("lastViewedBooks", lastViewed);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("filteredId", authorId);
        model.addAttribute("filteredGenreId", genreId);
        return "listBooks";
    }

    @GetMapping("/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam Long genreId,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId
    ) {
        Genre genre = genreService.findById(genreId);
        bookService.add(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @GetMapping("/book-form/{bookId}")
    public String getEditBookForm(@PathVariable Long bookId, Model model) {
        if(bookId == null) {
            return "redirect:/books?error=BookNotFound";
        }

        Book book = bookService.findBook(bookId);
        model.addAttribute("bookId", book.getId());
        model.addAttribute("title", book.getTitle());
        Genre bookGenre = book.getGenre();
        model.addAttribute("genreId", bookGenre != null ? bookGenre.getId() : -1);
        model.addAttribute("genre", book.getGenreName());
        model.addAttribute("averageRating", book.getAverageRating());
        Author author = book.getAuthor();
        model.addAttribute("authorId", author != null ? author.getId() : -1);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "book-form";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam Long genreId,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId
    ) {
        Genre genre = genreService.findById(genreId);
        bookService.update(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.delete(bookId);
        return "redirect:/books";
    }
}

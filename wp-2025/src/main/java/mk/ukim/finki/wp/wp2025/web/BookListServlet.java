package mk.ukim.finki.wp.wp2025.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.wp.wp2025.model.Book;
import mk.ukim.finki.wp.wp2025.service.BookService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookListServlet", urlPatterns = "")
@Component
public class BookListServlet extends HttpServlet {
    private final BookService bookService;
    private final SpringTemplateEngine templateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine templateEngine) {
        this.bookService = bookService;
        this.templateEngine = templateEngine;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(exchange);
        String text=req.getParameter("text");
        String ratingParam=req.getParameter("rating");
        Double rating=null;
        if(ratingParam!=null && !ratingParam.isEmpty()){
            try {
                rating=Double.parseDouble(ratingParam);
            } catch (NumberFormatException e) {
                rating=null;
            }
        }

        List<Book> books;
        if ((text==null || text.isEmpty()) && rating==null) {
            books = bookService.listAll();
        }
        else {
            books=bookService.searchBooks(text!= null ? text : "",rating!=null ? rating : 0.0);
        }
        context.setVariable("books", books);
        HttpSession session=req.getSession();
        List<String> lastVisitedBooks=(List<String>)session.getAttribute("lastVisitedBooks");
        if(lastVisitedBooks==null){
            lastVisitedBooks=new ArrayList<>();
        }
        context.setVariable("lastVisitedBooks", lastVisitedBooks);
        templateEngine.process("listBooks.html", context, resp.getWriter());
    }

}

package mk.ukim.finki.wp.wp2025.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.wp.wp2025.model.BookReservation;
import mk.ukim.finki.wp.wp2025.service.BookReservationService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "bookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {

    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine templateEngine;

    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine templateEngine) {
        this.bookReservationService = bookReservationService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numCopies = Integer.parseInt(req.getParameter("numCopies"));

        String clientIp = req.getRemoteAddr();

        BookReservation reservation = bookReservationService.placeReservation(
                bookTitle, readerName, readerAddress, numCopies
        );
        HttpSession session=req.getSession();
        List<String> lastVisitedBooks=(List<String>)session.getAttribute("lastVisitedBooks");
        if (lastVisitedBooks==null) {
            lastVisitedBooks=new ArrayList<>();
        }
        lastVisitedBooks.add(0,bookTitle);
        if (lastVisitedBooks.size()>3){
            lastVisitedBooks.removeLast();
        }
        session.setAttribute("lastVisitedBooks", lastVisitedBooks);
        context.setVariable("reservation", reservation);
        context.setVariable("clientIp", clientIp);

        templateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }
}
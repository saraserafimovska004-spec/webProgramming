package mk.ukim.finki.wp.wp2025.repository;

import mk.ukim.finki.wp.wp2025.bootstrap.DataHolder;
import mk.ukim.finki.wp.wp2025.model.BookReservation;
import org.springframework.stereotype.Repository;
@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {


    @Override
    public BookReservation save(BookReservation bookReservation) {
        DataHolder.reservations.add(bookReservation);
        return bookReservation;
    }
}

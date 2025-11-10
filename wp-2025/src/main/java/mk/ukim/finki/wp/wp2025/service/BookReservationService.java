package mk.ukim.finki.wp.wp2025.service;

import mk.ukim.finki.wp.wp2025.model.BookReservation;

public interface BookReservationService {
    BookReservation placeReservation(String bookTitle,String readerName, String readerAddress,int numberOfCopies);
}

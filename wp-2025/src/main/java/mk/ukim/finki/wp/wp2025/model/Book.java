package mk.ukim.finki.wp.wp2025.model;

public class Book {
    String title;
    String genre;
    double averageRating;
    public Book(String title, String genre, double averageRating) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getAverageRating() {
        return averageRating;
    }
}

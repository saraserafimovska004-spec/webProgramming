package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Genre genre;
    private double averageRating;
    @ManyToOne
    private Author author;

    public Book(String title, Genre genre, double averageRating) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
    }

    public String getAuthorName() {
        return author != null  ? author.getName() + " " + author.getSurname() : "None";
    }

    public String getGenreName() {
        return genre != null ? genre.getName() : "None";
    }
}

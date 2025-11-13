package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Author {
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String biography;

    public Author(String name, String surname, String country, String biography) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;
    }
}

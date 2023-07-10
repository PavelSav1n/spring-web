package ru.itsjava.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity(name = "films") //В БД в таблице films всего три поля id, title, genre_id. Тут в сущности добавляется поле private List<Place> places;
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    // Это поле будет записываться/читаться в/из таблицы place.
    @OneToMany(targetEntity = Place.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="film_id")
    private List<Place> places;
}

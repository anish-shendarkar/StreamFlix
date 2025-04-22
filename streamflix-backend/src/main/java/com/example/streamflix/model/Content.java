package com.example.streamflix.model;

import jakarta.persistence.*;
import lombok.*;
import com.example.streamflix.model.Movie;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Series series;

    private String content_type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

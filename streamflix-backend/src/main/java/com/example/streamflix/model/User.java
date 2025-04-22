package com.example.streamflix.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String name;
    private String password;
    private String role;

    @ManyToMany
    private List<Movie> watchHistory;

    @ManyToMany
    private List<Movie> continueWatching;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> myList = new ArrayList<>();
}

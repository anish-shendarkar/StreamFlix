package com.example.streamflix.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String Description;
    private int episodeNumber;
    private String fileName;
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;
}

package com.example.streamflix.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String description;
    private int episodeNumber;
    private String fileName;
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonBackReference("season-episodes")
    private Season season;
}

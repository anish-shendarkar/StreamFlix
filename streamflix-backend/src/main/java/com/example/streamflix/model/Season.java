package com.example.streamflix.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seasonNumber;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private List<Episode> episodes;
}

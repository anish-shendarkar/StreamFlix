package com.example.streamflix.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference("series-seasons")
    private Series series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    @JsonManagedReference("season-episodes")
    private List<Episode> episodes;
}

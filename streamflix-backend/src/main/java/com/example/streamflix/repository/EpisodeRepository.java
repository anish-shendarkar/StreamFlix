package com.example.streamflix.repository;

import com.example.streamflix.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findBySeasonId(Long id);
}

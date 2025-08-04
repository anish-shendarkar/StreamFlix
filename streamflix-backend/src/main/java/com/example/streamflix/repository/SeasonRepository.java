package com.example.streamflix.repository;

import com.example.streamflix.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeasonRepository extends JpaRepository<Season, Long> {
    List<Season> findBySeriesId(Long seriesId);
}

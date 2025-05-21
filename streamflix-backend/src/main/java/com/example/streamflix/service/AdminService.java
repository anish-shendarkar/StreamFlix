package com.example.streamflix.service;

import com.example.streamflix.model.Movie;
import com.example.streamflix.model.Series;
import com.example.streamflix.repository.MovieRepository;
import com.example.streamflix.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MovieRepository movieRepo;
    private final SeriesRepository seriesRepo;

    public String addMovie(Movie movie) {
        System.out.println(movie);
        movieRepo.save(movie);
        return "Movie saved";
    }

    public String addSeries(Series series) {
        seriesRepo.save(series);
        return "Series saved";
    }
}

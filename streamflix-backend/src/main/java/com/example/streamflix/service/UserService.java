package com.example.streamflix.service;

import com.example.streamflix.model.Content;
import com.example.streamflix.model.Movie;
import com.example.streamflix.model.Series;
import com.example.streamflix.model.User;
import com.example.streamflix.repository.MovieRepository;
import com.example.streamflix.repository.SeriesRepository;
import com.example.streamflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MovieRepository movieRepo;
    private final SeriesRepository seriesRepo;
    private final UserRepository userRepo;

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public List<Series> getAllSeries() {
        return seriesRepo.findAll();
    }

    public List<Content> getUserList(User user) {
        return user.getMyList();
    }

    public String addToList(User user, Long itemId, String contentType) {
        Content content = new Content();
        content.setContent_type(contentType.toLowerCase());

        if (contentType.equalsIgnoreCase("movie")) {
            Movie movie = movieRepo.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            content.setMovie(movie);
        } else if (contentType.equalsIgnoreCase("series")) {
            Series series = seriesRepo.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            content.setSeries(series);
        } else {
            throw new RuntimeException("Invalid content type");
        }

        user.getMyList().add(content);
        userRepo.save(user);
        return "Item added to list";
    }

    public String removeFromList(User user, Long itemId) {
        user.getMyList().removeIf(item -> {
            if ("movie".equalsIgnoreCase(item.getContent_type()) && item.getMovie() != null) {
                return item.getMovie().getId() == itemId;
            } else if ("series".equalsIgnoreCase(item.getContent_type()) && item.getSeries() != null) {
                return item.getSeries().getId() == itemId;
            }
            return false;
        });

        userRepo.save(user);
        return "Item removed from list";
    }
}



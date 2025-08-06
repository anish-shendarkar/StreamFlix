package com.example.streamflix.controller;

import com.example.streamflix.model.Content;
import com.example.streamflix.model.Movie;
import com.example.streamflix.model.Series;
import com.example.streamflix.model.User;
import com.example.streamflix.repository.MovieRepository;
import com.example.streamflix.repository.SeriesRepository;
import com.example.streamflix.repository.UserRepository;
import com.example.streamflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieRepository.findAll());
    }

    @GetMapping("/series")
    public ResponseEntity<List<Series>> getAllSeries() {
        return ResponseEntity.ok(seriesRepository.findAll());
    }

    @GetMapping("/mylist")
    public ResponseEntity<List<Content>> getMyList(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user.getMyList());
    }

    @PostMapping("/mylist/{itemId}")
    public ResponseEntity<String> addToList(@PathVariable Long itemId, @PathVariable String contentType, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.addToList(user, itemId, contentType));
    }

    @DeleteMapping("/mylist/{itemId}")
    public ResponseEntity<String> removeFromList(@PathVariable Long itemId, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.removeFromList(user, itemId));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(userService.getMovie(movieId));
    }

    @GetMapping("/series/{seriesId}")
    public ResponseEntity<Series> getSeries(@PathVariable Long seriesId) {
        return ResponseEntity.ok(userService.getSeries(seriesId));
    }
}

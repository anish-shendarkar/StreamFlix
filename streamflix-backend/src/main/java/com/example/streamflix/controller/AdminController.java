package com.example.streamflix.controller;

import com.example.streamflix.model.Movie;
import com.example.streamflix.model.Series;
import com.example.streamflix.repository.MovieRepository;
import com.example.streamflix.repository.SeriesRepository;
import com.example.streamflix.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/addmovie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(adminService.addMovie(movie));
    }

    @PostMapping("/addseries")
    public ResponseEntity<String> addSeries(@RequestBody Series series) {
        return ResponseEntity.ok(adminService.addSeries(series));
    }
}

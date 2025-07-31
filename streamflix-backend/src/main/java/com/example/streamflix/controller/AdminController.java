package com.example.streamflix.controller;

import com.example.streamflix.model.Movie;
import com.example.streamflix.model.Series;
import com.example.streamflix.repository.MovieRepository;
import com.example.streamflix.repository.SeriesRepository;
import com.example.streamflix.service.AdminService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping(value="/addmovie", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addMovie(
            @RequestPart("movie") @Valid @NotNull Movie movie,
            @RequestPart("thumbnail") MultipartFile thumbnail,
            @RequestPart("videoFile") MultipartFile videoFile
            ) {
        return ResponseEntity.ok(adminService.addMovie(movie, thumbnail, videoFile));
    }

    @PostMapping("/series")
    public ResponseEntity<String> addSeries(
            @RequestPart("series") @Valid @NotNull Series series,
            @RequestPart("thumbnail") MultipartFile thumbnail
    ) {
        String result = adminService.addSeries(series, thumbnail);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/series")
    public ResponseEntity<List<Series>> getAllSeries() {
        return ResponseEntity.ok(adminService.getAllSeries());
    }

    @GetMapping("/series/{id}")
    public ResponseEntity<Series> getSeriesById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getSeriesById(id));
    }

    @PutMapping("/series/{id}")
    public ResponseEntity<String> updateSeries(
            @PathVariable Long id,
            @RequestPart("series") Series series,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail) {
        String result = adminService.updateSeries(id, series, thumbnail);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/series/{id}")
    public ResponseEntity<String> deleteSeries(@PathVariable Long id) {
        String result = adminService.deleteSeries(id);
        return ResponseEntity.ok(result);
    }
}

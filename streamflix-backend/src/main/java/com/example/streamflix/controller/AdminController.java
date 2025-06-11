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

    @PostMapping("/addseries")
    public ResponseEntity<String> addSeries(@RequestBody Series series) {
        return ResponseEntity.ok(adminService.addSeries(series));
    }
}

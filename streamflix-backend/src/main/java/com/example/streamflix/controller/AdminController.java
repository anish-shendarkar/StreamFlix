package com.example.streamflix.controller;

import com.example.streamflix.model.Movie;
import com.example.streamflix.model.Season;
import com.example.streamflix.model.Episode;
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

    // Season endpoints

    @PostMapping("/series/{seriesId}/seasons")
    public ResponseEntity<String> createSeason(
            @PathVariable Long seriesId,
            @RequestBody Season season) {
        String result = adminService.createSeason(seriesId, season);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/series/{seriesId}/seasons")
    public ResponseEntity<List<Season>> getSeasonsBySeriesId(@PathVariable Long seriesId) {
        return ResponseEntity.ok(adminService.getSeasonsBySeriesId(seriesId));
    }

    @GetMapping("/seasons/{id}")
    public ResponseEntity<Season> getSeasonById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getSeasonById(id));
    }

    @DeleteMapping("/seasons/{id}")
    public ResponseEntity<String> deleteSeason(@PathVariable Long id) {
        String result = adminService.deleteSeason(id);
        return ResponseEntity.ok(result);
    }

    // Episode endpoints

    @PostMapping("/seasons/{seasonId}/episodes")
    public ResponseEntity<String> uploadEpisode(
            @PathVariable Long seasonId,
            @RequestPart("episode") Episode episode,
            @RequestPart("videoFile") MultipartFile videoFile) {
        String result = adminService.uploadEpisode(seasonId, episode, videoFile);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/seasons/{seasonId}/episodes/batch")
    public ResponseEntity<String> uploadMultipleEpisodes(
            @PathVariable Long seasonId,
            @RequestPart("episodes") List<Episode> episodes,
            @RequestPart("videoFiles") List<MultipartFile> videoFiles) {
        String result = adminService.uploadMultipleEpisodes(seasonId, episodes, videoFiles);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/seasons/{seasonId}/episodes")
    public ResponseEntity<List<Episode>> getEpisodesBySeasonId(@PathVariable Long seasonId) {
        return ResponseEntity.ok(adminService.getEpisodesBySeasonId(seasonId));
    }

    @GetMapping("/episodes/{id}")
    public ResponseEntity<Episode> getEpisodeById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getEpisodeById(id));
    }

    @PutMapping("/episodes/{id}")
    public ResponseEntity<String> updateEpisode(
            @PathVariable Long id,
            @RequestPart("episode") Episode episode,
            @RequestPart(value = "videoFile", required = false) MultipartFile videoFile) {
        String result = adminService.updateEpisode(id, episode, videoFile);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/episodes/{id}")
    public ResponseEntity<String> deleteEpisode(@PathVariable Long id) {
        String result = adminService.deleteEpisode(id);
        return ResponseEntity.ok(result);
    }
}
